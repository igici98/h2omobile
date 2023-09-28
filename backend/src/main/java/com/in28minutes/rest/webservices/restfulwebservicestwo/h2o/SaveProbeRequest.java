package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

public class SaveProbeRequest {

	H2oProbe probe;
	Probedaten temperaturProbedaten;
	Probedaten phwertProbedaten;
	Probedaten leitfaehigkeitProbedaten;
	Probedaten sauerstoffgehaltProbedaten;
	Probedaten sauerstoffsaettigungProbedaten;
	Probedaten faerbungProbedaten;
	Probedaten truebungProbedaten;
	Probedaten geruchProbedaten;
	Probedaten oelfilmProbedaten;
	Probedaten schaumbildungProbedaten;

	public Probedaten getFaerbungProbedaten() {
		return faerbungProbedaten;
	}

	public void setFaerbungProbedaten(Probedaten faerbungProbedaten) {
		this.faerbungProbedaten = faerbungProbedaten;
	}

	public Probedaten getTruebungProbedaten() {
		return truebungProbedaten;
	}

	public void setTruebungProbedaten(Probedaten truebungProbedaten) {
		this.truebungProbedaten = truebungProbedaten;
	}

	public Probedaten getGeruchProbedaten() {
		return geruchProbedaten;
	}

	public void setGeruchProbedaten(Probedaten geruchProbedaten) {
		this.geruchProbedaten = geruchProbedaten;
	}

	public Probedaten getOelfilmProbedaten() {
		return oelfilmProbedaten;
	}

	public void setOelfilmProbedaten(Probedaten oelfilmProbedaten) {
		this.oelfilmProbedaten = oelfilmProbedaten;
	}

	public Probedaten getSchaumbildungProbedaten() {
		return schaumbildungProbedaten;
	}

	public void setSchaumbildungProbedaten(Probedaten schaumbildungProbedaten) {
		this.schaumbildungProbedaten = schaumbildungProbedaten;
	}

	public H2oProbe getProbe() {
		return probe;
	}

	public void setProbe(H2oProbe probe) {
		this.probe = probe;
	}

	public Probedaten getTemperaturProbedaten() {
		return temperaturProbedaten;
	}

	public void setTemperaturProbedaten(Probedaten temperaturProbedaten) {
		this.temperaturProbedaten = temperaturProbedaten;
	}

	public Probedaten getPhwertProbedaten() {
		return phwertProbedaten;
	}

	public void setPhwertProbedaten(Probedaten phwertProbedaten) {
		this.phwertProbedaten = phwertProbedaten;
	}

	public Probedaten getLeitfaehigkeitProbedaten() {
		return leitfaehigkeitProbedaten;
	}

	public void setLeitfaehigkeitProbedaten(Probedaten leitfaehigkeitProbedaten) {
		this.leitfaehigkeitProbedaten = leitfaehigkeitProbedaten;
	}

	public Probedaten getSauerstoffgehaltProbedaten() {
		return sauerstoffgehaltProbedaten;
	}

	public void setSauerstoffgehaltProbedaten(Probedaten sauerstoffgehaltProbedaten) {
		this.sauerstoffgehaltProbedaten = sauerstoffgehaltProbedaten;
	}

	public Probedaten getSauerstoffsaettigungProbedaten() {
		return sauerstoffsaettigungProbedaten;
	}

	public void setSauerstoffsaettigungProbedaten(Probedaten sauerstoffsaettigungProbedaten) {
		this.sauerstoffsaettigungProbedaten = sauerstoffsaettigungProbedaten;
	}

}
