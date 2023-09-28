package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

public class WateranalysisOrganolepticObjectResults {
	private String parameterValue;
	private Boolean bereitsVorgefallen = false;
	private String vorgefalleneCodes = "";

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public Boolean getBereitsVorgefallen() {
		return bereitsVorgefallen;
	}

	public void setBereitsVorgefallen(Boolean bereitsVorgefallen) {
		this.bereitsVorgefallen = bereitsVorgefallen;
	}

	public String getVorgefalleneCodes() {
		return vorgefalleneCodes;
	}

	public void setVorgefalleneCodes(String vorgefalleneCodes) {
		this.vorgefalleneCodes = vorgefalleneCodes;
	}

}
