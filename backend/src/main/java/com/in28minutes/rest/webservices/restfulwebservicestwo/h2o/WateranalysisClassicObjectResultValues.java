package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

public class WateranalysisClassicObjectResultValues {

	public WateranalysisClassicObjectResultValues() {
		super();
		// TODO Auto-generated constructor stub
	}

	private double parameterValue;
	private double unterepruefschrankeValue;
	private double oberepruefschrankeValue;
	private double mittelwert;
	private boolean istOk;

	public double getMittelwert() {
		return mittelwert;
	}

	public void setMittelwert(double mittelwert) {
		this.mittelwert = mittelwert;
	}

	public double getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(double parameterValue) {
		this.parameterValue = parameterValue;
	}

	public double getUnterepruefschrankeValue() {
		return unterepruefschrankeValue;
	}

	public void setUnterepruefschrankeValue(double unterepruefschrankeValue) {
		this.unterepruefschrankeValue = unterepruefschrankeValue;
	}

	public double getOberepruefschrankeValue() {
		return oberepruefschrankeValue;
	}

	public void setOberepruefschrankeValue(double oberepruefschrankeValue) {
		this.oberepruefschrankeValue = oberepruefschrankeValue;
	}

	public boolean getIstOk() {
		return istOk;
	}

	public void setIstOk(boolean istOk) {
		this.istOk = istOk;
	}

	@Override
	public String toString() {
		return "WateranalysisClassicObjectResultValues [parameterValue=" + parameterValue
				+ ", unterepruefschrankeValue=" + unterepruefschrankeValue + ", oberepruefschrankeValue="
				+ oberepruefschrankeValue + ", istOk=" + istOk + ", getParameterValue()=" + getParameterValue()
				+ ", getUnterepruefschrankeValue()=" + getUnterepruefschrankeValue() + ", getOberepruefschrankeValue()="
				+ getOberepruefschrankeValue() + ", getIstOk()=" + getIstOk() + "]";
	}

}
