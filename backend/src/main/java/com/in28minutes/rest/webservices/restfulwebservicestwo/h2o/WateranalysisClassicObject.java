package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

public class WateranalysisClassicObject {

	public WateranalysisClassicObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	WateranalysisClassicObjectResultValues temperatur;
	WateranalysisClassicObjectResultValues elektronischeLeitfaehigkeit;
	WateranalysisClassicObjectResultValues phWert;
	WateranalysisClassicObjectResultValues sauerstoffgehalt;
	WateranalysisClassicObjectResultValues sauerstoffsaettigung;
	Boolean prologResultsAvailable = false;
	String prologResultsMessage = "false";
	WateranalysisOrganolepticObject organoleptischeWerte;

	@Override
	public String toString() {
		return "WateranalysisClassicObject [temperatur=" + temperatur + ", elektronischeLeitfaehigkeit="
				+ elektronischeLeitfaehigkeit + ", phWert=" + phWert + ", sauerstoffgehalt=" + sauerstoffgehalt
				+ ", sauerstoffsaettigung=" + sauerstoffsaettigung + ", prologResults=" + prologResultsAvailable
				+ ", prologResultsMessage=" + prologResultsMessage + ", getPrologResults()=" + getPrologResults() + "]";
	}

	public WateranalysisOrganolepticObject getOrganoleptischeWerte() {
		return organoleptischeWerte;
	}

	public void setOrganoleptischeWerte(WateranalysisOrganolepticObject organoleptischeWerte) {
		this.organoleptischeWerte = organoleptischeWerte;
	}

	public Boolean getPrologResultsAvailable() {
		return prologResultsAvailable;
	}

	public void setPrologResultsAvailable(Boolean prologResultsAvailable) {
		this.prologResultsAvailable = prologResultsAvailable;
	}

	public Boolean getPrologResults() {
		return prologResultsAvailable;
	}

	public void setPrologResults(Boolean prologResults) {
		this.prologResultsAvailable = prologResults;
	}

	public String getPrologResultsMessage() {
		return prologResultsMessage;
	}

	public void setPrologResultsMessage(String prologResultsMessage) {
		this.prologResultsMessage = prologResultsMessage;
	}

	public WateranalysisClassicObjectResultValues getTemperatur() {
		return temperatur;
	}

	public void setTemperatur(WateranalysisClassicObjectResultValues temperatur) {
		this.temperatur = temperatur;
	}

	public WateranalysisClassicObjectResultValues getElektronischeLeitfaehigkeit() {
		return elektronischeLeitfaehigkeit;
	}

	public void setElektronischeLeitfaehigkeit(WateranalysisClassicObjectResultValues elektronischeLeitfaehigkeit) {
		this.elektronischeLeitfaehigkeit = elektronischeLeitfaehigkeit;
	}

	public WateranalysisClassicObjectResultValues getPhWert() {
		return phWert;
	}

	public void setPhWert(WateranalysisClassicObjectResultValues phWert) {
		this.phWert = phWert;
	}

	public WateranalysisClassicObjectResultValues getSauerstoffgehalt() {
		return sauerstoffgehalt;
	}

	public void setSauerstoffgehalt(WateranalysisClassicObjectResultValues sauerstoffgehalt) {
		this.sauerstoffgehalt = sauerstoffgehalt;
	}

	public WateranalysisClassicObjectResultValues getSauerstoffsaettigung() {
		return sauerstoffsaettigung;
	}

	public void setSauerstoffsaettigung(WateranalysisClassicObjectResultValues sauerstoffsaettigung) {
		this.sauerstoffsaettigung = sauerstoffsaettigung;
	}

}
