package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImportData {

	@Autowired
	private MessstelleRepository messstelleRepository;

	@Autowired
	private H2oProbeRepository h2oProbeRepository;

	@Autowired
	private ProbedatenRepository probedatenRepository;

	@Autowired
	private ParameterRepository parameterRepository;

	// https://stackoverflow.com/questions/1516144/how-to-read-and-write-excel-file

	public List<Messstelle> executeImportMessstelle(MultipartFile file) {
		List<Messstelle> listMessstelle = new ArrayList<>();

		try {

			POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			System.out.println("Hier sind Reihen " + rows);

			int cols = 16; // messstelle hat 16 Collumns
			int tmp = 0;

			for (int r = 1; r < rows; r++) {
				Messstelle newMessstelle = new Messstelle();
				row = sheet.getRow(r);
				if (row != null) {
					for (int c = 0; c < cols; c++) {
						cell = row.getCell((short) c);

						switch (c) {
//							Collumnnumber 0 STRING ZELLENVALUE: ID
//							Collumnnumber 1 STRING ZELLENVALUE: MOBJWGEVID
//							Collumnnumber 2 STRING ZELLENVALUE: MOBJNAME
//							Collumnnumber 3 STRING ZELLENVALUE: MOBJART
//							Collumnnumber 4 STRING ZELLENVALUE: AKTIVJN
//							Collumnnumber 5 STRING ZELLENVALUE: MSKEDIT
//							Collumnnumber 6 STRING ZELLENVALUE: MSKREMARK
//							Collumnnumber 7 STRING ZELLENVALUE: ZUSTID
//							Collumnnumber 8 STRING ZELLENVALUE: LASTUSERID
//							Collumnnumber 9 STRING ZELLENVALUE: LETZTEAENDERUNG
//							Collumnnumber 10 STRING ZELLENVALUE: UPDATECOUNT
//							Collumnnumber 11 STRING ZELLENVALUE: MOBJVERBORGEN
//							Collumnnumber 12 STRING ZELLENVALUE: MSKKUERZEL
//							Collumnnumber 13 STRING ZELLENVALUE: GZUEVJN
//							Collumnnumber 14 STRING ZELLENVALUE: GZUEVJN_TEMP
//							Collumnnumber 15 STRING ZELLENVALUE: PLKPRUEFEINSTELLUNG_ID
						case 0:
							// setID
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setID(cell.getStringCellValue());
							} else {

								System.out.println("Case " + c + null);
								newMessstelle.setID(null);
							}
							break;
						case 1:
							// setMOBJWGEVID

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setMOBJWGEVID(cell.getStringCellValue());
							} else {
								System.out.println("Case " + c + null);
								newMessstelle.setMOBJWGEVID(null);
							}
							break;
						case 2:
							// setMOBJNAME
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setMOBJNAME(cell.getStringCellValue());
							} else {
								System.out.println("Case " + c + null);
								newMessstelle.setMOBJNAME(null);
							}
							break;
						case 3:
							// setMOBJART

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setMOBJART(cell.getStringCellValue());
							} else {

								System.out.println("Case " + c + null);
								newMessstelle.setMOBJART(null);
							}
							break;
						case 4:
							// AKTIVJN
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setAKTIVJN(cell.getStringCellValue());
							} else {

								System.out.println("Case " + c + null);
								newMessstelle.setAKTIVJN(null);
							}
							break;
						case 5:
							// MSKEDIT
							// null
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setMSKEDIT(cell.getStringCellValue());
							} else {
								newMessstelle.setMSKEDIT(null);
								System.out.println("Case " + c + null);
							}

							break;
						case 6:
							// MSKREMARK
							// null
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setMSKREMARK(cell.getStringCellValue());
							} else {
								newMessstelle.setMSKREMARK(null);
								System.out.println("Case " + c + null);
							}
							break;
						case 7:
							// ZUSTID
							// null
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setZUSTID(cell.getStringCellValue());
							} else {
								newMessstelle.setZUSTID(null);
								System.out.println("Case " + c + null);
							}
							break;
						case 8:
							// LASTUSERID
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setLASTUSERID(cell.getStringCellValue());
							} else {
								System.out.println("Case " + c + null);
								newMessstelle.setLASTUSERID(null);
							}

							break;
						case 9:
							// LETZTEAENDERUNG
							if (cell != null) {
								System.out.println("Case " + c + cell.getNumericCellValue());
								// Numeric
								newMessstelle.setLETZTEAENDERUNG(cell.getDateCellValue());
							} else {
								System.out.println("Case " + c + null);
								// Numeric
								newMessstelle.setLETZTEAENDERUNG(null);
							}
							break;
						case 10:
							// UPDATECOUNT
							if (cell != null) {
								System.out.println("Case " + c + cell.getNumericCellValue());
								// numeric
								double updateCount = cell.getNumericCellValue();
								int updateCountInt = (int) updateCount;
								newMessstelle.setUPDATECOUNT(updateCountInt);
							} else {
								System.out.println("Case " + c + null);
								newMessstelle.setUPDATECOUNT(null);
							}
							break;
						case 11:
							// MOBJVERBORGEN
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setMOBJVERBORGEN(cell.getStringCellValue());
							} else {
								System.out.println("Case " + c + null);
								newMessstelle.setMOBJVERBORGEN(null);
							}
							break;
						case 12:
							// MSKKUERZEL
							// null

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setMSKKUERZEL(cell.getStringCellValue());
							} else {
								newMessstelle.setMSKKUERZEL(null);
								System.out.println("Case " + c + null);
							}
							break;
						case 13:
							// GZUEVJN
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setGZUEVJN(cell.getStringCellValue());
							} else {
								System.out.println("Case " + c + null);
								newMessstelle.setGZUEVJN(null);
							}
							break;

						case 14:
							/// GZUEVJN_TEMP
							// null
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setGZUEVJN_TEMP(cell.getStringCellValue());
							} else {
								newMessstelle.setGZUEVJN_TEMP(null);

								System.out.println("Case " + c + null);
							}
							break;
						case 15:
							// PLKPRUEFEINSTELLUNG_ID
							// null

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newMessstelle.setPLKPRUEFEINSTELLUNG_ID(cell.getStringCellValue());
							} else {
								newMessstelle.setPLKPRUEFEINSTELLUNG_ID(null);
								System.out.println("Case " + c + null);
							}
							break;
						default:
							System.out.println("Case " + c + cell.getStringCellValue());
							break;
						}

					}
				}

				listMessstelle.add(newMessstelle);
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return listMessstelle;

	}

	public List<H2oProbe> executeImportH2o_probe(MultipartFile file) {

		List<H2oProbe> h2oProbeList = new ArrayList<>();

		try {

			POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			System.out.println("Hier sind Reihen " + rows);

			int cols = 20; // h2o_probe hat 20 Collumns
			int tmp = 0;

			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);
				H2oProbe newH2oProbe = new H2oProbe();
				if (row != null) {
					for (int c = 0; c < cols; c++) {
						cell = row.getCell((short) c);

//						ID 0 STRING : 8a20a26a5018fe2d01504182675879f8
//						PROBENR 1 STRING : 8FW1500048
//						PROBEDATUM 2 NUMERIC : 42221.0
//						TURNUSNR 3 STRING ZELLENVALUE: B545
//						ZRID 4 STRING ZELLENVALUE: null
//						KATASTER 5 STRING ZELLENVALUE: 0001
//						ZUSTID 6 STRING ZELLENVALUE: null
//						MSTID 7 STRING ZELLENVALUE: 8ae5e2f31a15a9e4011a15aa3bc403fe
//						TRANSID 8 STRING ZELLENVALUE: null
//						PROBEKZ 9 STRING ZELLENVALUE: null
//						LETZTEAENDERUNG 10 NUMERIC ZELLENVALUE: 42284.0
//						UPDATECOUNT 11 NUMERIC ZELLENVALUE: 0.0
//						LASTUSERID 12 STRING ZELLENVALUE: AT:VKZ:L8:B19
//						PROBEREICH 13 STRING ZELLENVALUE: FW
//						PLKPRUEFUNGSTATUS 14 STRING ZELLENVALUE: 13
//						PROBENZUORDNUNGID 15 STRING ZELLENVALUE: GZUEV
//						FREIGABESTATUS_ID 16 STRING ZELLENVALUE: null
//						PLKPROTOKOLL 17 STRING ZELLENVALUE: replaced
//						PLKKOMMENTAR 18 STRING ZELLENVALUE: replaced
//						BIOLOGIE_QUALITAETSELEMENT 19 STRING ZELLENVALUE: null

						switch (c) {

						case 0:
							// ID 0 STRING
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setID(cell.getStringCellValue());
							} else {
								newH2oProbe.setID(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 1:
							// PROBENR 1 STRING : 8FW1500048
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setPROBENR(cell.getStringCellValue());
							} else {
								newH2oProbe.setPROBENR(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 2:
							// PROBEDATUM 2 NUMERIC : 42221.0
							if (cell != null) {
								System.out.println("Case " + c + cell.getNumericCellValue());
								newH2oProbe.setPROBEDATUM(cell.getDateCellValue());
							} else {
								newH2oProbe.setPROBEDATUM(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 3:
							// TURNUSNR 3 STRING ZELLENVALUE: B545
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setTURNUSNR(cell.getStringCellValue());
							} else {
								newH2oProbe.setTURNUSNR(null);
								System.out.println("Case " + c + "null");

							}
							break;
						case 4:
							// ZRID 4 STRING ZELLENVALUE: null

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setZRID(cell.getStringCellValue());
							} else {
								newH2oProbe.setZRID(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 5:
							// KATASTER 5 STRING ZELLENVALUE: 0001

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setKATASTER(cell.getStringCellValue());
							} else {
								newH2oProbe.setKATASTER(null);
								System.out.println("Case " + c + "null");
							}

							break;
						case 6:
							// ZUSTID 6 STRING ZELLENVALUE: null
							// null
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setZUSTID(cell.getStringCellValue());
							} else {
								newH2oProbe.setZUSTID(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 7:
							// MSTID 7 STRING ZELLENVALUE: 8ae5e2f31a15a9e4011a15aa3bc403fe

							// wegen foreign key muss man messstelle holen und dann speichern
							Messstelle messstelle = messstelleRepository.getById(cell.getStringCellValue());
							newH2oProbe.setMessstelle(messstelle);

							System.out.println("Case " + c + cell.getStringCellValue());

							break;
						case 8:
							// TRANSID 8 STRING ZELLENVALUE: null

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setTRANSID(cell.getStringCellValue());
							} else {
								newH2oProbe.setTRANSID(null);
								System.out.println("Case " + c + "null");
							}

							break;
						case 9:
							// PROBEKZ 9 STRING ZELLENVALUE: null
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setPROBEKZ(cell.getStringCellValue());
							} else {
								newH2oProbe.setPROBEKZ(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 10:
							// LETZTEAENDERUNG 10 NUMERIC ZELLENVALUE: 42284.0
							if (cell != null) {
								System.out.println("Case " + c + cell.getNumericCellValue());
								newH2oProbe.setLETZTEAENDERUNG(cell.getDateCellValue());
							} else {
								newH2oProbe.setLETZTEAENDERUNG(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 11:
							// UPDATECOUNT 11 NUMERIC ZELLENVALUE: 0.0

							double updateCount = cell.getNumericCellValue();
							int updateCountInt = (int) updateCount;

							if (cell != null) {
								System.out.println("Case " + c + updateCountInt);
								newH2oProbe.setUPDATECOUNT(updateCountInt);
							} else {
								newH2oProbe.setUPDATECOUNT(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 12:
							// LASTUSERID 12 STRING ZELLENVALUE: AT:VKZ:L8:B19

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setLASTUSERID(cell.getStringCellValue());
							} else {
								newH2oProbe.setLASTUSERID(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 13:
							// PROBEREICH 13 STRING ZELLENVALUE: FW
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setPROBEREICH(cell.getStringCellValue());
							} else {
								newH2oProbe.setPROBEREICH(null);
								System.out.println("Case " + c + "null");
							}
							break;

						case 14:
							/// PLKPRUEFUNGSTATUS 14 STRING ZELLENVALUE: 13
							// null
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setPLKPRUEFUNGSTATUS(cell.getStringCellValue());
							} else {
								newH2oProbe.setPLKPRUEFUNGSTATUS(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 15:
							// PROBENZUORDNUNGID 15 STRING ZELLENVALUE: GZUEV

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setPROBENZUORDNUNGID(cell.getStringCellValue());
							} else {
								newH2oProbe.setPROBENZUORDNUNGID(null);
								System.out.println("Case " + c + "null");
							}

							break;
						case 16:
							// FREIGABESTATUS_ID 16 STRING ZELLENVALUE: null

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setFREIGABESTATUS_ID(cell.getStringCellValue());
							} else {
								newH2oProbe.setFREIGABESTATUS_ID(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 17:
							// PLKPROTOKOLL 17 STRING ZELLENVALUE: replaced

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setPLKPROTOKOLL(cell.getStringCellValue());
							} else {
								newH2oProbe.setPLKPROTOKOLL(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 18:
							// PLKKOMMENTAR 18 STRING ZELLENVALUE: replaced

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setPLKKOMMENTAR(cell.getStringCellValue());
							} else {
								newH2oProbe.setPLKKOMMENTAR(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 19:
							// BIOLOGIE_QUALITAETSELEMENT 19 STRING ZELLENVALUE: null

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newH2oProbe.setBIOLOGIE_QUALITAETSELEMENT(cell.getStringCellValue());
							} else {
								newH2oProbe.setBIOLOGIE_QUALITAETSELEMENT(null);
								System.out.println("Case " + c + "null");
							}
							break;
						default:
							System.out.println("Case " + c + cell.getStringCellValue());
							break;
						}

					}
				}

				h2oProbeList.add(newH2oProbe);
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return h2oProbeList;

	}

	public List<Probedaten> executeImportProbedaten(MultipartFile file) {

		List<Probedaten> ProbedatenList = new ArrayList<>();

		try {

			POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;

			int rows; // No of rows
			rows = sheet.getPhysicalNumberOfRows();

			System.out.println("Hier sind Reihen " + rows);

			int cols = 17; // h2o_probe hat 17 Collumns
			int tmp = 0;

			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);
				Probedaten newProbedaten = new Probedaten();
				if (row != null) {
					for (int c = 0; c < cols; c++) {
						cell = row.getCell((short) c);
//				1					AT:VKZ:L8:B19	10.7.2015	0					

//						0 ID - 8a20a26a5018fe2d0150418267587a08
//						1 PROID - 8a20a26a5018fe2d01504182675879f8
//						2 PARID - 8ae5e2f31a15860a011a158621de000f
//						3 PROBEKZ - null
//						4 STRINGWERT - 1 numeric
//						5 REALWERT - numeric
//						6 WERTBG - null
//						7 WERTNG - null
//						8 DATUMWERT - null
//						9 LASTUSERID - string
//						10 LETZTEAENDERUNG - datum
//						11 UPDATECOUNT - numeric
//						12 VERTRAUENSBEREICH - null
//						13 FROMPARAMREF - null
//						14 MANUELLKORRIGIERTJN - null
//						15 BGNGWERT - null
//						16 QPARAMFLAGS - null

						switch (c) {

						case 0:
							// ID 0 STRING
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newProbedaten.setID(cell.getStringCellValue());
							} else {
								newProbedaten.setID(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 1:
							// 1 PROID - 8a20a26a5018fe2d01504182675879f8
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newProbedaten.setPROID(cell.getStringCellValue());
							} else {
								newProbedaten.setPROID(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 2:
							// 2 PARID - 8ae5e2f31a15860a011a158621de000fPROBEDATUM 2 NUMERIC : 42221.0
							// wegen foreign key muss man den Paremter holen und dann speichern
							Parameter parameter = parameterRepository.getById(cell.getStringCellValue());
							newProbedaten.setParameter(parameter);
							System.out.println("Case " + c + cell.getStringCellValue());
							break;
						case 3:
							// 3 PROBEKZ - null
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newProbedaten.setPROBEKZ(cell.getStringCellValue());
							} else {
								newProbedaten.setPROBEKZ(null);
								System.out.println("Case " + c + "null");

							}
							break;
						case 4:
							// 4 STRINGWERT - 1

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());

								newProbedaten.setSTRINGWERT(cell.getStringCellValue());
							} else {
								newProbedaten.setSTRINGWERT(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 5:
							// 5 REALWERT - numeric

							if (cell != null) {

								CellType s = cell.getCellType();
								if (s.toString() == "STRING") {

									System.out.println("Case " + c + cell.getStringCellValue());

									String wertString = cell.getStringCellValue();
									String wertReplace = wertString.replace('.', ',');

									System.out.println("Case " + c + wertReplace);
									newProbedaten.setREALWERT(wertReplace);

								} else {

									System.out.println("Case " + c + cell.getNumericCellValue());
									double wertDouble = cell.getNumericCellValue();
									String wertString = String.valueOf(wertDouble);
									String wertReplace = wertString.replace('.', ',');
									newProbedaten.setREALWERT(wertReplace);

								}

							} else {
								newProbedaten.setREALWERT(null);
								System.out.println("Case " + c + "null");
							}

							break;
						case 6:
							// WERTBG - null
							// null
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newProbedaten.setWERTBG(cell.getStringCellValue());
							} else {
								newProbedaten.setWERTBG(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 7:
							// 7 WERTNG - null

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newProbedaten.setWERTNG(cell.getStringCellValue());
							} else {
								newProbedaten.setWERTNG(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 8:
							// DATUMWERT - null

							if (cell != null) {
								System.out.println("Case " + c + cell.getNumericCellValue());
								newProbedaten.setDATUMWERT(cell.getDateCellValue());
							} else {
								newProbedaten.setDATUMWERT(null);
								System.out.println("Case " + c + "null");
							}

							break;
						case 9:
							// LASTUSERID - string
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newProbedaten.setLASTUSERID(cell.getStringCellValue());
							} else {
								newProbedaten.setLASTUSERID(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 10:
							// LETZTEAENDERUNG 10 NUMERIC ZELLENVALUE: 42284.0
							if (cell != null) {
								System.out.println("Case " + c + cell.getNumericCellValue());
								newProbedaten.setLETZTEAENDERUNG(cell.getDateCellValue());
							} else {
								newProbedaten.setLETZTEAENDERUNG(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 11:
							// UPDATECOUNT 11 NUMERIC ZELLENVALUE: 0.0

							double updateCount = cell.getNumericCellValue();
							int updateCountInt = (int) updateCount;

							if (cell != null) {
								System.out.println("Case " + c + updateCountInt);
								newProbedaten.setUPDATECOUNT(updateCountInt);
							} else {
								newProbedaten.setUPDATECOUNT(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 12:
							// VERTRAUENSBEREICH

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newProbedaten.setVERTRAUENSBEREICH(cell.getStringCellValue());
							} else {
								newProbedaten.setVERTRAUENSBEREICH(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 13:
							// FROMPARAMREF
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newProbedaten.setFROMPARAMREF(cell.getStringCellValue());
							} else {
								newProbedaten.setFROMPARAMREF(null);
								System.out.println("Case " + c + "null");
							}
							break;

						case 14:
							/// MANUELLKORRIGIERTJN
							// null
							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newProbedaten.setMANUELLKORRIGIERTJN(cell.getStringCellValue());
							} else {
								newProbedaten.setMANUELLKORRIGIERTJN(null);
								System.out.println("Case " + c + "null");
							}
							break;
						case 15:
							// PROBENZUORDNUNGID 15 STRING ZELLENVALUE: GZUEV

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newProbedaten.setBGNGWERT(cell.getStringCellValue());
							} else {
								newProbedaten.setBGNGWERT(null);
								System.out.println("Case " + c + "null");
							}

							break;
						case 16:
							// QPARAMFLAGS

							if (cell != null) {
								System.out.println("Case " + c + cell.getStringCellValue());
								newProbedaten.setQPARAMFLAGS(cell.getStringCellValue());
							} else {
								newProbedaten.setQPARAMFLAGS(null);
								System.out.println("Case " + c + "null");
							}
							break;

						default:
							System.out.println("Case " + c + cell.getStringCellValue());
							break;
						}

					}
				}

				ProbedatenList.add(newProbedaten);
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
		return ProbedatenList;

	}

}
