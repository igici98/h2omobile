package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Parameter {

	@Id
	public String ID;
	private String PARNAME;
	private String PARFORMAT;
	private String PARNR;
	private String PARKLASSE;
	private String PARKUERZEL;
	private String PARCASNUMMER;
	private String PAREINHEIT;
	private String PARPFLICHTJN;
	private String PARVERFAHREN;
	private Integer PARMINBEST;
	private String VERWENDETLOVNAMEID;
	private String TRANSID;
	private String LASTUSERID;
	private Date LETZTEAENDERUNG;
	private String UPDATECOUNT;
	private String AKTIVJN;
	private String PARART;
	private String HCPLICHTPARAMETERJN;
	private String QFBEWERTUNGJN;
	private String SKIPPLKPRUEFUNGJN;
	private String EHPCCODE;
	private String EHPCBEZEICHNUNG;

	@OneToMany(targetEntity = Probedaten.class, mappedBy = "parameter")
	private List<Probedaten> probedaten;

	protected Parameter() {

	}

	public Parameter(String ID, String PARNAME, String PARFORMAT, String PARNR, String PARKLASSE, String PARKUERZEL,
			String PARCASNUMMER, String PAREINHEIT, String PARPFLICHTJN, String PARVERFAHREN, Integer PARMINBEST,
			String VERWENDETLOVNAMEID, String TRANSID, String LASTUSERID, Date LETZTEAENDERUNG, String UPDATECOUNT,
			String AKTIVJN, String PARART, String HCPLICHTPARAMETERJN, String QFBEWERTUNGJN, String SKIPPLKPRUEFUNGJN,
			String EHPCCODE, String EHPCBEZEICHNUNG

	) {

		super();

		this.ID = ID;
		this.PARNAME = PARNAME;
		this.PARFORMAT = PARFORMAT;
		this.PARNR = PARNR;
		this.PARKLASSE = PARKLASSE;
		this.PARKUERZEL = PARKUERZEL;
		this.PARCASNUMMER = PARCASNUMMER;
		this.PAREINHEIT = PAREINHEIT;
		this.PARPFLICHTJN = PARPFLICHTJN;
		this.PARVERFAHREN = PARVERFAHREN;
		this.PARMINBEST = PARMINBEST;
		this.VERWENDETLOVNAMEID = VERWENDETLOVNAMEID;
		this.TRANSID = TRANSID;
		this.LASTUSERID = LASTUSERID;
		this.LETZTEAENDERUNG = LETZTEAENDERUNG;
		this.UPDATECOUNT = UPDATECOUNT;
		this.AKTIVJN = AKTIVJN;
		this.PARART = PARART;
		this.HCPLICHTPARAMETERJN = HCPLICHTPARAMETERJN;
		this.QFBEWERTUNGJN = QFBEWERTUNGJN;
		this.SKIPPLKPRUEFUNGJN = SKIPPLKPRUEFUNGJN;
		this.EHPCCODE = EHPCCODE;
		this.EHPCBEZEICHNUNG = EHPCBEZEICHNUNG;

	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPARNAME() {
		return PARNAME;
	}

	public void setPARNAME(String pARNAME) {
		PARNAME = pARNAME;
	}

	public String getPARFORMAT() {
		return PARFORMAT;
	}

	public void setPARFORMAT(String pARFORMAT) {
		PARFORMAT = pARFORMAT;
	}

	public String getPARNR() {
		return PARNR;
	}

	public void setPARNR(String pARNR) {
		PARNR = pARNR;
	}

	public String getPARKLASSE() {
		return PARKLASSE;
	}

	public void setPARKLASSE(String pARKLASSE) {
		PARKLASSE = pARKLASSE;
	}

	public String getPARKUERZEL() {
		return PARKUERZEL;
	}

	public void setPARKUERZEL(String pARKUERZEL) {
		PARKUERZEL = pARKUERZEL;
	}

	public String getPARCASNUMMER() {
		return PARCASNUMMER;
	}

	public void setPARCASNUMMER(String pARCASNUMMER) {
		PARCASNUMMER = pARCASNUMMER;
	}

	public String getPAREINHEIT() {
		return PAREINHEIT;
	}

	public void setPAREINHEIT(String pAREINHEIT) {
		PAREINHEIT = pAREINHEIT;
	}

	public String getPARPFLICHTJN() {
		return PARPFLICHTJN;
	}

	public void setPARPFLICHTJN(String pARPFLICHTJN) {
		PARPFLICHTJN = pARPFLICHTJN;
	}

	public String getPARVERFAHREN() {
		return PARVERFAHREN;
	}

	public void setPARVERFAHREN(String pARVERFAHREN) {
		PARVERFAHREN = pARVERFAHREN;
	}

	public Integer getPARMINBEST() {
		return PARMINBEST;
	}

	public void setPARMINBEST(Integer pARMINBEST) {
		PARMINBEST = pARMINBEST;
	}

	public String getVERWENDETLOVNAMEID() {
		return VERWENDETLOVNAMEID;
	}

	public void setVERWENDETLOVNAMEID(String vERWENDETLOVNAMEID) {
		VERWENDETLOVNAMEID = vERWENDETLOVNAMEID;
	}

	public String getTRANSID() {
		return TRANSID;
	}

	public void setTRANSID(String tRANSID) {
		TRANSID = tRANSID;
	}

	public String getLASTUSERID() {
		return LASTUSERID;
	}

	public void setLASTUSERID(String lASTUSERID) {
		LASTUSERID = lASTUSERID;
	}

	public Date getLETZTEAENDERUNG() {
		return LETZTEAENDERUNG;
	}

	public void setLETZTEAENDERUNG(Date lETZTEAENDERUNG) {
		LETZTEAENDERUNG = lETZTEAENDERUNG;
	}

	public String getUPDATECOUNT() {
		return UPDATECOUNT;
	}

	public void setUPDATECOUNT(String uPDATECOUNT) {
		UPDATECOUNT = uPDATECOUNT;
	}

	public String getAKTIVJN() {
		return AKTIVJN;
	}

	public void setAKTIVJN(String aKTIVJN) {
		AKTIVJN = aKTIVJN;
	}

	public String getPARART() {
		return PARART;
	}

	public void setPARART(String pARART) {
		PARART = pARART;
	}

	public String getHCPLICHTPARAMETERJN() {
		return HCPLICHTPARAMETERJN;
	}

	public void setHCPLICHTPARAMETERJN(String hCPLICHTPARAMETERJN) {
		HCPLICHTPARAMETERJN = hCPLICHTPARAMETERJN;
	}

	public String getQFBEWERTUNGJN() {
		return QFBEWERTUNGJN;
	}

	public void setQFBEWERTUNGJN(String qFBEWERTUNGJN) {
		QFBEWERTUNGJN = qFBEWERTUNGJN;
	}

	public String getSKIPPLKPRUEFUNGJN() {
		return SKIPPLKPRUEFUNGJN;
	}

	public void setSKIPPLKPRUEFUNGJN(String sKIPPLKPRUEFUNGJN) {
		SKIPPLKPRUEFUNGJN = sKIPPLKPRUEFUNGJN;
	}

	public String getEHPCCODE() {
		return EHPCCODE;
	}

	public void setEHPCCODE(String eHPCCODE) {
		EHPCCODE = eHPCCODE;
	}

	public String getEHPCBEZEICHNUNG() {
		return EHPCBEZEICHNUNG;
	}

	public void setEHPCBEZEICHNUNG(String eHPCBEZEICHNUNG) {
		EHPCBEZEICHNUNG = eHPCBEZEICHNUNG;
	}

}
