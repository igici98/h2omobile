package prolog;

public class PrologAllResultsObject {

	// Check if deviation exists

	// temperatur;
	// elektronischeLeitfaehigkeit;
	// phWert;
	// sauerstoffgehalt;
	// sauerstoffsaettigung;

	PrologResultObject ruleIce;
	PrologResultObject ruleGreatDeviation;
	PrologResultObject ruleDeviationTemperatur;
	PrologResultObject ruleDeviationElektronischeLeitfaehigkeit;
	PrologResultObject ruleDeviationPhWert;
	PrologResultObject ruleDeviationSauerstoffgehalt;
	PrologResultObject ruleDeviationSauerstoffsaettigung;

	public PrologResultObject getRuleGreatDeviation() {
		return ruleGreatDeviation;
	}

	public void setRuleGreatDeviation(PrologResultObject ruleGreatDeviation) {
		this.ruleGreatDeviation = ruleGreatDeviation;
	}

	public PrologResultObject getRuleIce() {
		return ruleIce;
	}

	public void setRuleIce(PrologResultObject ruleIce) {
		this.ruleIce = ruleIce;
	}

	public PrologResultObject getRuleDeviationTemperatur() {
		return ruleDeviationTemperatur;
	}

	public void setRuleDeviationTemperatur(PrologResultObject ruleDeviationTemperatur) {
		this.ruleDeviationTemperatur = ruleDeviationTemperatur;
	}

	public PrologResultObject getRuleDeviationElektronischeLeitfaehigkeit() {
		return ruleDeviationElektronischeLeitfaehigkeit;
	}

	public void setRuleDeviationElektronischeLeitfaehigkeit(
			PrologResultObject ruleDeviationElektronischeLeitfaehigkeit) {
		this.ruleDeviationElektronischeLeitfaehigkeit = ruleDeviationElektronischeLeitfaehigkeit;
	}

	public PrologResultObject getRuleDeviationPhWert() {
		return ruleDeviationPhWert;
	}

	public void setRuleDeviationPhWert(PrologResultObject ruleDeviationPhWert) {
		this.ruleDeviationPhWert = ruleDeviationPhWert;
	}

	public PrologResultObject getRuleDeviationSauerstoffgehalt() {
		return ruleDeviationSauerstoffgehalt;
	}

	public void setRuleDeviationSauerstoffgehalt(PrologResultObject ruleDeviationSauerstoffgehalt) {
		this.ruleDeviationSauerstoffgehalt = ruleDeviationSauerstoffgehalt;
	}

	public PrologResultObject getRuleDeviationSauerstoffsaettigung() {
		return ruleDeviationSauerstoffsaettigung;
	}

	public void setRuleDeviationSauerstoffsaettigung(PrologResultObject ruleDeviationSauerstoffsaettigung) {
		this.ruleDeviationSauerstoffsaettigung = ruleDeviationSauerstoffsaettigung;
	}

}
