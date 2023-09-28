package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Probedaten {

	@Id
	public String ID;
	private String PROID;
	// private String PARID;
	private String PROBEKZ;
	private String STRINGWERT;
	private String REALWERT;
	private String WERTBG;
	private String WERTNG;
	private Date DATUMWERT;
	private String LASTUSERID;
	private Date LETZTEAENDERUNG;
	private Integer UPDATECOUNT;
	private String VERTRAUENSBEREICH;
	private String FROMPARAMREF;
	private String MANUELLKORRIGIERTJN;
	private String BGNGWERT;
	private String QPARAMFLAGS;

	@ManyToOne
	@JoinColumn(name = "proid", insertable = false, updatable = false)
	@JsonBackReference
	private H2oProbe h2oprobe;

	@ManyToOne
	@JoinColumn(name = "parid")
	// @JsonBackReference
	private Parameter parameter;

	protected Probedaten() {

	}

	public Probedaten(String ID, String PROID, String PARID, String PROBEKZ, String STRINGWERT, String REALWERT,
			String WERTBG, String WERTNG, Date DATUMWERT, String LASTUSERID, Date LETZTEAENDERUNG, Integer UPDATECOUNT,
			String VERTRAUENSBEREICH, String FROMPARAMREF, String MANUELLKORRIGIERTJN, String BGNGWERT,
			String QPARAMFLAGS) {

		super();

		this.ID = ID;
		this.PROID = PROID;
		// this.PARID = PARID;
		this.PROBEKZ = PROBEKZ;
		this.STRINGWERT = STRINGWERT;
		this.REALWERT = REALWERT;
		this.WERTBG = WERTBG;
		this.WERTNG = WERTNG;
		this.DATUMWERT = DATUMWERT;
		this.LASTUSERID = LASTUSERID;
		this.LETZTEAENDERUNG = LETZTEAENDERUNG;
		this.UPDATECOUNT = UPDATECOUNT;
		this.VERTRAUENSBEREICH = VERTRAUENSBEREICH;
		this.FROMPARAMREF = FROMPARAMREF;
		this.MANUELLKORRIGIERTJN = MANUELLKORRIGIERTJN;
		this.BGNGWERT = BGNGWERT;
		this.QPARAMFLAGS = QPARAMFLAGS;

	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPROID() {
		return PROID;
	}

	public void setPROID(String pROID) {
		PROID = pROID;
	}
	/*
	 * public String getPARID() { return PARID; }
	 * 
	 * public void setPARID(String pARID) { PARID = pARID; }
	 */

	// parameter setter getter
	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public String getPROBEKZ() {
		return PROBEKZ;
	}

	public void setPROBEKZ(String pROBEKZ) {
		PROBEKZ = pROBEKZ;
	}

	public String getSTRINGWERT() {
		return STRINGWERT;
	}

	public void setSTRINGWERT(String sTRINGWERT) {
		STRINGWERT = sTRINGWERT;
	}

	public String getREALWERT() {
		return REALWERT;
	}

	public void setREALWERT(String rEALWERT) {
		REALWERT = rEALWERT;
	}

	public String getWERTBG() {
		return WERTBG;
	}

	public void setWERTBG(String wERTBG) {
		WERTBG = wERTBG;
	}

	public String getWERTNG() {
		return WERTNG;
	}

	public void setWERTNG(String wERTNG) {
		WERTNG = wERTNG;
	}

	public Date getDATUMWERT() {
		return DATUMWERT;
	}

	public void setDATUMWERT(Date dATUMWERT) {
		DATUMWERT = dATUMWERT;
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

	public Integer getUPDATECOUNT() {
		return UPDATECOUNT;
	}

	public void setUPDATECOUNT(Integer uPDATECOUNT) {
		UPDATECOUNT = uPDATECOUNT;
	}

	public String getVERTRAUENSBEREICH() {
		return VERTRAUENSBEREICH;
	}

	public void setVERTRAUENSBEREICH(String vERTRAUENSBEREICH) {
		VERTRAUENSBEREICH = vERTRAUENSBEREICH;
	}

	public String getFROMPARAMREF() {
		return FROMPARAMREF;
	}

	public void setFROMPARAMREF(String fROMPARAMREF) {
		FROMPARAMREF = fROMPARAMREF;
	}

	public String getMANUELLKORRIGIERTJN() {
		return MANUELLKORRIGIERTJN;
	}

	public void setMANUELLKORRIGIERTJN(String mANUELLKORRIGIERTJN) {
		MANUELLKORRIGIERTJN = mANUELLKORRIGIERTJN;
	}

	public String getBGNGWERT() {
		return BGNGWERT;
	}

	public void setBGNGWERT(String bGNGWERT) {
		BGNGWERT = bGNGWERT;
	}

	public String getQPARAMFLAGS() {
		return QPARAMFLAGS;
	}

	public void setQPARAMFLAGS(String qPARAMFLAGS) {
		QPARAMFLAGS = qPARAMFLAGS;
	}

}
