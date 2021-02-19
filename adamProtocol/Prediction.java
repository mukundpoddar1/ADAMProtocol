package adamProtocol;

import java.util.Calendar;

import adamProtocol.exceptions.OutOfBoundsDoseException;

public class Prediction {
	private Dose dose;
	private String comments;
	private Calendar appointmentDate;
	Patient testCase;

	public Prediction(Patient testCase) {
		dose = null;
		appointmentDate = null;
		comments = "";
		this.testCase = testCase;
	}

	public String getComments () {
		return comments;
	}

	public void setDose(Dose dose) throws OutOfBoundsDoseException {
		if (!testCase.isDoseWithinSafeLimit(dose))
			throw new OutOfBoundsDoseException(
					"The current dose of " + dose + " is above safe-limit (" + Dose.SAFE_LIMIT + " times) of BSA");
		this.dose = dose;
	}

	public void setOutOfBoundsDose(Dose dose) {
		this.dose = dose;
	}

	public void setAppointmentAfterDays(int days, Calendar today) {
		today.add(Calendar.DATE, days);
		appointmentDate = today;
	}

	public void addComments(String comments) {
		this.comments = comments + ", " + this.comments;
	}

	public Dose getDose() {
		return dose;
	}

}
