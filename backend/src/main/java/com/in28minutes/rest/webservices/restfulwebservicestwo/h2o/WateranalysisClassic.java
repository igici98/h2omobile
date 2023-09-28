package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WateranalysisClassic {

	@Autowired
	private ProbedatenRepository probedatenRepository;

	// WASSERANALYSE SPEICHERN IN WASSERNANALYSE OBJEKT
	public WateranalysisClassicObject wateranalysisObject(String messstellenID, double temperaturNeu,
			double leitfaehigkeitNeu, double phwertNeu, double sauerstoffgehaltNeu, double sauerstoffsaettigungNeu) {

		// erstelle neues WateranalysisClassicObject
		WateranalysisClassicObject wasseranalysisObject = new WateranalysisClassicObject();

		wasseranalysisObject.temperatur = new WateranalysisClassicObjectResultValues();
		wasseranalysisObject.elektronischeLeitfaehigkeit = new WateranalysisClassicObjectResultValues();
		wasseranalysisObject.phWert = new WateranalysisClassicObjectResultValues();
		wasseranalysisObject.sauerstoffgehalt = new WateranalysisClassicObjectResultValues();
		wasseranalysisObject.sauerstoffsaettigung = new WateranalysisClassicObjectResultValues();

		// neue Untere Prüfschranken
		double temperaturUnterepruefschranke;
		double leitfaehigkeitUnterepruefschranke;
		double phwertUnterepruefschranke;
		double sauerstoffgehaltUnterepruefschranke;
		double sauerstoffsaettigungUnterepruefschranke;

		// neue Obere Prüfschranken
		double temperaturOberepruefschranke;
		double leitfaehigkeitOberepruefschranke;
		double phwertOberepruefschranke;
		double sauerstoffgehaltOberepruefschranke;
		double sauerstoffsaettigungOberepruefschranke;

		// Mittelwerte über alte Proben selber berechnet
		double temperaturMittelwert = calculateMittelwert("F117", messstellenID);
		double leitfaehigkeitMittelwert = calculateMittelwert("F118", messstellenID);
		double phwertMittelwert = calculateMittelwert("F119", messstellenID);
		double sauerstoffgehaltMittelwert = calculateMittelwert("F124", messstellenID);
		double sauerstoffsaettigungMittelwert = calculateMittelwert("F125", messstellenID);

		wasseranalysisObject.temperatur.setMittelwert(temperaturMittelwert);
		wasseranalysisObject.elektronischeLeitfaehigkeit.setMittelwert(leitfaehigkeitMittelwert);
		wasseranalysisObject.phWert.setMittelwert(phwertMittelwert);
		wasseranalysisObject.sauerstoffgehalt.setMittelwert(sauerstoffgehaltMittelwert);
		wasseranalysisObject.sauerstoffsaettigung.setMittelwert(sauerstoffsaettigungMittelwert);

		System.out.println("\n" + "temperaturMittelwert: " + temperaturMittelwert);
		System.out.println("\n" + "leitfaehigkeitMittelwert: " + leitfaehigkeitMittelwert);
		System.out.println("\n" + "phwertMittelwert: " + phwertMittelwert);
		System.out.println("\n" + "sauerstoffgehaltMittelwert: " + sauerstoffgehaltMittelwert);
		System.out.println("\n" + "sauerstoffsaettigungMittelwert: " + sauerstoffsaettigungMittelwert);

		double temperaturStandardabweichung = calculateStandardabweichung("F117", messstellenID);
		double leitfaehigkeitStandardabweichung = calculateStandardabweichung("F118", messstellenID);
		double phwertStandardabweichung = calculateStandardabweichung("F119", messstellenID);
		double sauerstoffgehaltStandardabweichung = calculateStandardabweichung("F124", messstellenID);
		double sauerstoffsaettigungStandardabweichung = calculateStandardabweichung("F125", messstellenID);

		System.out.println("\n" + "temperaturStandardabweichung: " + temperaturStandardabweichung);
		System.out.println("\n" + "leitfaehigkeitStandardabweichung: " + leitfaehigkeitStandardabweichung);
		System.out.println("\n" + "phwertStandardabweichung: " + phwertStandardabweichung);
		System.out.println("\n" + "sauerstoffgehaltStandardabweichung: " + sauerstoffgehaltStandardabweichung);
		System.out.println("\n" + "sauerstoffsaettigungStandardabweichung: " + sauerstoffsaettigungStandardabweichung);

		// ERGEBNISSE Temperatur
		temperaturUnterepruefschranke = calculateUnterepruefschranke(4, temperaturMittelwert,
				temperaturStandardabweichung);
		temperaturOberepruefschranke = calculateOberepruefschranke(4, temperaturMittelwert,
				temperaturStandardabweichung);

		System.out.print("\n" + "\n" + "ERGEBNISSE WASSERANALYSE: " + "\n");
		System.out.print("\n" + "Temperatur: " + "\n");
		System.out.print("Unterepruefschranke: " + temperaturUnterepruefschranke + "\n");
		System.out.print("Oberepruefschranke: " + temperaturOberepruefschranke + "\n");
		System.out.print("Temperatur neu: " + temperaturNeu + "\n");
		System.out.print("Parameter ist ok: "
				+ checkPruefstatus(temperaturNeu, temperaturUnterepruefschranke, temperaturOberepruefschranke) + "\n");

		// Set Temperaturwerte in Object
		wasseranalysisObject.temperatur.setUnterepruefschrankeValue(temperaturUnterepruefschranke);
		wasseranalysisObject.temperatur.setOberepruefschrankeValue(temperaturOberepruefschranke);
		wasseranalysisObject.temperatur.setParameterValue(temperaturNeu);
		wasseranalysisObject.temperatur
				.setIstOk(checkPruefstatus(temperaturNeu, temperaturUnterepruefschranke, temperaturOberepruefschranke));

		// ERGEBNISSE ELEKTR. LEITF. (bei 25°C) µS/cm
		leitfaehigkeitUnterepruefschranke = calculateUnterepruefschranke(4, leitfaehigkeitMittelwert,
				leitfaehigkeitStandardabweichung);
		leitfaehigkeitOberepruefschranke = calculateOberepruefschranke(4, leitfaehigkeitMittelwert,
				leitfaehigkeitStandardabweichung);

		System.out.print("\n" + "Elektronische Leitfähigkeit: " + "\n");
		System.out.print("Unterepruefschranke: " + leitfaehigkeitUnterepruefschranke + "\n");
		System.out.print("Oberepruefschranke: " + leitfaehigkeitOberepruefschranke + "\n");
		System.out.print("Elektronische Leitfähigkeit neu: " + leitfaehigkeitNeu + "\n");
		System.out.print("Parameter ist ok: " + checkPruefstatus(leitfaehigkeitNeu, leitfaehigkeitUnterepruefschranke,
				leitfaehigkeitOberepruefschranke) + "\n");

		// Set leitfaehigkeit in Object
		wasseranalysisObject.elektronischeLeitfaehigkeit.setUnterepruefschrankeValue(leitfaehigkeitUnterepruefschranke);
		wasseranalysisObject.elektronischeLeitfaehigkeit.setOberepruefschrankeValue(leitfaehigkeitOberepruefschranke);
		wasseranalysisObject.elektronischeLeitfaehigkeit.setParameterValue(leitfaehigkeitNeu);
		wasseranalysisObject.elektronischeLeitfaehigkeit.setIstOk(checkPruefstatus(leitfaehigkeitNeu,
				leitfaehigkeitUnterepruefschranke, leitfaehigkeitOberepruefschranke));

		// ERGEBNISSE PH-WERT
		phwertUnterepruefschranke = calculateUnterepruefschranke(4, phwertMittelwert, phwertStandardabweichung);
		phwertOberepruefschranke = calculateOberepruefschranke(4, phwertMittelwert, phwertStandardabweichung);

		System.out.print("\n" + "PH-Wert: " + "\n");
		System.out.print("Unterepruefschranke: " + phwertUnterepruefschranke + "\n");
		System.out.print("Oberepruefschranke: " + phwertOberepruefschranke + "\n");
		System.out.print("PH-Wert neu: " + phwertNeu + "\n");
		System.out.print("Parameter ist ok: "
				+ checkPruefstatus(phwertNeu, phwertUnterepruefschranke, phwertOberepruefschranke) + "\n");

		// Set Phwerte in Object
		wasseranalysisObject.phWert.setUnterepruefschrankeValue(phwertUnterepruefschranke);
		wasseranalysisObject.phWert.setOberepruefschrankeValue(phwertOberepruefschranke);
		wasseranalysisObject.phWert.setParameterValue(phwertNeu);
		wasseranalysisObject.phWert
				.setIstOk(checkPruefstatus(phwertNeu, phwertUnterepruefschranke, phwertOberepruefschranke));

		// ERGEBNISSE sauerstoffgehalt
		sauerstoffgehaltUnterepruefschranke = calculateUnterepruefschranke(4, sauerstoffgehaltMittelwert,
				sauerstoffgehaltStandardabweichung);
		sauerstoffgehaltOberepruefschranke = calculateOberepruefschranke(4, sauerstoffgehaltMittelwert,
				sauerstoffgehaltStandardabweichung);

		System.out.print("\n" + "Sauerstoffgehalt: " + "\n");
		System.out.print("Unterepruefschranke: " + sauerstoffgehaltUnterepruefschranke + "\n");
		System.out.print("Oberepruefschranke: " + sauerstoffgehaltOberepruefschranke + "\n");
		System.out.print("Sauerstoffgehalt neu: " + sauerstoffgehaltNeu + "\n");
		System.out.print("Parameter ist ok: " + checkPruefstatus(sauerstoffgehaltNeu,
				sauerstoffgehaltUnterepruefschranke, sauerstoffgehaltOberepruefschranke) + "\n");

		// Set sauerstoffgehalt in Object
		wasseranalysisObject.sauerstoffgehalt.setUnterepruefschrankeValue(sauerstoffgehaltUnterepruefschranke);
		wasseranalysisObject.sauerstoffgehalt.setOberepruefschrankeValue(sauerstoffgehaltOberepruefschranke);
		wasseranalysisObject.sauerstoffgehalt.setParameterValue(sauerstoffgehaltNeu);
		wasseranalysisObject.sauerstoffgehalt.setIstOk(checkPruefstatus(sauerstoffgehaltNeu,
				sauerstoffgehaltUnterepruefschranke, sauerstoffgehaltOberepruefschranke));

		// ERGEBNISSE sauerstoffsaettigung
		sauerstoffsaettigungUnterepruefschranke = calculateUnterepruefschranke(4, sauerstoffsaettigungMittelwert,
				sauerstoffsaettigungStandardabweichung);
		sauerstoffsaettigungOberepruefschranke = calculateOberepruefschranke(4, sauerstoffsaettigungMittelwert,
				sauerstoffsaettigungStandardabweichung);

		System.out.print("\n" + "Sauerstoffsättigung: " + "\n");
		System.out.print("Unterepruefschranke: " + sauerstoffsaettigungUnterepruefschranke + "\n");
		System.out.print("Oberepruefschranke: " + sauerstoffsaettigungOberepruefschranke + "\n");
		System.out.print("Sauerstoffsättigung neu: " + sauerstoffsaettigungNeu + "\n");
		System.out.print("Parameter ist ok: " + checkPruefstatus(sauerstoffsaettigungNeu,
				sauerstoffsaettigungUnterepruefschranke, sauerstoffsaettigungOberepruefschranke) + "\n");

		// Set sauerstoffsaettigung in Object
		wasseranalysisObject.sauerstoffsaettigung.setUnterepruefschrankeValue(sauerstoffsaettigungUnterepruefschranke);
		wasseranalysisObject.sauerstoffsaettigung.setOberepruefschrankeValue(sauerstoffsaettigungOberepruefschranke);
		wasseranalysisObject.sauerstoffsaettigung.setParameterValue(sauerstoffsaettigungNeu);
		wasseranalysisObject.sauerstoffsaettigung.setIstOk(checkPruefstatus(sauerstoffsaettigungNeu,
				sauerstoffsaettigungUnterepruefschranke, sauerstoffsaettigungOberepruefschranke));

		return wasseranalysisObject;

	}

	// WASSERANALYSE AUSGABE IN CONSOLE
	public void wateranalysis(String messstellenID, double temperaturNeu, double leitfaehigkeitNeu, double phwertNeu,
			double sauerstoffgehaltNeu, double sauerstoffsaettigungNeu) {
		// kommentieren - strg + shift + c

		// INFORMATION ÜBER VORORTPARAMETER
		// F117 WASSERTEMPERATUR °C 5,3
		// F118 ELEKTR. LEITF. (bei 25°C) µS/cm 678
		// F119 PH-WERT 8,04
		// F124 SAUERSTOFFGEHALT mg/l 12,4
		// F125 SAUERSTOFFSAETTIGUNG % 97

		/*
		 * WIRD JETZT ALS Parameter übergeben
		 * 
		 * // neue Prüfwerte der Parameter double temperaturNeu = 5.3; double
		 * leitfaehigkeitNeu = 678; double phwertNeu = 8.04; double sauerstoffgehaltNeu
		 * = 12.4; double sauerstoffsaettigungNeu = 97;
		 * 
		 */

		// neue Untere Prüfschranken
		double temperaturUnterepruefschranke;
		double leitfaehigkeitUnterepruefschranke;
		double phwertUnterepruefschranke;
		double sauerstoffgehaltUnterepruefschranke;
		double sauerstoffsaettigungUnterepruefschranke;

		// neue Obere Prüfschranken
		double temperaturOberepruefschranke;
		double leitfaehigkeitOberepruefschranke;
		double phwertOberepruefschranke;
		double sauerstoffgehaltOberepruefschranke;
		double sauerstoffsaettigungOberepruefschranke;

		// ALTE DATEN
		// Mittelwerte über alte Proben
		/*
		 * Echte Daten aus Excel double temperaturMittelwert =
		 * calculateMittelwert("F125", "8ae5e2f31a15a9e4011a15aa06f900a0"); double
		 * leitfaehigkeitMittelwert = 666.9247312; double phwertMittelwert =
		 * 8.092580645; double sauerstoffgehaltMittelwert = 10.66215054; double
		 * sauerstoffsaettigungMittelwert = 99.32150538;
		 */

		// Mittelwerte über alte Proben selber berechnet
		double temperaturMittelwert = calculateMittelwert("F117", messstellenID);
		double leitfaehigkeitMittelwert = calculateMittelwert("F118", messstellenID);
		double phwertMittelwert = calculateMittelwert("F119", messstellenID);
		double sauerstoffgehaltMittelwert = calculateMittelwert("F124", messstellenID);
		double sauerstoffsaettigungMittelwert = calculateMittelwert("F125", messstellenID);

		System.out.println("\n" + "temperaturMittelwert: " + temperaturMittelwert);
		System.out.println("\n" + "leitfaehigkeitMittelwert: " + leitfaehigkeitMittelwert);
		System.out.println("\n" + "phwertMittelwert: " + phwertMittelwert);
		System.out.println("\n" + "sauerstoffgehaltMittelwert: " + sauerstoffgehaltMittelwert);
		System.out.println("\n" + "sauerstoffsaettigungMittelwert: " + sauerstoffsaettigungMittelwert);

		// Standardabweichung über alte Proben
		/*
		 * double temperaturStandardabweichung = 7.000334761; double
		 * leitfaehigkeitStandardabweichung = 172.6896082; double
		 * phwertStandardabweichung = 0.166889076; double
		 * sauerstoffgehaltStandardabweichung = 1.538738084; double
		 * sauerstoffsaettigungStandardabweichung = 8.709113785;
		 */

		// Standardabweichung über alte Proben - Selbst berechnet

		double temperaturStandardabweichung = calculateStandardabweichung("F117", messstellenID);
		double leitfaehigkeitStandardabweichung = calculateStandardabweichung("F118", messstellenID);
		double phwertStandardabweichung = calculateStandardabweichung("F119", messstellenID);
		double sauerstoffgehaltStandardabweichung = calculateStandardabweichung("F124", messstellenID);
		double sauerstoffsaettigungStandardabweichung = calculateStandardabweichung("F125", messstellenID);

		System.out.println("\n" + "temperaturStandardabweichung: " + temperaturStandardabweichung);
		System.out.println("\n" + "leitfaehigkeitStandardabweichung: " + leitfaehigkeitStandardabweichung);
		System.out.println("\n" + "phwertStandardabweichung: " + phwertStandardabweichung);
		System.out.println("\n" + "sauerstoffgehaltStandardabweichung: " + sauerstoffgehaltStandardabweichung);
		System.out.println("\n" + "sauerstoffsaettigungStandardabweichung: " + sauerstoffsaettigungStandardabweichung);

		// ERGEBNISSE Temperatur
		temperaturUnterepruefschranke = calculateUnterepruefschranke(4, temperaturMittelwert,
				temperaturStandardabweichung);
		temperaturOberepruefschranke = calculateOberepruefschranke(4, temperaturMittelwert,
				temperaturStandardabweichung);

		System.out.print("\n" + "\n" + "ERGEBNISSE WASSERANALYSE: " + "\n");
		System.out.print("\n" + "Temperatur: " + "\n");
		System.out.print("Unterepruefschranke: " + temperaturUnterepruefschranke + "\n");
		System.out.print("Oberepruefschranke: " + temperaturOberepruefschranke + "\n");
		System.out.print("Temperatur neu: " + temperaturNeu + "\n");
		System.out.print("Parameter ist ok: "
				+ checkPruefstatus(temperaturNeu, temperaturUnterepruefschranke, temperaturOberepruefschranke) + "\n");

		// ERGEBNISSE ELEKTR. LEITF. (bei 25°C) µS/cm
		leitfaehigkeitUnterepruefschranke = calculateUnterepruefschranke(4, leitfaehigkeitMittelwert,
				leitfaehigkeitStandardabweichung);
		leitfaehigkeitOberepruefschranke = calculateOberepruefschranke(4, leitfaehigkeitMittelwert,
				leitfaehigkeitStandardabweichung);

		System.out.print("\n" + "Elektronische Leitfähigkeit: " + "\n");
		System.out.print("Unterepruefschranke: " + leitfaehigkeitUnterepruefschranke + "\n");
		System.out.print("Oberepruefschranke: " + leitfaehigkeitOberepruefschranke + "\n");
		System.out.print("Elektronische Leitfähigkeit neu: " + leitfaehigkeitNeu + "\n");
		System.out.print("Parameter ist ok: " + checkPruefstatus(leitfaehigkeitNeu, leitfaehigkeitUnterepruefschranke,
				leitfaehigkeitOberepruefschranke) + "\n");

		// ERGEBNISSE PH-WERT
		phwertUnterepruefschranke = calculateUnterepruefschranke(4, phwertMittelwert, phwertStandardabweichung);
		phwertOberepruefschranke = calculateOberepruefschranke(4, phwertMittelwert, phwertStandardabweichung);

		System.out.print("\n" + "PH-Wert: " + "\n");
		System.out.print("Unterepruefschranke: " + phwertUnterepruefschranke + "\n");
		System.out.print("Oberepruefschranke: " + phwertOberepruefschranke + "\n");
		System.out.print("PH-Wert neu: " + phwertNeu + "\n");
		System.out.print("Parameter ist ok: "
				+ checkPruefstatus(phwertNeu, phwertUnterepruefschranke, phwertOberepruefschranke) + "\n");

		// ERGEBNISSE Werte
		sauerstoffgehaltUnterepruefschranke = calculateUnterepruefschranke(4, sauerstoffgehaltMittelwert,
				sauerstoffgehaltStandardabweichung);
		sauerstoffgehaltOberepruefschranke = calculateOberepruefschranke(4, sauerstoffgehaltMittelwert,
				sauerstoffgehaltStandardabweichung);

		System.out.print("\n" + "Sauerstoffgehalt: " + "\n");
		System.out.print("Unterepruefschranke: " + sauerstoffgehaltUnterepruefschranke + "\n");
		System.out.print("Oberepruefschranke: " + sauerstoffgehaltOberepruefschranke + "\n");
		System.out.print("Sauerstoffgehalt neu: " + sauerstoffgehaltNeu + "\n");
		System.out.print("Parameter ist ok: " + checkPruefstatus(sauerstoffgehaltNeu,
				sauerstoffgehaltUnterepruefschranke, sauerstoffgehaltOberepruefschranke) + "\n");

		// ERGEBNISSE Werte
		sauerstoffsaettigungUnterepruefschranke = calculateUnterepruefschranke(4, sauerstoffsaettigungMittelwert,
				sauerstoffsaettigungStandardabweichung);
		sauerstoffsaettigungOberepruefschranke = calculateOberepruefschranke(4, sauerstoffsaettigungMittelwert,
				sauerstoffsaettigungStandardabweichung);

		System.out.print("\n" + "Sauerstoffsättigung: " + "\n");
		System.out.print("Unterepruefschranke: " + sauerstoffsaettigungUnterepruefschranke + "\n");
		System.out.print("Oberepruefschranke: " + sauerstoffsaettigungOberepruefschranke + "\n");
		System.out.print("Sauerstoffsättigung neu: " + sauerstoffsaettigungNeu + "\n");
		System.out.print("Parameter ist ok: " + checkPruefstatus(sauerstoffsaettigungNeu,
				sauerstoffsaettigungUnterepruefschranke, sauerstoffsaettigungOberepruefschranke) + "\n");

	}

	// get Mittelwert für ID ('F125') und messstelle
	// ('8ae5e2f31a15a9e4011a15aa06f900a0')
	private List<java.lang.String> getMittelwert() {

		List<String> werte = probedatenRepository.getRealwert();

		return werte;
	}

	// KLasse zum berechnen des Mittelwerts für einen Parameter einer Messstelle

	private double calculateMittelwert(String parameterNummer, String messstellenID) {

		double summe = 0.0;
		String str1;
		double dou1 = 0.0;
		double mittelwert = 0.0;

		System.out.println("\n" + "CALCULATE MITTELWERT FOR:");
		System.out.println("parameterNummer: " + parameterNummer + " AND " + "messstellenID: " + messstellenID);

		List<String> liste = probedatenRepository.getByMessstelleIDAndParameternummer(parameterNummer, messstellenID);

		// calculate Mittelwert
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i) != null) {
				str1 = liste.get(i).replaceAll(",", ".");
				dou1 = Double.valueOf(str1);
				summe = summe + dou1;
			}
		}

		mittelwert = summe / (liste.size() + 1);

		System.out.println("Mittelwert für: " + parameterNummer + " = " + mittelwert);
		return mittelwert;
	}

	private double calculateStandardabweichung(String parameterNummer, String messstellenID) {

		double summe = 0.0;
		String str1;
		double dou1 = 0.0;
		double quadrat = 0.0;
		double mittelwert = 0.0;
		double summeQuadratzahlen = 0.0;
		double varianz = 0.0;
		double standardabweichung = 0.0;

		System.out.println("\n" + "CALCULATE STANDARDABWEICHUNG FOR:");
		System.out.println("parameterNummer: " + parameterNummer + " AND " + "messstellenID: " + messstellenID);

		List<String> liste = probedatenRepository.getByMessstelleIDAndParameternummer(parameterNummer, messstellenID);

		// calculate Mittelwert
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i) != null) {
				str1 = liste.get(i).replaceAll(",", ".");
				dou1 = Double.valueOf(str1);
				summe = summe + dou1;
			}
		}

		mittelwert = summe / (liste.size() + 1);

		System.out.println("Mittelwert für: " + parameterNummer + " = " + mittelwert);

		// calculate summeQuadratzahlen
		for (int i = 0; i < liste.size(); i++) {
			if (liste.get(i) != null) {
				str1 = liste.get(i).replaceAll(",", ".");
				dou1 = Double.valueOf(str1);
				quadrat = (dou1 - mittelwert) * (dou1 - mittelwert);
			}

			// System.out.println(
			// "Quadratzahl: (" + dou1 + "-" + mittelwert + ") X (" + dou1 + "-" +
			// mittelwert + ") = " + quadrat);
			// System.out.println("summeQuadratzahlen: (" + summeQuadratzahlen + "+" +
			// quadrat + ")");

			summeQuadratzahlen = summeQuadratzahlen + quadrat;

			// System.out.println("=========" + summeQuadratzahlen);

		}

		// calculate varianz - list.size ist bereits n-1
		varianz = summeQuadratzahlen / liste.size();
		System.out.println("Varianz für: " + parameterNummer + " = " + varianz);

		// calculate Standardabweichung
		// wurzeln von varianz
		standardabweichung = Math.sqrt(varianz);

		System.out.println("Standardabweichung für: " + parameterNummer + " = " + standardabweichung);
		return standardabweichung;
	}

	private double calculateUnterepruefschranke(float pruefeinstellungLinks, double mittelwert,
			double standardabweichung) {

		// unterepruefschranke berechnen
		// MW - x-fache STABW (links)
		double unterepruefschranke = 0.0;

		unterepruefschranke = mittelwert - pruefeinstellungLinks * standardabweichung;

		return unterepruefschranke;
	}

	private double calculateOberepruefschranke(float pruefeinstellungRechts, double mittelwert,
			double standardabweichung) {
		// oberepruefschranke berechnen
		// MW + x-fache STABW (rechts)

		double oberepruefschranke = 0.0;

		oberepruefschranke = mittelwert + pruefeinstellungRechts * standardabweichung;

		return oberepruefschranke;

	}

	private boolean checkPruefstatus(double pruefwert, double unterepruefschranke, double oberepruefschranke) {
		// Prüfwert des Parameters muss innerhalb der Schranke sein

		if ((pruefwert >= unterepruefschranke) && (pruefwert <= oberepruefschranke))
			return true;
		else
			return false;

	}

	public WateranalysisOrganolepticObject calculateOccurrenceOrganoleptic(String messstellenId, String faerbung,
			String truebung, String geruch, String oelfilm, String schaumbildung) {
		// neues organoleptisches object erstellen
		WateranalysisOrganolepticObject organolepticValues = new WateranalysisOrganolepticObject();

		organolepticValues.faerbung = new WateranalysisOrganolepticObjectResults();
		organolepticValues.geruch = new WateranalysisOrganolepticObjectResults();
		organolepticValues.oelfilm = new WateranalysisOrganolepticObjectResults();
		organolepticValues.schaumbildung = new WateranalysisOrganolepticObjectResults();
		organolepticValues.truebung = new WateranalysisOrganolepticObjectResults();

		// set die values ins object
		organolepticValues.faerbung.setParameterValue(faerbung);
		organolepticValues.geruch.setParameterValue(geruch);
		organolepticValues.oelfilm.setParameterValue(oelfilm);
		organolepticValues.schaumbildung.setParameterValue(schaumbildung);
		organolepticValues.truebung.setParameterValue(truebung);

		// alle h2o proben holen für die bestimmte messstelle

//		F114	Geruch --Stringwert 1 bis 30
//		F115	Färbung -- Stringwert 3 stellen
//		F116	Trübung --Stringwert - 1 bis 4
//		F553	Ölfilm--Stringwert 1 oder 2
//		F854	schaum--Stringwert 1 oder 2

// check ob Färbung für diese messstelle bereits vorgekommen ist
		List<String> listFaerbung = probedatenRepository.getByMessstelleIDAndParameternummerOrganoleptic("F115",
				messstellenId);

		// liste durchlaufen und in String speichern
		String codesFaerbung = "\n";
		codesFaerbung = codesFaerbung + "\n";
		for (int i = 0; i < listFaerbung.size(); i++) {
			codesFaerbung = codesFaerbung + listFaerbung.get(i) + "-mal" + "\n";

			System.out.println(codesFaerbung);
		}
		String codesFaerbungClean = codesFaerbung.replace(",", " = ");
		organolepticValues.faerbung.setVorgefalleneCodes(codesFaerbungClean);
		// CHeck ob der code bereits aufgetreten ist
		Integer faerbungAuftreten = probedatenRepository
				.getByMessstelleIDAndParameternummerAndStringvalueOrganoleptic("F115", messstellenId, faerbung);
		if (faerbungAuftreten > 0)
			organolepticValues.faerbung.setBereitsVorgefallen(true);

// check ob truebung für diese messstelle bereits vorgekommen ist
		List<String> ListTruebung = probedatenRepository.getByMessstelleIDAndParameternummerOrganoleptic("F116",
				messstellenId);
		String codesTruebung = "\n";
		codesTruebung = codesTruebung + "\n";
		for (int i = 0; i < ListTruebung.size(); i++) {
			codesTruebung = codesTruebung + ListTruebung.get(i) + "-mal" + "\n";

			System.out.println(codesTruebung);
		}
		String codesTruebungClean = codesTruebung.replace(",", " = ");
		organolepticValues.truebung.setVorgefalleneCodes(codesTruebungClean);
		// CHeck ob der code bereits aufgetreten ist
		Integer truebungAuftreten = probedatenRepository
				.getByMessstelleIDAndParameternummerAndStringvalueOrganoleptic("F116", messstellenId, truebung);
		if (truebungAuftreten > 0)
			organolepticValues.truebung.setBereitsVorgefallen(true);

// check ob geruch für diese messstelle bereits vorgekommen ist
		List<String> listGeruch = probedatenRepository.getByMessstelleIDAndParameternummerOrganoleptic("F114",
				messstellenId);

		String codesGeruch = "\n";
		codesGeruch = codesGeruch + "\n";
		for (int i = 0; i < listGeruch.size(); i++) {
			codesGeruch = codesGeruch + listGeruch.get(i) + "-mal" + "\n";

			System.out.println(codesGeruch);
		}
		String codesGeruchClean = codesGeruch.replace(",", " = ");
		organolepticValues.geruch.setVorgefalleneCodes(codesGeruchClean);
		// CHeck ob der code bereits aufgetreten ist
		Integer geruchAuftreten = probedatenRepository
				.getByMessstelleIDAndParameternummerAndStringvalueOrganoleptic("F114", messstellenId, geruch);
		if (geruchAuftreten > 0)
			organolepticValues.geruch.setBereitsVorgefallen(true);

// check ob oelfilm für diese messstelle bereits vorgekommen ist
		List<String> listOelfilm = probedatenRepository.getByMessstelleIDAndParameternummerOrganoleptic("F553",
				messstellenId);
		String codesOelfilm = "\n";
		codesOelfilm = codesOelfilm + "\n";
		for (int i = 0; i < listOelfilm.size(); i++) {
			codesOelfilm = codesOelfilm + listOelfilm.get(i) + "-mal" + "\n";

			System.out.println(codesOelfilm);
		}
		String codesOelfilmClean = codesOelfilm.replace(",", " = ");
		organolepticValues.oelfilm.setVorgefalleneCodes(codesOelfilmClean);
		// CHeck ob ölfilm bereits aufgetreten ist
		Integer oelfilmAuftreten = probedatenRepository
				.getByMessstelleIDAndParameternummerAndStringvalueOrganoleptic("F553", messstellenId, "1");
		if (oelfilmAuftreten > 0)
			organolepticValues.oelfilm.setBereitsVorgefallen(true);

// check ob schaumbildung für diese messstelle bereits vorgekommen ist
		List<String> listSchaumbildung = probedatenRepository.getByMessstelleIDAndParameternummerOrganoleptic("F854",
				messstellenId);
		String codesSchaumbildung = "\n";
		codesSchaumbildung = codesSchaumbildung + "\n";
		for (int i = 0; i < listSchaumbildung.size(); i++) {
			codesSchaumbildung = codesSchaumbildung + listSchaumbildung.get(i) + "-mal" + "\n";

			System.out.println(codesSchaumbildung);
		}
		String codesSchaumbildungClean = codesSchaumbildung.replace(",", " = ");

		organolepticValues.schaumbildung.setVorgefalleneCodes(codesSchaumbildungClean);
		// CHeck ob der schaumbildung bereits aufgetreten ist
		Integer schaumbildungAuftreten = probedatenRepository
				.getByMessstelleIDAndParameternummerAndStringvalueOrganoleptic("F854", messstellenId, "1");
		if (schaumbildungAuftreten > 0)
			organolepticValues.schaumbildung.setBereitsVorgefallen(true);

		return organolepticValues;
	}

}
