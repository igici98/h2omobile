package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "h2o_probe")
public class H2oProbe {

	@Id
	public String ID;
	private String PROBENR;
	private Date PROBEDATUM;
	private String TURNUSNR;
	private String ZRID;
	private String KATASTER;
	private String ZUSTID;
	// private String MSTID;
	private String TRANSID;
	private String PROBEKZ;
	private Date LETZTEAENDERUNG;
	private Integer UPDATECOUNT;
	private String LASTUSERID;
	private String PROBEREICH;
	private String PLKPRUEFUNGSTATUS;
	private String PROBENZUORDNUNGID;
	private String FREIGABESTATUS_ID;
	private String PLKPROTOKOLL;
	private String PLKKOMMENTAR;
	private String BIOLOGIE_QUALITAETSELEMENT;

	@OneToMany(targetEntity = Probedaten.class, mappedBy = "h2oprobe")
	private List<Probedaten> probedaten;

	@ManyToOne
	@JoinColumn(name = "MSTID")
	// @JsonBackReference
	@JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
	// hinzugefügt wegen fehler beim Speichern von neuer Probe:
	// https://stackoverflow.com/questions/52656517/no-serializer-found-for-class-org-hibernate-proxy-pojo-bytebuddy-bytebuddyinterc
	private Messstelle messstelle;

	protected H2oProbe() {

	}

	public H2oProbe(String ID, String PROBENR, Date PROBEDATUM, String TURNUSNR, String ZRID, String KATASTER,
			String ZUSTID, String MSTID, String TRANSID, String PROBEKZ, Date LETZTEAENDERUNG, Integer UPDATECOUNT,
			String LASTUSERID, String PROBEREICH, String PLKPRUEFUNGSTATUS, String PROBENZUORDNUNGID,
			String FREIGABESTATUS_ID, String PLKPROTOKOLL, String PLKKOMMENTAR, String BIOLOGIE_QUALITAETSELEMENT) {

		super();

		this.ID = ID;
		this.PROBENR = PROBENR;
		this.PROBEDATUM = PROBEDATUM;
		this.TURNUSNR = TURNUSNR;
		this.ZRID = ZRID;
		this.KATASTER = KATASTER;
		this.ZUSTID = ZUSTID;
		// this.MSTID = MSTID;
		this.TRANSID = TRANSID;
		this.PROBEKZ = PROBEKZ;
		this.LETZTEAENDERUNG = LETZTEAENDERUNG;
		this.UPDATECOUNT = UPDATECOUNT;
		this.LASTUSERID = LASTUSERID;
		this.PROBEREICH = PROBEREICH;
		this.PLKPRUEFUNGSTATUS = PLKPRUEFUNGSTATUS;
		this.PROBENZUORDNUNGID = PROBENZUORDNUNGID;
		this.FREIGABESTATUS_ID = FREIGABESTATUS_ID;
		this.PLKPROTOKOLL = PLKPROTOKOLL;
		this.PLKKOMMENTAR = PLKKOMMENTAR;
		this.BIOLOGIE_QUALITAETSELEMENT = BIOLOGIE_QUALITAETSELEMENT;

	}

	public String getBIOLOGIE_QUALITAETSELEMENT() {
		return BIOLOGIE_QUALITAETSELEMENT;
	}

	public void setBIOLOGIE_QUALITAETSELEMENT(String bIOLOGIE_QUALITAETSELEMENT) {
		BIOLOGIE_QUALITAETSELEMENT = bIOLOGIE_QUALITAETSELEMENT;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPROBENR() {
		return PROBENR;
	}

	public void setPROBENR(String pROBENR) {
		PROBENR = pROBENR;
	}

	public Date getPROBEDATUM() {
		return PROBEDATUM;
	}

	public void setPROBEDATUM(Date pROBEDATUM) {
		PROBEDATUM = pROBEDATUM;
	}

	public String getTURNUSNR() {
		return TURNUSNR;
	}

	public void setTURNUSNR(String tURNUSNR) {
		TURNUSNR = tURNUSNR;
	}

	public String getZRID() {
		return ZRID;
	}

	public void setZRID(String zRID) {
		ZRID = zRID;
	}

	public String getKATASTER() {
		return KATASTER;
	}

	public void setKATASTER(String kATASTER) {
		KATASTER = kATASTER;
	}

	public String getZUSTID() {
		return ZUSTID;
	}

	public void setZUSTID(String zUSTID) {
		ZUSTID = zUSTID;
	}

	/*
	 * public String getMSTID() { return MSTID; }
	 * 
	 * public void setMSTID(String mSTID) { MSTID = mSTID; }
	 */

	// getter and setter for Messtelle
	public Messstelle getMessstelle() {
		return messstelle;
	}

	public void setMessstelle(Messstelle messstelle) {
		this.messstelle = messstelle;
	}

	public String getTRANSID() {
		return TRANSID;
	}

	public void setTRANSID(String tRANSID) {
		TRANSID = tRANSID;
	}

	public String getPROBEKZ() {
		return PROBEKZ;
	}

	public void setPROBEKZ(String pROBEKZ) {
		PROBEKZ = pROBEKZ;
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

	public String getLASTUSERID() {
		return LASTUSERID;
	}

	public void setLASTUSERID(String lASTUSERID) {
		LASTUSERID = lASTUSERID;
	}

	public String getPROBEREICH() {
		return PROBEREICH;
	}

	public void setPROBEREICH(String pROBEREICH) {
		PROBEREICH = pROBEREICH;
	}

	public String getPLKPRUEFUNGSTATUS() {
		return PLKPRUEFUNGSTATUS;
	}

	public void setPLKPRUEFUNGSTATUS(String pLKPRUEFUNGSTATUS) {
		PLKPRUEFUNGSTATUS = pLKPRUEFUNGSTATUS;
	}

	public String getPROBENZUORDNUNGID() {
		return PROBENZUORDNUNGID;
	}

	public void setPROBENZUORDNUNGID(String pROBENZUORDNUNGID) {
		PROBENZUORDNUNGID = pROBENZUORDNUNGID;
	}

	public String getFREIGABESTATUS_ID() {
		return FREIGABESTATUS_ID;
	}

	public void setFREIGABESTATUS_ID(String fREIGABESTATUS_ID) {
		FREIGABESTATUS_ID = fREIGABESTATUS_ID;
	}

	public String getPLKPROTOKOLL() {
		return PLKPROTOKOLL;
	}

	public void setPLKPROTOKOLL(String pLKPROTOKOLL) {
		PLKPROTOKOLL = pLKPROTOKOLL;
	}

	public String getPLKKOMMENTAR() {
		return PLKKOMMENTAR;
	}

	public void setPLKKOMMENTAR(String pLKKOMMENTAR) {
		PLKKOMMENTAR = pLKKOMMENTAR;
	}

}
