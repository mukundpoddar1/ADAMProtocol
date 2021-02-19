package adamProtocol;

public class BloodCounts implements Comparable<BloodCounts>{
	enum Condition{
		SEVERE,
		MILD,
		TARGET,
		HIGH
	}
	public static final double BILLION = Math.pow(10, 9);
	public static final double NEUTROPHIL_UNIT = BILLION/1000;
	public static final double PLATELET_UNIT = BILLION;

	public final double neutrophilCount, plateletCount;
	public final Condition condition;
	
	public BloodCounts(double anc, double plc){
		neutrophilCount = anc;
		plateletCount = plc;
		condition = getCondition();
	}
	
	private Condition getCondition() {
		if(isAncOrPlcBelow(0.5*BILLION,50*BILLION))
			return Condition.SEVERE;
		else if(isAncOrPlcBelow(0.75*BILLION,75*BILLION))
			return Condition.MILD;
		else if(isAncOrPlcBelow(1.5*BILLION,0))
			return Condition.TARGET;
		else
			return Condition.HIGH;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof BloodCounts) {
			BloodCounts bloodCounts2 = (BloodCounts) arg0;
			return (this.neutrophilCount == bloodCounts2.neutrophilCount && 
					this.plateletCount == bloodCounts2.plateletCount);
		}
		return false;		
	}
	@Override
	public int compareTo(BloodCounts bloodCounts2) {
		if(isAncOrPlcBelow(bloodCounts2.neutrophilCount, bloodCounts2.plateletCount))
			return -1 ;
		else if(this.equals(bloodCounts2))
			return 0;
		return 1;
	}

	private boolean isAncOrPlcBelow(double anc, double plc) {
		if(neutrophilCount < anc || plateletCount < plc)
			return true;
		return false;
	}
}
