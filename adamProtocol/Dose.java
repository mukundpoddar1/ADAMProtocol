package adamProtocol;


import adamProtocol.exceptions.IndivisibleDoseException;

public class Dose implements Comparable<Dose>{

	private static final double mtxStep = 2.5, smpStep = 25;
	public static final double SAFE_LIMIT = 1.60;
	public static final double STANDARD_INCREASE = 15;
	private double mtx, smp;

	public Dose(double mtx, double smp) throws IndivisibleDoseException {
		if(mtx % mtxStep !=0 || smp % smpStep != 0) {
			throw new IndivisibleDoseException(); 
		}
		this.mtx = mtx;
		this.smp = smp;
	}

	public static Dose roundOff(double mtx, double smp) {
		try {
			return new Dose(mtxStep*(Math.round(mtx/mtxStep)), smpStep*(Math.round(smp/smpStep)));
		} catch (IndivisibleDoseException e) {
			//This is never expected to execute because we are only creating rounded off doses by definition
			return null;
		}
	}
	/*This override is necessary as the default comparison just does a memory check
	* It does not check whether the values are really equal
	*/
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Dose) {
			Dose dose2 = (Dose) arg0;
			return (this.getMtx() == dose2.getMtx() && this.getSmp() == dose2.getSmp());
		}
		else
			return false;		
	}
	@Override
	public int compareTo(Dose dose2) {
		if (dose2 == null)
			return 1;
		if(this.getSmp() < dose2.getSmp() || this.getMtx() < dose2.getMtx())
			return -1 ;
		else if(this.equals(dose2))
			return 0;
		return 1;
	}

	public double getSmp() {
		return smp;
	}

	public double getMtx() {
		return mtx;
	}

	public Dose multiplyByPercentage(double smpPercent, double mtxPercent) {
		Dose nextDose = Dose.roundOff(this.getMtx() * mtxPercent/100,
								 	  this.getSmp()	* smpPercent/100);
		return nextDose;
	}

	public static Dose maximumOf(Dose dose1, Dose dose2) {
		if(dose1.compareTo(dose2)<0)
			return dose2;
		else
			return dose1;
	}

	public Dose addAmount(Dose hunderedPercentDose, double smpIncreaseAmount, double mtxIncreaseAmount) throws IndivisibleDoseException {
		Dose nextDose = Dose.roundOff(this.getMtx() + mtxIncreaseAmount,
								  	  this.getSmp() + smpIncreaseAmount);
		return nextDose;
	}

	public boolean is6mpPercentGreaterThanmtxPercent(Dose hunderedPercentDose) {
		double percentageStep = 0.125;
		double smpPercentage = percentageStep*Math.round(getSmp()/hunderedPercentDose.getSmp()/percentageStep);
		double mtxPercentage = percentageStep*Math.round(getMtx()/hunderedPercentDose.getMtx()/percentageStep);
		return (smpPercentage > mtxPercentage);
	}
}
