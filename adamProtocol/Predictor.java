package adamProtocol;

import java.util.Calendar;

import adamProtocol.exceptions.OutOfBoundsDoseException;

public class Predictor {
	
	private static final int WEEK = 7;
	private static final double THRESHOLD_FOR_NEUTROPHIL_DECLINE = 1 / 10 * BloodCounts.BILLION;
	Patient testCase;
	Prediction prediction;
		
	enum Action {
		STOP, FIFTY_PERCENT, SAME_AS_BEFORE, MAX_OF_PREV_AND_TOLERATED, FIRST_VISIT, INCREASE_6MP, INCREASE_MTX
	}
	public Prediction predictFor(Patient testCase) {
		this.setPatient(testCase);
		BloodCounts.Condition condition = testCase.getBloodCounts().condition;
		if (testCase.getNumberOfVisits() == 1) {
			if (condition.compareTo(BloodCounts.Condition.TARGET) >= 0) {
				setPredictionFor(Action.FIRST_VISIT);
				return prediction;
			}
			else 
				throw new RuntimeException("The patient has blood counts outside protocol parameters on first visit.");
		}
		BloodCounts.Condition prevCondition = testCase.getBloodCountsAt(testCase.getVisitNumber(-2)).condition;
		DisplayMessage.displayMessage("Previous visit's condition: "+ prevCondition);
		
		if (condition == BloodCounts.Condition.SEVERE)
			setPredictionFor(Action.STOP);
		else if (condition == BloodCounts.Condition.MILD) {
			if (prevCondition == BloodCounts.Condition.SEVERE)
				setPredictionFor(Action.STOP);
			else if (prevCondition == BloodCounts.Condition.MILD)
				setPredictionFor(Action.SAME_AS_BEFORE);
			else {
				resetPatientHundredPercentDose();
				setPredictionFor(Action.FIFTY_PERCENT);
			}
		}
		else {
			if (checkCan6mpIncrease(condition))
				setPredictionFor(Action.INCREASE_6MP);
			else if (prevCondition.compareTo(BloodCounts.Condition.TARGET)<0) {
				if (canAttemptHunderedPercentDose())
					setPredictionFor(Action.MAX_OF_PREV_AND_TOLERATED);
				else
					setPredictionFor(Action.FIFTY_PERCENT);
			}
			else if (testCase.getNumberOfVisits()>1 && (testCase.getPreviousDose().is6mpPercentGreaterThanmtxPercent(testCase.getHundredPercentDose()))) {
				int timeToWait = condition == BloodCounts.Condition.TARGET ? 4*WEEK : 2*WEEK;
				if (daysSinceDoseIncrease() >= timeToWait){
					setPredictionFor(Action.INCREASE_MTX);
				}
				else
					setPredictionFor(Action.MAX_OF_PREV_AND_TOLERATED);
			}
			else
				setPredictionFor(Action.MAX_OF_PREV_AND_TOLERATED);
		}
		return prediction;
	}
	
	private boolean canAttemptHunderedPercentDose() {
		int visitNumber = testCase.getNumberOfVisits()-2;
		int visitsOfStopDose = getVisitsOfStopDose(visitNumber);
		if (visitsOfStopDose == 0)
			return true;
		else if (visitsOfStopDose >= 3)
			return false;

		Dose hundredPercentDose = testCase.getHundredPercentDose();
		// If a tolerated dose exists, we do not need to worry about not reaching the hundred percent dose
		if (testCase.getToleratedDose() != null && testCase.getToleratedDose().compareTo(hundredPercentDose)>=0)
			return true;

		// If there have been two previous unsuccessful attempts to prescribe 100% dose,
		// we do not wish to try again with the same dose
		int attempts = 0;
		while (visitNumber > 0) {
			visitsOfStopDose = getVisitsOfStopDose(visitNumber);
			// A failed attempt means at least 2 visits of stop dose.
			if (visitsOfStopDose>=2) {
				attempts+=1;
				if (attempts>=2)
					return false;
			}
			visitNumber -= visitsOfStopDose+1;
		}
		return true;
	}

	// Get the number of visits with consecutive stop dose upto passed index
	// Named as visits as 1 visit could correspond to 1 or 2 or more weeks.
	// Important thing to remember
	// as here we assume a visit means 1 week only
	private int getVisitsOfStopDose(int index) {
		int count=0;
		while (testCase.getDoseAt(index).equals(Dose.roundOff(0, 0))) {
			index--;
			count++;
		}
		return count;
	}

	private int daysSinceDoseIncrease() {
		int days = 0, index = testCase.getVisitNumber(-1);
		if (index>0)
			days += testCase.getDaysSinceStartAt(index) - testCase.getDaysSinceStartAt(index-1);
		while (index >=2){
			if (testCase.getDoseAt(index-2).compareTo(testCase.getDoseAt(index-1))<0)
				break;
			else
				days += testCase.getDaysSinceStartAt(index-1) - testCase.getDaysSinceStartAt(index-2);
			index--;
		}
		DisplayMessage.displayMessage("Days since previous dose increase: " +days);
		return days;
	}

	private void setPredictionFor(Action toDo) {
		prediction.addComments("Action being taken: " + toDo);
		Dose fallbackDose = (testCase.getToleratedDose() == null) ? testCase.getHundredPercentDose() : testCase.getToleratedDose();
		Dose max;
		try {
			if (null != toDo)
				switch (toDo) {
					case FIRST_VISIT:
						prediction.setDose(fallbackDose);
						prediction.setAppointmentAfterDays(2*WEEK, (Calendar)testCase.getCurrentDate().clone());
						prediction.addComments("Dose Change: CONTINUE SAME DOSE AS PREVIOUS");
						break;
					case STOP:
						prediction.setDose(Dose.roundOff(0,0));
						prediction.setAppointmentAfterDays(WEEK, (Calendar)testCase.getCurrentDate().clone());
						prediction.addComments("Dose Change: STOP");
						break;
					case FIFTY_PERCENT:
						max = Dose.maximumOf(testCase.getPreviousDose(), testCase.getHundredPercentDose());
						prediction.setDose(max.multiplyByPercentage(50, 50));
						prediction.setAppointmentAfterDays(WEEK, (Calendar)testCase.getCurrentDate().clone());
						prediction.addComments("Dose Change: REDUCE BY 50%");
						break;
					case SAME_AS_BEFORE:
						prediction.setDose(testCase.getPreviousDose());
						prediction.setAppointmentAfterDays(2*WEEK, (Calendar)testCase.getCurrentDate().clone());
						prediction.addComments("Dose Change: CONTINUE AS BEFORE");
						break;
					case MAX_OF_PREV_AND_TOLERATED:
						max = Dose.maximumOf(testCase.getPreviousDose(), fallbackDose);
						prediction.setDose(max);
						prediction.setAppointmentAfterDays(2*WEEK, (Calendar)testCase.getCurrentDate().clone());
						if (max.equals(fallbackDose) && !max.equals(testCase.getDoseAt(-2)))
							prediction.addComments("Dose Change: DOSE RESET TO TOLERATED");
						else
							prediction.addComments("Dose Change: CONTINUE AS BEFORE");
						break;
					case INCREASE_6MP:
						max = Dose.maximumOf(testCase.calculateIncreasedDoseByPercent(Dose.STANDARD_INCREASE,0), fallbackDose);
						prediction.setDose(max);
						prediction.setAppointmentAfterDays(2*WEEK, (Calendar)testCase.getCurrentDate().clone());
						if (max.equals(fallbackDose))
							prediction.addComments("Dose Change: DOSE RESET TO TOLERATED");
						else
							prediction.addComments("Dose Change: INCREASE 6MP");
						break;
					case INCREASE_MTX:
						max = Dose.maximumOf(testCase.calculateIncreasedDoseByPercent(0, Dose.STANDARD_INCREASE), fallbackDose);
						prediction.setDose(max);
						prediction.setAppointmentAfterDays(2*WEEK, (Calendar)testCase.getCurrentDate().clone());
						if (max.equals(fallbackDose))
							prediction.addComments("Dose Change: DOSE RESET TO TOLERATED");
						else
							prediction.addComments("Dose Change: INCREASE MTX");
						break;
					default:
						break;
                }
		} catch (OutOfBoundsDoseException ex) {
			DisplayMessage.displayMessage("Unexpectedly the algorithm tried to set out of bounds dose");
			ex.printStackTrace();
		}
	}
	
	private boolean checkCan6mpIncrease(BloodCounts.Condition condition) {
		if (!testCase.isDoseWithinSafeLimit(testCase.calculateIncreasedDoseByPercent(Dose.STANDARD_INCREASE,0)))
			return false;
		int noOfWeeksToWait;
		if(condition == BloodCounts.Condition.TARGET)
			noOfWeeksToWait = 8;
		else if(condition == BloodCounts.Condition.HIGH)
			noOfWeeksToWait = 6;
		else
			return false;
		if (haveCountsBeenDeclining())
			return false;
		if (daysOfConditionNotLessThan(condition) >= noOfWeeksToWait*WEEK) {
			DisplayMessage.displayMessage("Weeks condition satisfied");
			if(daysSinceDoseIncrease()>=8*WEEK) {
				DisplayMessage.displayMessage("Should attempt increase");
				return true;
			}
		}
		else if (condition == BloodCounts.Condition.HIGH) {
			return checkCan6mpIncrease(BloodCounts.Condition.TARGET);
		}
		return false;
	}

	private boolean haveCountsBeenDeclining() {
		int index = testCase.getNumberOfVisits()-2;
		if (index < 0)
			return false;
		int daysSinceStart = testCase.getDaysSinceStart();
		BloodCounts currentCounts = testCase.getBloodCountsAt(-1);
		BloodCounts prevCounts = testCase.getBloodCountsAt(-2);
		while (index >=0 && testCase.getDaysSinceStartAt(index)>daysSinceStart-6*WEEK) {
			prevCounts  = testCase.getBloodCountsAt(index--);
			if (currentCounts.compareTo(prevCounts) >= 0)
				return false;
			currentCounts = prevCounts;
		}
		if (testCase.getBloodCountsAt(-1).compareTo(new BloodCounts(prevCounts.neutrophilCount-THRESHOLD_FOR_NEUTROPHIL_DECLINE, 0))<0)
				return true;
		return false;
	}

	private int daysOfConditionNotLessThan(BloodCounts.Condition benchmarkcondition) {
		int days = 0, index = testCase.getVisitNumber(-1);
		BloodCounts bloodCount;
		while (index > 0){
			bloodCount = testCase.getBloodCountsAt(index-1);
			if (bloodCount.condition.compareTo(benchmarkcondition) >= 0)
				days += testCase.getDaysSinceStartAt(index) - testCase.getDaysSinceStartAt(--index);
			else
				break;
		}
		
		DisplayMessage.displayMessage("Days of status above " + benchmarkcondition + " is " + days);
		return days;
	}

	private void resetPatientHundredPercentDose() {
		this.testCase.setHundredPercentDose(testCase.getHundredPercentDose().multiplyByPercentage(50, 50));
	}

	private void setPatient(Patient testCase) {
		this.testCase = testCase;
		this.prediction = new Prediction(testCase);
	}
}
