package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Messstelle {

	@Id
	public String ID;
	private String MOBJWGEVID;
	private String MOBJNAME;
	private String MOBJART;
	private String AKTIVJN;
	private String MSKEDIT;
	private String MSKREMARK;
	private String ZUSTID;
	private String LASTUSERID;
	private Date LETZTEAENDERUNG;
	private Integer UPDATECOUNT;
	private String MOBJVERBORGEN;
	private String MSKKUERZEL;
	private String GZUEVJN;
	private String GZUEVJN_TEMP;
	private String PLKPRUEFEINSTELLUNG_ID;

	@OneToMany(targetEntity = H2oProbe.class, mappedBy = "messstelle")
	private List<H2oProbe> h2oprobe;

	protected Messstelle() {

	}

	public Messstelle(String ID, String MOBJWGEVID, String MOBJNAME, String MOBJART, String AKTIVJN, String MSKEDIT,
			String MSKREMARK, String ZUSTID, String LASTUSERID, Date LETZTEAENDERUNG, Integer UPDATECOUNT,
			String MOBJVERBORGEN, String MSKKUERZEL, String GZUEVJN, String GZUEVJN_TEMP, String PLKPRUEFEINSTELLUNG_ID

	) {
		super();

		this.ID = ID;
		this.MOBJWGEVID = MOBJWGEVID;
		this.MOBJNAME = MOBJNAME;
		this.MOBJART = MOBJART;
		this.AKTIVJN = AKTIVJN;
		this.MSKEDIT = MSKEDIT;
		this.MSKREMARK = MSKREMARK;
		this.ZUSTID = ZUSTID;
		this.LASTUSERID = LASTUSERID;
		this.LETZTEAENDERUNG = LETZTEAENDERUNG;
		this.UPDATECOUNT = UPDATECOUNT;
		this.MOBJVERBORGEN = MOBJVERBORGEN;
		this.MSKKUERZEL = MSKKUERZEL;
		this.GZUEVJN = GZUEVJN;
		this.GZUEVJN_TEMP = GZUEVJN_TEMP;
		this.PLKPRUEFEINSTELLUNG_ID = PLKPRUEFEINSTELLUNG_ID;

	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getMOBJWGEVID() {
		return MOBJWGEVID;
	}

	public void setMOBJWGEVID(String mOBJWGEVID) {
		MOBJWGEVID = mOBJWGEVID;
	}

	public String getMOBJNAME() {
		return MOBJNAME;
	}

	public void setMOBJNAME(String mOBJNAME) {
		MOBJNAME = mOBJNAME;
	}

	public String getMOBJART() {
		return MOBJART;
	}

	public void setMOBJART(String mOBJART) {
		MOBJART = mOBJART;
	}

	public String getAKTIVJN() {
		return AKTIVJN;
	}

	public void setAKTIVJN(String aKTIVJN) {
		AKTIVJN = aKTIVJN;
	}

	public String isMSKEDIT() {
		return MSKEDIT;
	}

	public void setMSKEDIT(String mSKEDIT) {
		MSKEDIT = mSKEDIT;
	}

	public String getMSKREMARK() {
		return MSKREMARK;
	}

	public void setMSKREMARK(String mSKREMARK) {
		MSKREMARK = mSKREMARK;
	}

	public String getZUSTID() {
		return ZUSTID;
	}

	public void setZUSTID(String zUSTID) {
		ZUSTID = zUSTID;
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

	public String isMOBJVERBORGEN() {
		return MOBJVERBORGEN;
	}

	public void setMOBJVERBORGEN(String mOBJVERBORGEN) {
		MOBJVERBORGEN = mOBJVERBORGEN;
	}

	public String getMSKKUERZEL() {
		return MSKKUERZEL;
	}

	public void setMSKKUERZEL(String mSKKUERZEL) {
		MSKKUERZEL = mSKKUERZEL;
	}

	public String isGZUEVJN() {
		return GZUEVJN;
	}

	public void setGZUEVJN(String gZUEVJN) {
		GZUEVJN = gZUEVJN;
	}

	public String getGZUEVJN_TEMP() {
		return GZUEVJN_TEMP;
	}

	public void setGZUEVJN_TEMP(String gZUEVJN_TEMP) {
		GZUEVJN_TEMP = gZUEVJN_TEMP;
	}

	public String getPLKPRUEFEINSTELLUNG_ID() {
		return PLKPRUEFEINSTELLUNG_ID;
	}

	public void setPLKPRUEFEINSTELLUNG_ID(String pLKPRUEFEINSTELLUNG_ID) {
		PLKPRUEFEINSTELLUNG_ID = pLKPRUEFEINSTELLUNG_ID;
	}

}
