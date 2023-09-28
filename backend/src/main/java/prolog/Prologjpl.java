//https://jpl7.org/Tutorial
//https://jpl7.org/TutorialJavaCallsProlog

package prolog;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.jpl7.Query;
import org.jpl7.Term;

import com.in28minutes.rest.webservices.restfulwebservicestwo.h2o.WateranalysisClassicObject;

public class Prologjpl {

	public static String getOutput() {
		Query q1 = new Query(
				"consult('C:/Users/Ifangelium/Dropbox/00 WINF MASTER/Prototype/Prototype/Udemy/Full Stack Web Development with React and Spring Boot II/restful-web-services-two/src/main/java/prolog/db.pl')");

		String output = ("consult " + (q1.hasSolution() ? "succeeded" : "failed"));
		return output;
	}

	public static String checkIfPrologRuns() {

		Path abs = Paths.get("src/main/java/prolog/db.pl");
		String absolut = abs.toAbsolutePath().toString();
		String absolutNew = absolut.replace("\\", "/");

		System.out.println(absolutNew);

		// Query q1 = new Query(
		// "consult('C:/Users/Ifangelium/Dropbox/00 WINF
		// MASTER/Prototype/Prototype/Udemy/Full Stack Web Development with React and
		// Spring Boot II/restful-web-services-two/src/main/java/prolog/db.pl')");

		Query q1 = new Query("consult('" + absolutNew + "')");

		String t2 = "prolog_runs(yes).";
		Query q2 = new Query(t2);

		String output = ("Consult: " + (q1.hasSolution() ? "succeeded" : "failed") + " Prolog runs: "
				+ (q2.hasSolution() ? "true" : "false"));

		// Path abs = Paths.get("src/main/java/prolog/db.pl");

		// return abs.toAbsolutePath().toString();

		return output;
	}

	public static PrologAllResultsObject checkPrologRules(WateranalysisClassicObject waterObject) {

		// relativen pfad berechnen
		Path abs = Paths.get("src/main/java/prolog/db.pl");
		String absolut = abs.toAbsolutePath().toString();
		String absolutNew = absolut.replace("\\", "/");
		System.out.println(absolutNew);

		// Rückgabe prolog object mit allen Results
		PrologAllResultsObject prologAllResultsObject = new PrologAllResultsObject();

		// Check if sensor iced
		prologAllResultsObject.ruleIce = checkIcedSensor(waterObject, absolutNew);

		// Check if deviation exists
		// So den ersten Parameter für die Methode checkDeviation angeben temperatur;
		// elektronischeLeitfaehigkeit; phWert; sauerstoffgehalt; sauerstoffsaettigung;

		// Initialiseren als Schalter für Great deviation
		PrologResultObject prologResultGreatDeviation = new PrologResultObject();
		// Initialiseren als Schalter für Great deviation
		prologAllResultsObject.ruleGreatDeviation = new PrologResultObject();

		// Temperatur deviation berechnen und falls es eine
		prologAllResultsObject.ruleDeviationTemperatur = checkDeviation("temperatur",
				waterObject.getTemperatur().getParameterValue(),
				waterObject.getTemperatur().getOberepruefschrankeValue(),
				waterObject.getTemperatur().getUnterepruefschrankeValue(), absolutNew);
		// falls eine deviation entdeckt wurde wird gecheckt ob es eine große abweichung
		// check ob untere prüfschranke minus ist für die great deviation rule
		double greatUnterGrenze = 0.0;
		if (waterObject.getTemperatur().getUnterepruefschrankeValue() < 0)
			greatUnterGrenze = waterObject.getTemperatur().getUnterepruefschrankeValue() * 2;
		else
			greatUnterGrenze = waterObject.getTemperatur().getUnterepruefschrankeValue() / 2;

		if (prologAllResultsObject.ruleDeviationTemperatur.getRuleFired()) {
			prologResultGreatDeviation = Prologjpl.checkGreatDeviation("temperatur",
					waterObject.getTemperatur().getParameterValue(),
					waterObject.getTemperatur().getOberepruefschrankeValue() * 2, greatUnterGrenze, absolutNew);
			if (prologResultGreatDeviation.getRuleFired().booleanValue()) {
				prologAllResultsObject.getRuleGreatDeviation().setRuleFired(true);
				prologAllResultsObject.getRuleGreatDeviation()
						.setRuleMessage(prologResultGreatDeviation.getRuleMessage());
				System.out.println("BIN DRINNEN IM IF Temperatur!!!!");
			}
		}

		// elektronischeleitfaehigkeit deviation berechnen
		prologAllResultsObject.ruleDeviationElektronischeLeitfaehigkeit = checkDeviation("elektronischeleitfaehigkeit",
				waterObject.getElektronischeLeitfaehigkeit().getParameterValue(),
				waterObject.getElektronischeLeitfaehigkeit().getOberepruefschrankeValue(),
				waterObject.getElektronischeLeitfaehigkeit().getUnterepruefschrankeValue(), absolutNew);
		// falls eine deviation entdeckt wurde wird gecheckt ob es eine große abweichung
		// check ob untere prüfschranke minus ist für die great deviation rule
		double greatUnterGrenzeLeit = 0.0;
		if (waterObject.getElektronischeLeitfaehigkeit().getUnterepruefschrankeValue() < 0)
			greatUnterGrenzeLeit = waterObject.getElektronischeLeitfaehigkeit().getUnterepruefschrankeValue() * 2;
		else
			greatUnterGrenzeLeit = waterObject.getElektronischeLeitfaehigkeit().getUnterepruefschrankeValue() / 2;
		if (prologAllResultsObject.ruleDeviationElektronischeLeitfaehigkeit.getRuleFired()) {
			prologResultGreatDeviation = Prologjpl.checkGreatDeviation("elektronischeleitfaehigkeit",
					waterObject.getElektronischeLeitfaehigkeit().getParameterValue(),
					waterObject.getElektronischeLeitfaehigkeit().getOberepruefschrankeValue() * 2, greatUnterGrenzeLeit,
					absolutNew);
			if (prologResultGreatDeviation.getRuleFired().booleanValue()) {
				prologAllResultsObject.getRuleGreatDeviation().setRuleFired(true);
				prologAllResultsObject.getRuleGreatDeviation()
						.setRuleMessage(prologResultGreatDeviation.getRuleMessage());
				System.out.println("BIN DRINNEN IM IF elektronischeleitfaehigkeit!!!!");
			}
		}

		// phWert deviation berechnen
		prologAllResultsObject.ruleDeviationPhWert = checkDeviation("phwert",
				waterObject.getPhWert().getParameterValue(), waterObject.getPhWert().getOberepruefschrankeValue(),
				waterObject.getPhWert().getUnterepruefschrankeValue(), absolutNew);
		// falls eine deviation entdeckt wurde wird gecheckt ob es eine große abweichung
		if (prologAllResultsObject.ruleDeviationPhWert.getRuleFired()) {
			prologResultGreatDeviation = Prologjpl.checkGreatDeviation("phwert",
					waterObject.getPhWert().getParameterValue(),
					waterObject.getPhWert().getOberepruefschrankeValue() * 2,
					waterObject.getPhWert().getUnterepruefschrankeValue() / 2, absolutNew);
			if (prologResultGreatDeviation.getRuleFired().booleanValue()) {
				prologAllResultsObject.getRuleGreatDeviation().setRuleFired(true);
				prologAllResultsObject.getRuleGreatDeviation()
						.setRuleMessage(prologResultGreatDeviation.getRuleMessage());
				System.out.println("BIN DRINNEN IM IF phwert!!!!");
			}
		}

		// sauerstoffgehalt deviation berechnen
		prologAllResultsObject.ruleDeviationSauerstoffgehalt = checkDeviation("sauerstoffgehalt",
				waterObject.getSauerstoffgehalt().getParameterValue(),
				waterObject.getSauerstoffgehalt().getOberepruefschrankeValue(),
				waterObject.getSauerstoffgehalt().getUnterepruefschrankeValue(), absolutNew);
		// falls eine deviation entdeckt wurde wird gecheckt ob es eine große abweichung
		if (prologAllResultsObject.ruleDeviationSauerstoffgehalt.getRuleFired()) {
			prologResultGreatDeviation = Prologjpl.checkGreatDeviation("sauerstoffgehalt",
					waterObject.getSauerstoffgehalt().getParameterValue(),
					waterObject.getSauerstoffgehalt().getOberepruefschrankeValue() * 2,
					waterObject.getSauerstoffgehalt().getUnterepruefschrankeValue() / 2, absolutNew);
			if (prologResultGreatDeviation.getRuleFired().booleanValue()) {
				prologAllResultsObject.getRuleGreatDeviation().setRuleFired(true);
				prologAllResultsObject.getRuleGreatDeviation()
						.setRuleMessage(prologResultGreatDeviation.getRuleMessage());
				System.out.println("BIN DRINNEN IM IF sauerstoffgehalt!!!!");
			}
		}

		// sauerstoffsaettigung deviation berechnen
		prologAllResultsObject.ruleDeviationSauerstoffsaettigung = checkDeviation("sauerstoffsaettigung",
				waterObject.getSauerstoffsaettigung().getParameterValue(),
				waterObject.getSauerstoffsaettigung().getOberepruefschrankeValue(),
				waterObject.getSauerstoffsaettigung().getUnterepruefschrankeValue(), absolutNew);
		// falls eine deviation entdeckt wurde wird gecheckt ob es eine große abweichung
		if (prologAllResultsObject.ruleDeviationSauerstoffsaettigung.getRuleFired()) {
			prologResultGreatDeviation = Prologjpl.checkGreatDeviation("sauerstoffsaettigung",
					waterObject.getSauerstoffsaettigung().getParameterValue(),
					waterObject.getSauerstoffsaettigung().getOberepruefschrankeValue() * 2,
					waterObject.getSauerstoffsaettigung().getUnterepruefschrankeValue() / 2, absolutNew);
			if (prologResultGreatDeviation.getRuleFired().booleanValue()) {
				prologAllResultsObject.getRuleGreatDeviation().setRuleFired(true);
				prologAllResultsObject.getRuleGreatDeviation()
						.setRuleMessage(prologResultGreatDeviation.getRuleMessage());
				System.out.println("BIN DRINNEN IM IF sauerstoffsaettigung!!!!");
			}
		}

		return prologAllResultsObject;

	}

	public static PrologResultObject checkIcedSensor(WateranalysisClassicObject waterObject, String path) {

		// consult der Datei durchführen
		Query q1 = new Query("consult('" + path + "')");
		String output = ("Consult: " + (q1.hasSolution() ? "succeeded" : "failed"));

		System.out.println(output);

		//// ++++ DAS IST DER ERSTE CHECK WEGEN VEREISTEM SENSOR
		// hinzufuegen von neuem fact parameter
		Double temperaturValue = waterObject.getTemperatur().getParameterValue();
		Query q2 = new Query("assertz(parameter(temperatur," + temperaturValue + ")).");

		// Query q2 = new Query("assert(parameter(temperatur, 10.0)).");
		System.out.println("assert hat eine Solution: " + q2.hasSolution());

		// Query Prolog to display a message
		Query q3 = new Query("check_if_iced(temperatur, Messageice).");

		System.out.println("check_if_iced hat eine Solution: " + q3.hasSolution());

		// checkt ob die Query generell eine Lösung hat

		String messageTemperaturIcedString = "false";
		Boolean ruleFired = false;

		// https://jpl7.org/TutorialJavaCallsProlog
		if (q3.hasSolution()) {

			Term messageTemperaturIced = q3.oneSolution().get("Messageice");
			String messageTemperaturIcedStringMitZeichen = messageTemperaturIced.toString();
			messageTemperaturIcedString = messageTemperaturIcedStringMitZeichen.replaceAll("'", "");

			System.out.println(messageTemperaturIcedString);
			ruleFired = true;
		} else {
			System.out.println("No message found for Iced Sensor!");

			messageTemperaturIcedString = "No message found for Iced Sensor!";
			ruleFired = false;
		}

		q1.close();
		q2.close();
		q3.close();

		Query q4 = new Query("retract(parameter(temperatur," + temperaturValue + ")).");

		System.out.println("Enrfernen von temperatur " + q4.hasSolution());
		q4.close();

		PrologResultObject prologResultObjectInstance = new PrologResultObject();

		prologResultObjectInstance.setRuleFired(ruleFired);
		prologResultObjectInstance.setRuleMessage(messageTemperaturIcedString);

		return prologResultObjectInstance;

	}

	public static PrologResultObject checkDeviation(String parameterName, double parameterValue,
			double parameterObergrenze, double Untergrenze, String path) {

		// consult der Datei durchführen
		Query q1 = new Query("consult('" + path + "')");
		String output = ("Consult: " + (q1.hasSolution() ? "succeeded" : "failed"));

		System.out.println(output);

		//// ++++ DAS IST DER ERSTE CHECK WEGEN VEREISTEM SENSOR
		// hinzufuegen von neuem fact parameter

		String assertString = "parameter(" + parameterName + "," + parameterValue + "," + parameterObergrenze + ","
				+ Untergrenze + ")";

		Query q2 = new Query("assertz(" + assertString + ").");
		System.out.println("assertString: " + q2.toString());
		System.out.println("assert hat eine Solution: " + q2.hasSolution());

		// Query Prolog to display a message
		Query q3 = new Query("check_if_deviation(" + parameterName + ", Message).");

		System.out.println("check_if_deviation hat eine Solution: " + q3.hasSolution());

		// checkt ob die Query generell eine Lösung hat

		String messageDeviationString = "false";
		Boolean ruleFired = false;

		// https://jpl7.org/TutorialJavaCallsProlog
		if (q3.hasSolution()) {

			Term messageDeviation = q3.oneSolution().get("Message");
			String messageDeviationStringMitZeichen = messageDeviation.toString();
			messageDeviationString = messageDeviationStringMitZeichen.replaceAll("'", "");
			;
			System.out.println(messageDeviationString);
			ruleFired = true;
		} else {
			System.out.println("No devitation found!");
			messageDeviationString = "No devitation found for parameter: ";
			ruleFired = false;
		}

		q1.close();
		q2.close();
		q3.close();

		Query q4 = new Query("retract(" + assertString + ").");

		System.out.println("Enrfernen von Fact " + q4.hasSolution());
		q4.close();

		PrologResultObject prologResultObjectInstance = new PrologResultObject();

		prologResultObjectInstance.setRuleFired(ruleFired);
		prologResultObjectInstance.setRuleMessage(messageDeviationString);

		return prologResultObjectInstance;

	}

	public static String checkIteration(Integer iteration) {

		Path abs = Paths.get("src/main/java/prolog/db.pl");
		String absolut = abs.toAbsolutePath().toString();
		String absolutNew = absolut.replace("\\", "/");

		// consult der Datei durchführen
		Query q1 = new Query("consult('" + absolutNew + "')");
		String output = ("Consult: " + (q1.hasSolution() ? "succeeded" : "failed"));

		System.out.println(output);

		//// ++++ DAS IST DER ERSTE CHECK WEGEN VEREISTEM SENSOR
		// hinzufuegen von neuem fact parameter

		String assertString = "iteration(" + iteration + ")";

		Query q2 = new Query("assertz(" + assertString + ").");
		System.out.println("assertString: " + q2.toString());
		System.out.println("assert hat eine Solution: " + q2.hasSolution());

		// check_if_first_iteration(Iteration, Iterationmessage) :-
		// check_if_second_iteration(Iteration, Iterationmessagetwo) :-
		// check_if_third_iteration(Iteration, Iterationmessagethree) :-

		Query it1 = new Query("check_if_first_iteration(" + iteration + ", Iterationmessage).");
		System.out.println("check_if_first_iteration hat eine Solution: " + it1.hasSolution());

		Query it2 = new Query("check_if_second_iteration(" + iteration + ", Iterationmessagetwo).");
		System.out.println("check_if_second_iteration hat eine Solution: " + it2.hasSolution());

		Query it3 = new Query("check_if_third_iteration(" + iteration + ", Iterationmessagethree).");
		System.out.println("check_if_third_iteration hat eine Solution: " + it3.hasSolution());

		String messageDeviationString = "false";
		Boolean ruleFired = false;

		// https://jpl7.org/TutorialJavaCallsProlog
		if (it1.hasSolution()) {
			Term messageDeviation = it1.oneSolution().get("Iterationmessage");
			String messageDeviationStringMitZeichen = messageDeviation.toString();
			messageDeviationString = messageDeviationStringMitZeichen.replaceAll("'", "");
			System.out.println(messageDeviationString);
			ruleFired = true;
		}

		if (it2.hasSolution()) {
			Term messageDeviation = it2.oneSolution().get("Iterationmessagetwo");
			String messageDeviationStringMitZeichen = messageDeviation.toString();
			messageDeviationString = messageDeviationStringMitZeichen.replaceAll("'", "");
			System.out.println(messageDeviationString);
			ruleFired = true;
		}

		if (it3.hasSolution()) {
			Term messageDeviation = it3.oneSolution().get("Iterationmessagethree");
			String messageDeviationStringMitZeichen = messageDeviation.toString();
			messageDeviationString = messageDeviationStringMitZeichen.replaceAll("'", "");
			System.out.println(messageDeviationString);
			ruleFired = true;
		}

		q1.close();
		q2.close();
		it1.close();
		it2.close();
		it3.close();

		Query q3 = new Query("retract(" + assertString + ").");
		System.out.println("Enrfernen von Fact " + q3.hasSolution());
		q3.close();

		return messageDeviationString;

	}

	public static PrologResultObject checkGreatDeviation(String parameterName, double parameterValue,
			double deviationOver, double deviationUnder, String path) {

		// consult der Datei durchführen
		Query q1 = new Query("consult('" + path + "')");
		String output = ("Consult: " + (q1.hasSolution() ? "succeeded" : "failed"));

		System.out.println(output);

		//// ++++ DAS IST DER ERSTE CHECK WEGEN VEREISTEM SENSOR
		// hinzufuegen von neuem fact parameter

		String assertString = "parameter(" + parameterName + "," + parameterValue + "," + deviationOver + ","
				+ deviationUnder + ")";

		Query q2 = new Query("assertz(" + assertString + ").");
		System.out.println("assertString: " + q2.toString());
		System.out.println("assert hat eine Solution:  " + q2.hasSolution());

		// Query Prolog to display a message
		Query q3 = new Query("check_if_great_deviation(" + parameterName + ", Messagegreat).");

		System.out.println("check_if_great_deviation hat eine Solution: " + q3.hasSolution());

		// checkt ob die Query generell eine Lösung hat

		String messageDeviationString = "false";
		Boolean ruleFired = false;

		// https://jpl7.org/TutorialJavaCallsProlog
		if (q3.hasSolution()) {

			Term messageDeviation = q3.oneSolution().get("Messagegreat");
			String messageDeviationStringMitZeichen = messageDeviation.toString();
			messageDeviationString = messageDeviationStringMitZeichen.replaceAll("'", "");
			;
			System.out.println(messageDeviationString);
			ruleFired = true;
		} else {
			System.out.println("No devitation found!");
			messageDeviationString = "No devitation found for parameter GREAT: ";
			ruleFired = false;
		}

		q1.close();
		q2.close();
		q3.close();

		Query q4 = new Query("retract(" + assertString + ").");

		System.out.println("Enrfernen von Fact GREAT " + q4.hasSolution());
		q4.close();

		PrologResultObject prologResultObjectInstance = new PrologResultObject();

		prologResultObjectInstance.setRuleFired(ruleFired);
		prologResultObjectInstance.setRuleMessage(messageDeviationString);

		return prologResultObjectInstance;

	}

}
