package adamProtocol;

import adamProtocol.exceptions.OutOfBoundsDoseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Patient implements Cloneable {

	private static final int WEEK = 7;
	private final Calendar startDate = new GregorianCalendar();
	private Calendar currentDate = new GregorianCalendar();
	private Calendar nextAppointment = new GregorianCalendar();
	private String patientId;
	private final double height, weight;
	private final double bsa;
	private final Dose hunderedPercentDose;
	private Dose toleratedDose;
	private Vector<BloodCounts> bloodCounts;
	private Vector<Dose> weeklyDose;
	private Vector<Dose> weeklyToleratedDose;
	private Vector<Integer> daysSinceStart;

	public Patient(Date startMTDate, String id, double height, double weight) {
		this.startDate.setTime(startMTDate);
		this.patientId = id;
		this.height = height;
		this.weight = weight;
		bsa=Math.sqrt(height * weight / 3600.0);
		hunderedPercentDose=Dose.roundOff(bsa*20, bsa*60*7);
		toleratedDose = null;
		bloodCounts = new Vector<>();
		weeklyDose = new Vector<>();
		weeklyToleratedDose = new Vector<>();
		daysSinceStart = new Vector<>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Patient clone() {
		Patient dummy = new Patient(this.startDate.getTime(), this.patientId, this.height, this.weight);
		dummy.nextAppointment.setTime(this.nextAppointment.getTime());
		dummy.currentDate.setTime(this.currentDate.getTime());
		dummy.toleratedDose = this.toleratedDose;
		dummy.bloodCounts = (Vector<BloodCounts>) this.bloodCounts.clone();
		dummy.weeklyDose = (Vector<Dose>) this.weeklyDose.clone();
		dummy.weeklyToleratedDose = (Vector<Dose>) this.weeklyToleratedDose.clone();
		dummy.daysSinceStart = (Vector<Integer>) this.daysSinceStart.clone();

		return dummy;
	}
        
	public void addVisit(Date visitDate, BloodCounts bloodCounts) {
		setCurrentDate(visitDate);
		setCount(bloodCounts);
	}

	private void setCurrentDate(Date recordDate) {
		this.currentDate.setTime(recordDate);
		this.setdaysSinceStart(recordDate);
	}

	private void setdaysSinceStart(Date recordDate) {
		Calendar date = new GregorianCalendar();
		date.setTime(recordDate);
		long current = date.getTimeInMillis();
		long start = startDate.getTimeInMillis();
		int days = (int) TimeUnit.MILLISECONDS.toDays(Math.abs(current - start));

		this.daysSinceStart.addElement(days);
	}

	private void setCount(BloodCounts bloodCounts) {
		this.bloodCounts.addElement(bloodCounts);
		checkAndSetToleratedDose();

		this.weeklyToleratedDose.addElement(getToleratedDose());
	}

	public void checkAndSetToleratedDose() {
		if (this.getBloodCounts().condition.compareTo(BloodCounts.Condition.TARGET) >= 0 && 
				daysOfDoseTolerance() >= 6 * WEEK) {
			if (getToleratedDose() == null || getToleratedDose().compareTo(getPreviousDose()) < 0) {
				setToleratedDose(getPreviousDose());
			}
		}
	}

	public Dose getToleratedDose() {
		return toleratedDose;
	}

	public void setToleratedDose(Dose dose) {
		this.toleratedDose = dose;
	}

	private int daysOfDoseTolerance() {
		int days = 0, index = this.getVisitNumber(-1), daysSinceStart = this.getDaysSinceStart();
		if (index <= 0)
			return days;
		Dose refDose = getPreviousDose();
		while (index > 0) {
			if (this.getBloodCountsAt(index).condition.compareTo(BloodCounts.Condition.TARGET) >= 0
					&& this.getDoseAt(index - 1).compareTo(refDose) >= 0)
				days = daysSinceStart - this.getDaysSinceStartAt(index - 1);
			else
				break;
			index--;
		}
		return days;
	}

	public int numberOftimesofStatusRecoverFromBelow(BloodCounts.Condition target) {
		int index, total = getNumberOfVisits()-1, times = 0;
		for(index = 1; index<total; index++) {
			BloodCounts.Condition previousStatus = getBloodCountsAt(index-1).condition;
			if(previousStatus.compareTo(target)<0 && getBloodCountsAt(index).condition.compareTo(target)>=0)
				times++;
		}
		return times;
	}

	public Dose getPreviousDose() {
		return getDoseAt(-2);
	}

	public Dose getDoseAt(int index) {
		return weeklyDose.elementAt(getVisitNumber(index));
	}

	public int getDaysSinceStart() {
		return getDaysSinceStartAt(daysSinceStart.size() - 1);
	}

	public int getDaysSinceStartAt(int index) {
		return daysSinceStart.elementAt(getVisitNumber(index));
	}

	public int getVisitNumber(int index) {
		if (index >= 0)
			return index;
		else
			return getNumberOfVisits() + index;
	}

	public int getNumberOfVisits() {
		return daysSinceStart.size();
	}

	public BloodCounts getBloodCounts() {
		return getBloodCountsAt(bloodCounts.size() - 1);
	}

	public BloodCounts getBloodCountsAt(int index) {
		return bloodCounts.elementAt(getVisitNumber(index));
	}

	public int getCycle() {
		int cycle = getDaysSinceStart() / WEEK;
		return cycle / 12 + 1;
	}

	public int getWeekInCycle() {
		int week = getDaysSinceStart() / WEEK;
		return week % 12 + 1;
	}

	public Dose setCurrentDose(Dose curr) throws OutOfBoundsDoseException {
		if (!this.isDoseWithinSafeLimit(curr))
			throw new OutOfBoundsDoseException(
				"The current dose of " + curr + " is above safe-limit (" + Dose.SAFE_LIMIT + " times) of BSA");
		
		this.weeklyDose.addElement(curr);
		return curr;
	}

	public boolean isDoseWithinSafeLimit(Dose dose) {
		double percentageStep = Dose.STANDARD_INCREASE/100;
		double smpPercentage = percentageStep
				* Math.round(dose.getSmp() / getHunderedPercentDose().getSmp() / percentageStep);
		double mtxPercentage = percentageStep
				* Math.round(dose.getMtx() / getHunderedPercentDose().getMtx() / percentageStep);

		if (smpPercentage > Dose.SAFE_LIMIT || mtxPercentage > Dose.SAFE_LIMIT)
			return false;
		return true;
	}

	public Dose getHunderedPercentDose() {
		return hunderedPercentDose;
	}

	public Calendar getCurrentDate() {
		return currentDate;
	}

	public Dose calculateIncreasedDoseByPercent(double smpPercent, double mtxPercent) {
		return this.getPreviousDose().multiplyByPercentage(100+smpPercent, 100+mtxPercent);
	}

    public Date getLastVisitDate() {
        return new Date(this.startDate.getTimeInMillis() + TimeUnit.DAYS.toMillis(this.getDaysSinceStartAt(-1)));
    }
}
