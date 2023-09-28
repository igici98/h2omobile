package com.in28minutes.rest.webservices.restfulwebservicestwo.h2o;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import prolog.PrologAllResultsObject;
import prolog.Prologjpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class H2oResource {

	@Autowired
	private MessstelleRepository messstelleRepository;

	@Autowired
	private H2oProbeRepository h2oProbeRepository;

	@Autowired
	private ProbedatenRepository probedatenRepository;

	@Autowired
	private ParameterRepository parameterRepository;

	@Autowired
	private WateranalysisClassic wateranalysisClassic;

	@Autowired
	private ImportData importData;

	// Messstellen Links
	// get all messstellen
	@GetMapping("/messstellen")
	public List<Messstelle> getAllMesstellen() {

		return messstelleRepository.findAll();

	}

	// get by messstellenID
	@GetMapping("/messstellen/{messstellenid}")
	public List<Messstelle> getByMesstellenID(@PathVariable String messstellenid) {

		return messstelleRepository.getByID(messstellenid);

	}

	// H2OProbe
	// get all Proben

	@GetMapping("/proben/{probenid}")
	public List<H2oProbe> getByProbenID(@PathVariable String probenid) {

		return h2oProbeRepository.getByID(probenid);

	}

	@GetMapping("/proben")
	public List<H2oProbe> getAllProben() {

		return h2oProbeRepository.findAll();

	}

	// Probedaten
	// get all ProbeDATEN
	@GetMapping("/probedaten")
	public List<Probedaten> getAllProbedaten() {

		return probedatenRepository.findAll();

	}

	// Create Probedaten
	// createProbedaten(id, h2oProbe) {
	// return axios.post(`${API_URL}/messstellen/${id}/proben`, h2oProbe)
	// }
	@PostMapping("/messstellen/{id}/proben")
	public ResponseEntity<H2oProbe> addProbe(@PathVariable String id, @RequestBody SaveProbeRequest saveProbe) {
		// erstellen letzte Änderung für probe und für die probedaten
		saveProbe.probe.setLETZTEAENDERUNG(new Date());
		saveProbe.temperaturProbedaten.setLETZTEAENDERUNG(new Date());
		saveProbe.phwertProbedaten.setLETZTEAENDERUNG(new Date());
		saveProbe.leitfaehigkeitProbedaten.setLETZTEAENDERUNG(new Date());
		saveProbe.sauerstoffgehaltProbedaten.setLETZTEAENDERUNG(new Date());
		saveProbe.sauerstoffsaettigungProbedaten.setLETZTEAENDERUNG(new Date());

		// messtelle holen wegen Foreign Key und danach die Messtelle setzen
		Messstelle messstelle = messstelleRepository.getById(id);
		saveProbe.probe.setMessstelle(messstelle);
		H2oProbe createH2oProbe = h2oProbeRepository.save(saveProbe.probe);

		// Temperatur Parameter holen für Temperatur Foreign Key und danach die
		// parameter setzen
		Parameter temperaturParameter = parameterRepository.getById("8ae5e2f31a15860a011a1586224c0012");
		saveProbe.temperaturProbedaten.setParameter(temperaturParameter);
		probedatenRepository.save(saveProbe.temperaturProbedaten);

		// ph Parameter holen für Foreign Key und danach die parameter setzen
		Parameter phParameter = parameterRepository.getById("8ae5e2f31a15860a011a1586227b0014");
		saveProbe.phwertProbedaten.setParameter(phParameter);
		probedatenRepository.save(saveProbe.phwertProbedaten);

		// leitfähigkeit Parameter holen für Foreign Key und danach die parameter setzen
		Parameter leitfaehigkeitParameter = parameterRepository.getById("8ae5e2f31a15860a011a1586225b0013");
		saveProbe.leitfaehigkeitProbedaten.setParameter(leitfaehigkeitParameter);
		probedatenRepository.save(saveProbe.leitfaehigkeitProbedaten);

		// sauerstoffgehalt Parameter holen für Foreign Key und danach die parameter
		// setzen
		Parameter sauerstoffgehaltParameter = parameterRepository.getById("8ae5e2f31a15860a011a158623070019");
		saveProbe.sauerstoffgehaltProbedaten.setParameter(sauerstoffgehaltParameter);
		probedatenRepository.save(saveProbe.sauerstoffgehaltProbedaten);

		// sauerstoffsaettigung Parameter holen für Foreign Key und danach die parameter
		// setzen
		Parameter sauerstoffsaettigungParameter = parameterRepository.getById("8ae5e2f31a15860a011a15862317001a");
		saveProbe.sauerstoffsaettigungProbedaten.setParameter(sauerstoffsaettigungParameter);
		probedatenRepository.save(saveProbe.sauerstoffsaettigungProbedaten);

//		8ae5e2f31a15860a011a1586224c0012	WASSERTEMPERATUR °C
//		8ae5e2f31a15860a011a1586225b0013	ELEKTR. LEITF. (bei 25°C) µS/cm
//		8ae5e2f31a15860a011a1586227b0014	PH-WERT
//		8ae5e2f31a15860a011a158623070019	SAUERSTOFFGEHALT mg/l
//		8ae5e2f31a15860a011a15862317001a	SAUERSTOFFSAETTIGUNG %

//		8ae5e2f31a15860a011a1586222c0011	F116	Trübung
//		8ae5e2f31a15860a011a158621de000f	F114	Geruch 
//		8ae5e2f31a15860a011a15864bcc01c6	F553	Ölfilm
//		8ae5e2f31a15860a011a1586220d0010	F115	Färbung
//		4028f0f02b8a3a5b012b9b3f99ae0ae6	F854	schaum

		// Trübung Parameter holen für FK danach die parameter setzen
		Parameter truebungParameter = parameterRepository.getById("8ae5e2f31a15860a011a1586222c0011");
		saveProbe.truebungProbedaten.setParameter(truebungParameter);
		probedatenRepository.save(saveProbe.truebungProbedaten);

		// Geruch Parameter holen für FK danach die parameter setzen
		Parameter geruchParameter = parameterRepository.getById("8ae5e2f31a15860a011a158621de000f");
		saveProbe.geruchProbedaten.setParameter(geruchParameter);
		probedatenRepository.save(saveProbe.geruchProbedaten);

		// Ölfilm Parameter holen für FK danach die parameter setzen
		Parameter oelfilmParameter = parameterRepository.getById("8ae5e2f31a15860a011a15864bcc01c6");
		saveProbe.oelfilmProbedaten.setParameter(oelfilmParameter);
		probedatenRepository.save(saveProbe.oelfilmProbedaten);

		// Faerbung Parameter holen für FK danach die parameter setzen
		Parameter faerbungParameter = parameterRepository.getById("8ae5e2f31a15860a011a1586220d0010");
		saveProbe.faerbungProbedaten.setParameter(faerbungParameter);
		probedatenRepository.save(saveProbe.faerbungProbedaten);

		// Schaumbildung Parameter holen für FK danach die parameter setzen
		Parameter schaumbildungParameter = parameterRepository.getById("4028f0f02b8a3a5b012b9b3f99ae0ae6");
		saveProbe.schaumbildungProbedaten.setParameter(schaumbildungParameter);
		probedatenRepository.save(saveProbe.schaumbildungProbedaten);
		return new ResponseEntity<H2oProbe>(createH2oProbe, HttpStatus.CREATED);

	}

	// Parameter
	// get all Parameter
	@GetMapping("/parameter")
	public List<Parameter> getAllParameter() {

		return parameterRepository.findAll();

	}

	// get proben (h20 probe) by Messstellen ID
	@GetMapping("/messstellen/{mstid}/proben")
	public List<H2oProbe> getAllProbenByMessstelleID(@PathVariable String mstid) {

		// System.out.println(mstid);

		return h2oProbeRepository.getByMessstelleID(mstid);

	}

	// get parameter & probedaten für eine Probe einer Messstelle
	@GetMapping("/messstellen/{mstid}/proben/{probenid}/parameter")
	public List<Probedaten> getProbedatenParameter(@PathVariable String mstid, @PathVariable String probenid) {

		return probedatenRepository.getByPROID(probenid);

	}

	// WASSERANALYSE
	// nur in console schreiben

	/*
	 * @GetMapping(
	 * "/wasseranalyse/{messstellenId}/{temperaturNeu}/{leitfaehigkeitNeu}/{phwertNeu}/{sauerstoffgehaltNeu}/{sauerstoffsaettigungNeu}")
	 * public void getWateranalysis(@PathVariable String
	 * messstellenId, @PathVariable double temperaturNeu,
	 * 
	 * @PathVariable double leitfaehigkeitNeu, @PathVariable double phwertNeu,
	 * 
	 * @PathVariable double sauerstoffgehaltNeu, @PathVariable double
	 * sauerstoffsaettigungNeu) {
	 * 
	 * System.out.println("\n" + "++MESSSTELLEN-ID: " + messstellenId);
	 * System.out.println("\n" + "++TEMPERATUR: " + temperaturNeu);
	 * System.out.println("\n" + "++LEITFÄHIGKEIT: " + leitfaehigkeitNeu);
	 * System.out.println("\n" + "++PH-WERT: " + phwertNeu); System.out.println("\n"
	 * + "++SAUERSTOFFGEHALT: " + sauerstoffgehaltNeu); System.out.println("\n" +
	 * "++SAUERSTSÄTTIGUNG: " + sauerstoffsaettigungNeu);
	 * 
	 * wateranalysisClassic.wateranalysis(messstellenId, temperaturNeu,
	 * leitfaehigkeitNeu, phwertNeu, sauerstoffgehaltNeu, sauerstoffsaettigungNeu);
	 * 
	 * }
	 */

	// Object zurückliefern an frontend mit results schreiben
	@GetMapping("/wasseranalyse/{messstellenId}/{temperaturNeu}/{leitfaehigkeitNeu}/{phwertNeu}/{sauerstoffgehaltNeu}/{sauerstoffsaettigungNeu}/{iteration}/{faerbung}/{truebung}/{geruch}/{oelfilm}/{schaumbildung}")
	public WateranalysisClassicObject getWateranalysisObject(@PathVariable String messstellenId,
			@PathVariable double temperaturNeu, @PathVariable double leitfaehigkeitNeu, @PathVariable double phwertNeu,
			@PathVariable double sauerstoffgehaltNeu, @PathVariable double sauerstoffsaettigungNeu,
			@PathVariable Integer iteration, @PathVariable String faerbung, @PathVariable String truebung,
			@PathVariable String geruch, @PathVariable String oelfilm, @PathVariable String schaumbildung) {

		System.out.println("\n" + "++MESSSTELLEN-ID: " + messstellenId);
		System.out.println("\n" + "++TEMPERATUR: " + temperaturNeu);
		System.out.println("\n" + "++LEITFÄHIGKEIT: " + leitfaehigkeitNeu);
		System.out.println("\n" + "++PH-WERT: " + phwertNeu);
		System.out.println("\n" + "++SAUERSTOFFGEHALT: " + sauerstoffgehaltNeu);
		System.out.println("\n" + "++SAUERSTSÄTTIGUNG: " + sauerstoffsaettigungNeu);
		System.out.println("\n" + "++Iteration: " + iteration);

		System.out.println("\n" + "+++++FÄRBUNG: " + faerbung);
		System.out.println("\n" + "+++++truebung: " + truebung);
		System.out.println("\n" + "+++++geruch: " + geruch);
		System.out.println("\n" + "+++++oelfilm: " + oelfilm);
		System.out.println("\n" + "+++++schaumbildung: " + schaumbildung);

		WateranalysisClassicObject object = new WateranalysisClassicObject();
		// wasseranalyse berechnen
		object = wateranalysisClassic.wateranalysisObject(messstellenId, temperaturNeu, leitfaehigkeitNeu, phwertNeu,
				sauerstoffgehaltNeu, sauerstoffsaettigungNeu);

		// organoleptische werte berechnen
		// get Liste mit allen h2oproben für diese Messstelle
		object.setOrganoleptischeWerte(wateranalysisClassic.calculateOccurrenceOrganoleptic(messstellenId, faerbung,
				truebung, geruch, oelfilm, schaumbildung));

		System.out.println(
				"ORGANOLEPTISCH AUSSEN faerbung: " + object.organoleptischeWerte.faerbung.getVorgefalleneCodes());
		System.out
				.println("ORGANOLEPTISCH AUSSEN geruch: " + object.organoleptischeWerte.geruch.getVorgefalleneCodes());
		System.out.println(
				"ORGANOLEPTISCH AUSSEN truebung: " + object.organoleptischeWerte.truebung.getVorgefalleneCodes());
		System.out.println(
				"ORGANOLEPTISCH AUSSEN oelfilm: " + object.organoleptischeWerte.oelfilm.getVorgefalleneCodes());
		System.out.println("ORGANOLEPTISCH AUSSEN schaumbildung: "
				+ object.organoleptischeWerte.schaumbildung.getVorgefalleneCodes());
		PrologAllResultsObject prologResults = Prologjpl.checkPrologRules(object);

		String empfehlungVorhanden = "";
		String empfehlungNichtVorhanden = "Das System konnte keine Abweichungen feststellen! Alle Parameterwerte sind gültig und können gespeichert werden! - The system could not detect any deviation! All parameter values are valid and can be saved!";
		boolean normalDeviationTriggered = false;
		boolean iceDeviationTriggered = false;
		boolean greatDeviationTriggered = false;

		// check ob iceRule gefeuert wurde dann prolog Boolean auf true schalten, weil
		// es eine Empfehlung gibt
		if (prologResults.getRuleIce().getRuleFired()) {
			object.setPrologResults(true);
			iceDeviationTriggered = true;
			// object.setPrologResultsMessage(prologResults.getRuleIce().getRuleMessage());
			empfehlungVorhanden = empfehlungVorhanden + prologResults.getRuleIce().getRuleMessage() + "\n";
		}

		if (prologResults.getRuleGreatDeviation().getRuleFired()) {
			object.setPrologResults(true);
			greatDeviationTriggered = true;
			empfehlungVorhanden = empfehlungVorhanden + prologResults.getRuleGreatDeviation().getRuleMessage() + "\n";

		}

		// Check ob es normale deviation gibt
		// temperatur
		if (prologResults.getRuleDeviationTemperatur().getRuleFired()) {
			object.setPrologResults(true);
			normalDeviationTriggered = true;
			// object.setPrologResultsMessage(prologResults.getRuleIce().getRuleMessage());
			empfehlungVorhanden = empfehlungVorhanden + prologResults.getRuleDeviationTemperatur().getRuleMessage()
					+ " Temperatur \n";
		}

		// Check ob es normale deviation gibt
		// Leitfähigkeit
		if (prologResults.getRuleDeviationElektronischeLeitfaehigkeit().getRuleFired()) {
			object.setPrologResults(true);
			normalDeviationTriggered = true;
			// object.setPrologResultsMessage(prologResults.getRuleIce().getRuleMessage());
			empfehlungVorhanden = empfehlungVorhanden
					+ prologResults.getRuleDeviationElektronischeLeitfaehigkeit().getRuleMessage()
					+ " Elektronische Leitfaehigkeit \n";
		}

		// Check ob es normale deviation gibt
		// phwert
		if (prologResults.getRuleDeviationPhWert().getRuleFired()) {
			object.setPrologResults(true);
			normalDeviationTriggered = true;
			// object.setPrologResultsMessage(prologResults.getRuleIce().getRuleMessage());
			empfehlungVorhanden = empfehlungVorhanden + prologResults.getRuleDeviationPhWert().getRuleMessage()
					+ " PH-Wert \n";
		}

		// Check ob es normale deviation gibt
		// sauerstoffgehalt
		if (prologResults.getRuleDeviationSauerstoffgehalt().getRuleFired()) {
			object.setPrologResults(true);
			normalDeviationTriggered = true;
			// object.setPrologResultsMessage(prologResults.getRuleIce().getRuleMessage());
			empfehlungVorhanden = empfehlungVorhanden
					+ prologResults.getRuleDeviationSauerstoffgehalt().getRuleMessage() + " Sauerstoffgehalt \n";
		}

		// Check ob es normale deviation gibt
		// sauerstoffsaettigung
		if (prologResults.getRuleDeviationSauerstoffsaettigung().getRuleFired()) {
			object.setPrologResults(true);
			normalDeviationTriggered = true;
			// object.setPrologResultsMessage(prologResults.getRuleIce().getRuleMessage());
			empfehlungVorhanden = empfehlungVorhanden
					+ prologResults.getRuleDeviationSauerstoffsaettigung().getRuleMessage()
					+ " Sauerstoffsaettigung \n";
		}

		if (object.getPrologResults()) {
			if (normalDeviationTriggered && !iceDeviationTriggered && !greatDeviationTriggered) {
				System.out.println("NORMALE ABWEICHUNG WURDE GETRIGGERED!");

				System.out.println("IM IF DIE EMPFEHLUNG: " + Prologjpl.checkIteration(iteration));

				empfehlungVorhanden = empfehlungVorhanden + Prologjpl.checkIteration(iteration);
			}
			object.setPrologResultsMessage(empfehlungVorhanden);
		} else
			object.setPrologResultsMessage(empfehlungNichtVorhanden);

		System.out.println("Das ist  Boolean: " + object.getPrologResults().toString());
		System.out.println("Das ist  String: " + object.getPrologResultsMessage());

		return object;

	}

	@GetMapping("/prolog")
	public void getPrologInfo() {

		// System.out.println(Prologjpl.getOutput());

		System.out.println(Prologjpl.checkIfPrologRuns());
	}

	// Test Join Query
	@GetMapping("/wasseranalyse")
	public List<String> getRealData() {

		return probedatenRepository.getRealwert();

	}

	@PostMapping("/upload")
	public void addData(@RequestParam("file") MultipartFile file) {

		System.out.println(file.getOriginalFilename());

		if (file.getOriginalFilename().contains("messstelle")) {
			List<Messstelle> listMessstelle = importData.executeImportMessstelle(file);
			System.out.println(listMessstelle);
			messstelleRepository.saveAll(listMessstelle);

		}

		if (file.getOriginalFilename().contains("h2o_probe")) {
			List<H2oProbe> listH2o_probe = importData.executeImportH2o_probe(file);
			System.out.println(listH2o_probe);
			h2oProbeRepository.saveAll(listH2o_probe);

		}

		if (file.getOriginalFilename().contains("probedaten")) {
			List<Probedaten> listProbedaten = importData.executeImportProbedaten(file);
			System.out.println(listProbedaten);
			probedatenRepository.saveAll(listProbedaten);
		}
	}

}
