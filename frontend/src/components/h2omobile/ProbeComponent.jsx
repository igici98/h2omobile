import React, { Component } from "react"
import moment from 'moment';
import { Field, Formik, Form, ErrorMessage } from 'formik';
import AuthenticationService from "./AuthenticationService";
import H2oService from "../../api/h2o/H2oService";


class ProbeComponent extends Component {


    constructor(props) {
        super(props)

        this.state = {
            probenahmeid: this.props.match.params.idnew,
            messstellenid: this.props.match.params.id,
            newEntrySchalter: false,
            saveNewEntry: false,
            doWateranalysis: false,
            probenummer: '',
            probedatum: '',
            messstellennummer: '',
            messstellenname: '',
            turnusnummer: '',
            katasternummer: '',
            faerbung: '',
            truebung: '',
            geruch: '',
            oelfilm: '',
            schaumbildung: '',
            analysisResults: {},
            analysisCalculated: false,
            analysisIteration: 0,

            //leeres Objekt ist automatisch map ähnlich wie hashmap
            probedaten: {}




        }

        this.onSubmit = this.onSubmit.bind(this)


    }
    componentDidMount() {
        console.log('+++++++++' + this.state.probenahmeid)
        //neue ProbeID wird erstellt
        if (this.state.probenahmeid == -1) {
            //https://attacomsian.com/blog/javascript-generate-random-string
            //Create new random ID
            let chars = 'abcdefghijklmnopqrstuvwxyz0123456789';
            let str = '';
            for (let i = 0; i < 32; i++) {
                str += chars.charAt(Math.floor(Math.random() * chars.length));
            }
            this.setState({
                probenahmeid: str,
                newEntrySchalter: true
            })
            H2oService.retrieveMesstelleById(this.state.messstellenid)
                .then(response => {
                    this.setState({
                        messstellennummer: response.data[0].mobjwgevid,
                        messstellenname: response.data[0].mobjname
                    })
                })
                .catch((error) => { console.log(error) })
            //  console.log(this.state.probenameid)
            return
        }
        //alte ProbeID wird geladen
        console.log(this.state.probenahmeid)
        console.log(this.props.match.params.id)
        let probenid = this.state.probenahmeid
        let messstellenid = this.state.messstellenid
        //obere Daten
        H2oService.retrieveProbe(probenid)
            .then(response => {
                // console.log(response)
                //    console.log(response.data[0].probedatum, response.data[0].messstelle.mobjwgevid)
                this.setState({
                    probenummer: response.data[0].probenr,
                    probedatum: moment(response.data[0].probedatum).format('YYYY-MM-DD'),
                    turnusnummer: response.data[0].turnusnr,
                    katasternummer: response.data[0].kataster,
                    messstellennummer: response.data[0].messstelle.mobjwgevid,
                    messstellenname: response.data[0].messstelle.mobjname
                })
            })
            .catch((error) => { console.log(error) })
        //untere Daten
        H2oService.retrieveAllParameterFromProbe(probenid, messstellenid)
            .then(response => {
                console.log('RESPONSE PARAMETETER' + response)
                console.log(response.data[0].realwert, response.data[0].parameter.parnr)
                let probemap = {}
                //befüllung von map mit den Parametern aus der Response
                response.data.forEach(item => {

                    if (item.parameter.parnr == 'F114' || item.parameter.parnr == 'F115' || item.parameter.parnr == 'F116' || item.parameter.parnr == 'F553' || item.parameter.parnr == 'F854') {
                        probemap[item.parameter.parnr] = item.stringwert
                    }
                    else probemap[item.parameter.parnr] = item.realwert


                })


                console.log(probemap)
                this.setState({
                    probedaten: probemap
                })
            })
            .catch((error) => { console.log(error) })




    }

    /*
        validate(values) {
            let errors = {}
            if (!values.description) {
                errors.description = 'Enter a Description'
            } else if (values.description.length < 5) {
                errors.description = 'Enter at least 5 Characters in Description'
    
    
            }
    
            if (!moment(values.targetDate).isValid()) {
    
                errors.targetDate = 'Enter a Valid Target Date'
            }
    
            return errors
        }
        */


    onSubmit(values) {


        var new_water = values.wassertemperatur.replace(',', '.');
        var new_leitfaehigkeit = values.leitfaehigkeit.replace(',', '.');
        var new_phWert = values.phWert.replace(',', '.');
        var new_sauerstoffgehalt = values.sauerstoffgehalt.replace(',', '.');
        var new_sauerstoffsaettigung = values.sauerstoffsaettigung.replace(',', '.');


        /* 
        nicht notwendig für organoloptische parameter
        
        Färbung
Trübung
Geruch
Ölfilm
Schaumbildung */

        console.log(this.props.match.params.id);
        console.log(new_water);
        console.log(new_leitfaehigkeit);
        console.log(new_phWert);
        console.log(new_sauerstoffgehalt);
        console.log(new_sauerstoffsaettigung);




        //If für wasseranalyse Button
        if (this.state.doWateranalysis === true) {
            //Debug für wasseranalyse schalter auf true
            console.log(" Wasseranalyse = true");
            this.state.analysisIteration = this.state.analysisIteration + 1;
            console.log("ITERATION ANALYSE:" + this.state.analysisIteration);

            //Make Wateranalysis
            //  temperaturNeu, leitfaehigkeitNeu, phwertNeu, sauerstoffgehaltNeu, sauerstoffsaettigungNeu

            H2oService.executeWateranalysisClassic(this.props.match.params.id, new_water, new_leitfaehigkeit, new_phWert, new_sauerstoffgehalt, new_sauerstoffsaettigung, this.state.analysisIteration, values.faerbung, values.truebung, values.geruch, values.oelfilm, values.schaumbildung)
                .then(response => {
                    this.setState({
                        analysisResults: response,
                        analysisCalculated: true
                    })
                    console.log(" Wasseranalyse Objekt erfolgreich geladen");

                    console.log(this.state.analysisResults.data);
                })
                .catch((error) => { console.log(error) })

            //Schalter wieder auf false setzen
            this.setState({
                doWateranalysis: false
            })


        } else {

            console.log(" Wasseranalyse = false");
        }



        //If für Save Button neue Daten speichern
        if (this.state.saveNewEntry === true) {
            //Create New h2o Probe
            console.log(" Save new data = true");
            let username = AuthenticationService.getLoggedInUserName()

            //1. Step neue H2oprobe speichern
            let h2oProbe = {

                id: this.state.probenahmeid,
                probenr: values.probenummer,
                probedatum: values.probedatum,
                turnusnr: values.turnusnummer,
                zrid: null,
                kataster: values.katasternummer,
                zustid: null,
                mstid: this.state.messstellenid,
                transid: null,
                probekz: 'J',
                letzteaenderung: '',
                updatecount: '0',
                lastuserid: username,
                probereich: 'FW',
                plkpruefungstatus: null,
                probenzuordnungid: 'GZUEV',
                freigabestatus_id: null,
                plkprotokoll: 'Test',
                plkkommentar: 'Test',
                biologie_qualitaetselement: 'Test'
            }

            //2. Step Parameterobjekte speichern


            //https://attacomsian.com/blog/javascript-generate-random-string
            //Create new random ID
            let chars = 'abcdefghijklmnopqrstuvwxyz0123456789';
            let temperaturId = '';
            for (let i = 0; i < 32; i++) {
                temperaturId += chars.charAt(Math.floor(Math.random() * chars.length));
            }

            let phwertId = '';
            for (let i = 0; i < 32; i++) {
                phwertId += chars.charAt(Math.floor(Math.random() * chars.length));
            }

            let leitfaehigkeitId = '';
            for (let i = 0; i < 32; i++) {
                leitfaehigkeitId += chars.charAt(Math.floor(Math.random() * chars.length));
            }

            let sauerstoffgehaltId = '';
            for (let i = 0; i < 32; i++) {
                sauerstoffgehaltId += chars.charAt(Math.floor(Math.random() * chars.length));
            }

            let sauerstoffsaettigungId = '';
            for (let i = 0; i < 32; i++) {
                sauerstoffsaettigungId += chars.charAt(Math.floor(Math.random() * chars.length));
            }

            //neue organoleptische parameter
            let faerbungId = '';
            for (let i = 0; i < 32; i++) {
                faerbungId += chars.charAt(Math.floor(Math.random() * chars.length));
            }

            let truebungId = '';
            for (let i = 0; i < 32; i++) {
                truebungId += chars.charAt(Math.floor(Math.random() * chars.length));
            }

            let geruchId = '';
            for (let i = 0; i < 32; i++) {
                geruchId += chars.charAt(Math.floor(Math.random() * chars.length));
            }

            let oelfilmId = '';
            for (let i = 0; i < 32; i++) {
                oelfilmId += chars.charAt(Math.floor(Math.random() * chars.length));
            }

            let schaumbildungId = '';
            for (let i = 0; i < 32; i++) {
                schaumbildungId += chars.charAt(Math.floor(Math.random() * chars.length));
            }

            // set temperatur
            let temperaturProbedatenObject = {

                id: temperaturId,
                proid: this.state.probenahmeid,
                parid: "8ae5e2f31a15860a011a1586224c0012",
                probekz: "J",
                stringwert: null,
                realwert: new_water,
                wertbg: null,
                wertng: null,
                datumwert: null,
                lastuserid: username,
                letzteaenderung: "",
                updatecount: 0,
                vetrauensbereich: null,
                fromparamref: null,
                manuellkorrigiertjn: null,
                bgngwert: null,
                qparamflags: null
            }


            //set phwert
            let phwertProbedatenObject = {

                id: phwertId,
                proid: this.state.probenahmeid,
                parid: "8ae5e2f31a15860a011a1586227b0014",
                probekz: "J",
                stringwert: null,
                realwert: new_phWert,
                wertbg: null,
                wertng: null,
                datumwert: null,
                lastuserid: username,
                letzteaenderung: "",
                updatecount: 0,
                vetrauensbereich: null,
                fromparamref: null,
                manuellkorrigiertjn: null,
                bgngwert: null,
                qparamflags: null
            }


            //set leitfaehigkeit
            let leitfaehigkeitProbedatenObject = {

                id: leitfaehigkeitId,
                proid: this.state.probenahmeid,
                parid: "8ae5e2f31a15860a011a1586225b0013",
                probekz: "J",
                stringwert: null,
                realwert: new_leitfaehigkeit,
                wertbg: null,
                wertng: null,
                datumwert: null,
                lastuserid: username,
                letzteaenderung: "",
                updatecount: 0,
                vetrauensbereich: null,
                fromparamref: null,
                manuellkorrigiertjn: null,
                bgngwert: null,
                qparamflags: null
            }


            //set sauerstoffgehalt
            let sauerstoffgehaltProbedatenObject = {

                id: sauerstoffgehaltId,
                proid: this.state.probenahmeid,
                parid: "8ae5e2f31a15860a011a158623070019",
                probekz: "J",
                stringwert: null,
                realwert: new_sauerstoffgehalt,
                wertbg: null,
                wertng: null,
                datumwert: null,
                lastuserid: username,
                letzteaenderung: "",
                updatecount: 0,
                vetrauensbereich: null,
                fromparamref: null,
                manuellkorrigiertjn: null,
                bgngwert: null,
                qparamflags: null
            }

            //set sauerstoffsaettigung
            let sauerstoffsaettigungProbedatenObject = {

                id: sauerstoffsaettigungId,
                proid: this.state.probenahmeid,
                parid: "8ae5e2f31a15860a011a15862317001a",
                probekz: "J",
                stringwert: null,
                realwert: new_sauerstoffsaettigung,
                wertbg: null,
                wertng: null,
                datumwert: null,
                lastuserid: username,
                letzteaenderung: "",
                updatecount: 0,
                vetrauensbereich: null,
                fromparamref: null,
                manuellkorrigiertjn: null,
                bgngwert: null,
                qparamflags: null
            }

            //set Färbung
            let faerbungObject = {

                id: faerbungId,
                proid: this.state.probenahmeid,
                parid: "8ae5e2f31a15860a011a1586220d0010",
                probekz: "J",
                stringwert: values.faerbung,
                realwert: null,
                wertbg: null,
                wertng: null,
                datumwert: null,
                lastuserid: username,
                letzteaenderung: "",
                updatecount: 0,
                vetrauensbereich: null,
                fromparamref: null,
                manuellkorrigiertjn: null,
                bgngwert: null,
                qparamflags: null
            }
            //set Trübung
            let truebungObject = {

                id: truebungId,
                proid: this.state.probenahmeid,
                parid: "8ae5e2f31a15860a011a1586222c0011",
                probekz: "J",
                stringwert: values.truebung,
                realwert: null,
                wertbg: null,
                wertng: null,
                datumwert: null,
                lastuserid: username,
                letzteaenderung: "",
                updatecount: 0,
                vetrauensbereich: null,
                fromparamref: null,
                manuellkorrigiertjn: null,
                bgngwert: null,
                qparamflags: null
            }
            //set Geruch

            let geruchObject = {

                id: geruchId,
                proid: this.state.probenahmeid,
                parid: "8ae5e2f31a15860a011a158621de000f",
                probekz: "J",
                stringwert: values.geruch,
                realwert: null,
                wertbg: null,
                wertng: null,
                datumwert: null,
                lastuserid: username,
                letzteaenderung: "",
                updatecount: 0,
                vetrauensbereich: null,
                fromparamref: null,
                manuellkorrigiertjn: null,
                bgngwert: null,
                qparamflags: null
            }

            //set Ölfilm
            let oelfilmObject = {

                id: oelfilmId,
                proid: this.state.probenahmeid,
                parid: "8ae5e2f31a15860a011a15864bcc01c6",
                probekz: "J",
                stringwert: values.oelfilm,
                realwert: null,
                wertbg: null,
                wertng: null,
                datumwert: null,
                lastuserid: username,
                letzteaenderung: "",
                updatecount: 0,
                vetrauensbereich: null,
                fromparamref: null,
                manuellkorrigiertjn: null,
                bgngwert: null,
                qparamflags: null
            }
            //set Schaumbildung
            let schaumbildungObject = {

                id: schaumbildungId,
                proid: this.state.probenahmeid,
                parid: "4028f0f02b8a3a5b012b9b3f99ae0ae6",
                probekz: "J",
                stringwert: values.schaumbildung,
                realwert: null,
                wertbg: null,
                wertng: null,
                datumwert: null,
                lastuserid: username,
                letzteaenderung: "",
                updatecount: 0,
                vetrauensbereich: null,
                fromparamref: null,
                manuellkorrigiertjn: null,
                bgngwert: null,
                qparamflags: null
            }


            let saveProbedaten = {

                probe: h2oProbe,
                temperaturProbedaten: temperaturProbedatenObject,
                phwertProbedaten: phwertProbedatenObject,
                leitfaehigkeitProbedaten: leitfaehigkeitProbedatenObject,
                sauerstoffgehaltProbedaten: sauerstoffgehaltProbedatenObject,
                sauerstoffsaettigungProbedaten: sauerstoffsaettigungProbedatenObject,
                faerbungProbedaten: faerbungObject,
                truebungProbedaten: truebungObject,
                geruchProbedaten: geruchObject,
                oelfilmProbedaten: oelfilmObject,
                schaumbildungProbedaten: schaumbildungObject

            }

            console.log("Kontrolle Object temperatur");
            console.log(saveProbedaten.temperaturProbedaten);



            //Kontrolle h20 Probe
            console.log("Kontrolle h20 Probe");
            console.log(values.wassertemperatur);
            console.log(values.leitfaehigkeit);
            console.log(values.phWert);
            console.log(values.sauerstoffgehalt);
            console.log(values.sauerstoffsaettigung);
            console.log(" ");
            console.log("++++++++H20 PROBE:+++++++++");
            console.log(h2oProbe.id);
            console.log(h2oProbe.probenr);
            console.log("Probedatum " + h2oProbe.probedatum);
            console.log(h2oProbe.turnusnr);
            console.log(h2oProbe.zrid);
            console.log(h2oProbe.kataster);
            console.log(h2oProbe.zustid);
            console.log("MSTID " + h2oProbe.mstid);
            console.log(h2oProbe.transid);
            console.log(h2oProbe.probekz);
            console.log("letzte Änderung" + h2oProbe.letzteaenderung);
            console.log(h2oProbe.updatecount);
            console.log(h2oProbe.lastuserid);
            console.log(h2oProbe.probereich);
            console.log(h2oProbe.plkpruefungstatus);
            console.log(h2oProbe.probenzuordnungid);
            console.log(h2oProbe.freigabestatus_id);
            console.log(h2oProbe.plkprotokoll);
            console.log(h2oProbe.plkkommentar);
            console.log(h2oProbe.biologie_qualitaetselement);



            H2oService.createProbedaten(h2oProbe.mstid, saveProbedaten)
                .then(console.log("NEUE PROBE ERFOLGREICH GESPEICHERT"))

            //Schalter wieder auf false setzen
            this.setState({
                saveNewEntry: false
            })
            console.log(this.state.saveNewEntry);
        } else {

            console.log(" Save new data = false");
        }



    }




    render() {
        // let { description, targetDate } = this.state
        // let targetDate = this.state.targetDate



        //definieren von Properties
        let { probenummer, probedatum, messstellennummer, messstellenname, probenahmeid, turnusnummer, katasternummer, newEntrySchalter } = this.state


        let wassertemperatur = ""
        let leitfaehigkeit = ""
        let phWert = ""
        let sauerstoffgehalt = ""
        let sauerstoffsaettigung = ""

        let geruch = ""
        let faerbung = ""
        let truebung = ""
        let oelfilm = ""
        let schaumbildung = ""
        let analysisCalculated = this.state.analysisCalculated



        if (this.state.newEntrySchalter == false) {
            //No Data
            wassertemperatur = this.state.probedaten.F117
            leitfaehigkeit = this.state.probedaten.F118
            phWert = this.state.probedaten.F119
            sauerstoffgehalt = this.state.probedaten.F124
            sauerstoffsaettigung = this.state.probedaten.F125
            geruch = this.state.probedaten.F114
            faerbung = this.state.probedaten.F115
            truebung = this.state.probedaten.F116
            oelfilm = this.state.probedaten.F553
            schaumbildung = this.state.probedaten.F854
        }

        return (
            <div>

                <p >

                    {!this.state.newEntrySchalter && <h2>Probenummer {probenummer} </h2>}
                    {this.state.newEntrySchalter && <h2>Neue Probe hinzufügen </h2>}



                    <h4>Debug: Probe-ID {probenahmeid} </h4>




                </p>

                <div className="container">
                    <Formik

                        initialValues={{ probenummer, probedatum, turnusnummer, katasternummer, messstellennummer, messstellenname, wassertemperatur, leitfaehigkeit, phWert, sauerstoffgehalt, sauerstoffsaettigung, faerbung, truebung, geruch, oelfilm, schaumbildung }}



                        onSubmit={this.onSubmit}
                        //validateOnBlur={false}
                        //validateOnChange={false}
                        //validate={this.validate}
                        enableReinitialize={true}


                    >

                        {
                            (props) => (
                                <Form>
                                    <ErrorMessage name="description" component="div" className="alert alert-warning" />
                                    <ErrorMessage name="targetDate" component="div" className="alert alert-warning" />


                                    <fieldset className="border p-2 " style={{ marginTop: `20px`, marginBottom: `20px` }} >

                                        <legend className="w-auto text-left">Allgemeine Angaben</legend>

                                        <fieldset className="form-group row">

                                            <label className="col-md-2 col-form-label label-form" >Messstellen-Nr.:</label>
                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="messstellennummer" readonly="readonly"

                                                //value={this.state.messstellennummer} 
                                                />
                                            </div>
                                        </fieldset>


                                        <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form">Messstellen-Name:</label>
                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="messstellenname" readonly="readonly"

                                                //value={this.state.messstellenname} 

                                                />
                                            </div>
                                        </fieldset>






                                        {/* PROBENUMMER  */}
                                        {/* old data - read only  */}

                                        {!this.state.newEntrySchalter &&

                                            <fieldset className="form-group row">
                                                <label className="col-md-2 col-form-label label-form" >Probenummer: (Bsp.: BFW0100520)</label>
                                                <div className="col-md-10">
                                                    <Field className="form-control" type="text" name="probenummer" readonly="readonly"

                                                    //value={this.state.probedatum} 

                                                    />
                                                </div>
                                            </fieldset>

                                        }

                                        {/* new data - NO read only  */}
                                        {this.state.newEntrySchalter &&

                                            <fieldset className="form-group row">
                                                <label className="col-md-2 col-form-label label-form" >Probenummer: (Bsp.: BFW0100520)</label>
                                                <div className="col-md-10">
                                                    <Field className="form-control" type="text" name="probenummer"

                                                    //value={this.state.probedatum} 

                                                    />
                                                </div>
                                            </fieldset>

                                        }



                                        {/* TURNUSNR  */}
                                        {/* old data - read only  */}
                                        {!this.state.newEntrySchalter &&

                                            <fieldset className="form-group row">
                                                <label className="col-md-2 col-form-label label-form" >Turnusnummer: (Bsp.: A710)</label>
                                                <div className="col-md-10">
                                                    <Field className="form-control" type="text" name="turnusnummer" readonly="readonly"

                                                    //value={this.state.probedatum} 

                                                    />
                                                </div>
                                            </fieldset>

                                        }

                                        {/* new data - NO read only  */}
                                        {this.state.newEntrySchalter &&

                                            <fieldset className="form-group row">
                                                <label className="col-md-2 col-form-label label-form" >Turnusnummer: (Bsp.: A710)</label>
                                                <div className="col-md-10">
                                                    <Field className="form-control" type="text" name="turnusnummer"

                                                    //value={this.state.probedatum} 

                                                    />
                                                </div>
                                            </fieldset>

                                        }




                                        {/* KATASTERNUMMER  */}
                                        {/* old data - read only  */}
                                        {!this.state.newEntrySchalter &&

                                            <fieldset className="form-group row">
                                                <label className="col-md-2 col-form-label label-form" >Kataster: (Bsp.: 1300)</label>
                                                <div className="col-md-10">
                                                    <Field className="form-control" type="text" name="katasternummer" readonly="readonly"

                                                    //value={this.state.probedatum} 

                                                    />
                                                </div>
                                            </fieldset>

                                        }

                                        {/* new data - NO read only  */}
                                        {this.state.newEntrySchalter &&

                                            <fieldset className="form-group row">
                                                <label className="col-md-2 col-form-label label-form" >Kataster: (Bsp.: 1300)</label>
                                                <div className="col-md-10">
                                                    <Field className="form-control" type="text" name="katasternummer"

                                                    //value={this.state.probedatum} 

                                                    />
                                                </div>
                                            </fieldset>

                                        }



                                        {/* PROBENAHMEDATUM  */}
                                        {/* old data - read only  */}
                                        {!this.state.newEntrySchalter && <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form" >Probenahmedatum:</label>
                                            <div className="col-md-10">
                                                <Field className="form-control" type="date" name="probedatum" readonly="readonly"

                                                //value={this.state.probedatum} 

                                                />
                                            </div>
                                        </fieldset>}

                                        {/* new data - NO read only  */}
                                        {this.state.newEntrySchalter && <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form" >Probenahmedatum:</label>
                                            <div className="col-md-10">
                                                <Field className="form-control" type="date" name="probedatum"

                                                //value={this.state.probedatum} 

                                                />
                                            </div>
                                        </fieldset>}









                                    </fieldset>



                                    <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }} >

                                        <legend className="w-auto text-left">Physikalisch-chemische Parameter</legend>


                                        <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form">Wassertemperatur °C:</label>
                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="wassertemperatur"

                                                //value={this.state.probedaten.F117}  wassertemperatur

                                                />
                                            </div>
                                        </fieldset>

                                        <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form">Elektr.Leitfähigkeit (bei 25°C) µS/cm:</label>
                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="leitfaehigkeit"

                                                //value={this.state.probedaten.F118}  leitfaehigkeit

                                                />
                                            </div>
                                        </fieldset>

                                        <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form">PH-Wert:</label>
                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="phWert"

                                                // value={this.state.probedaten.F119} phWert

                                                />
                                            </div>
                                        </fieldset>
                                        <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form">Sauerstoffgehalt mg/l:</label>
                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="sauerstoffgehalt"

                                                //value={this.state.probedaten.F124} sauerstoffgehalt

                                                />
                                            </div>
                                        </fieldset>

                                        <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form">Sauerstoffsättigung %:</label>
                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="sauerstoffsaettigung"

                                                //value={this.state.probedaten.F125}  sauerstoffsaettigung

                                                />
                                            </div>
                                        </fieldset>

                                    </fieldset>



                                    <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>

                                        <legend className="w-auto text-left">Organoleptische Parameter</legend>



                                        <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form">Färbung:</label>
                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="faerbung" />
                                            </div>
                                            <span className="col-md-2" />
                                            <font size="2" style={{ whiteSpace: 'pre-line', textAlign: 'left', marginTop: `10px`, marginBottom: `20px` }} className="col-md-10">
                                                1. Stelle: Angabe zur Farbintensität  {"\n"}
                                                2. Stelle: Farbcode 1 (dominierender Farbton)  {"\n"}
                                                3. Stelle: Farbcode 2 {"\n"}
                                                {"\n"}
                                                Farbintensität: 0 = keine Angabe zur Intensität | 1 = leicht | 2 = mäßig | 3 = stark | 9 = Sonderfarbe
                                                {"\n"}
                                                Farbcode:
                                                0 = farblos |
                                                1 = weiß |
                                                2 = grau |
                                                3 = schwarz |
                                                4 = gelb |
                                                5 = orange |
                                                6 = rot |
                                                7 = grün |
                                                8 = braun |
                                                9 = blau
                                                {"\n"}
                                                Sonderfarben:
                                                901 = türkis |
                                                902 = violett |
                                                903 = lila |
                                                904 = opalisierend
                                            </font>
                                        </fieldset>






                                        <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form">Trübung:</label>

                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="truebung" />
                                            </div>
                                            <span className="col-md-2" />
                                            <font size="2" style={{ textAlign: 'left', marginTop: `10px`, marginBottom: `20px` }} className="col-md-10">
                                                0 = N.A. |
                                                1 = keine Trübung |
                                                2 = schwach |
                                                3 = mittel |
                                                4 = stark
                                            </font>
                                        </fieldset>



                                        <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form">Geruch:</label>

                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="geruch" />
                                            </div>
                                            <span className="col-md-2" />
                                            <font size="2" style={{ textAlign: 'left', marginTop: `10px`, marginBottom: `20px` }} className="col-md-10">
                                                1 = geruchlos |
                                                2 = aromatisch |
                                                3 = würzig |
                                                4 = erdig |
                                                5 = torfig |
                                                6 = muffig |
                                                7 = modrig |
                                                8 = faulig |
                                                9 = tranig |
                                                10 = fischig |
                                                11 = jauchig |
                                                12 = fäkalartig |
                                                20 = chemisch |
                                                21 = Schwefelwasserstoff |
                                                22 = Kohlensäure |
                                                23 = Chlor |
                                                24 = Mineralöl |
                                                25 = Benzin |
                                                26 = Ammoniak |
                                                27 = Phenol |
                                                28 = Chlorphenol |
                                                29 = Teer |
                                                30 = Silage
                                            </font>
                                        </fieldset>





                                        <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form">Ölfilm:</label>

                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="oelfilm" />
                                            </div>
                                            <span className="col-md-2" />
                                            <font size="2" style={{ textAlign: 'left', marginTop: `10px`, marginBottom: `20px` }} className="col-md-10">
                                                0 = Nein |
                                                1 = Ja
                                            </font>
                                        </fieldset>




                                        <fieldset className="form-group row">
                                            <label className="col-md-2 col-form-label label-form">Schaumbildung:</label>

                                            <div className="col-md-10">
                                                <Field className="form-control" type="text" name="schaumbildung" />
                                            </div>
                                            <span className="col-md-2" />
                                            <font size="2" style={{ textAlign: 'left', marginTop: `10px` }} className="col-md-10">
                                                0 = Nein |
                                                1 = Ja
                                            </font>
                                        </fieldset>


                                    </fieldset>

                                    {/* Save Button mit Schalter wenn NEUE Daten geladen werden   */}
                                    {this.state.newEntrySchalter && <button className="btn btn-success" type="submit" onClick={() => this.setState({
                                        saveNewEntry: true
                                    })} style={{ marginTop: `20px`, marginBottom: `60px`, marginRight: `10px` }}>Speichern</button>}


                                    {/* Wasseranalyse Button mit Schalter wenn NEUE Daten geladen werden   */}
                                    {this.state.newEntrySchalter && <button className="btn btn-success" type="submit" onClick={() => this.setState({
                                        doWateranalysis: true
                                    })} style={{ marginTop: `20px`, marginBottom: `60px`, marginRight: `10px` }}>Wasseranalyse</button>}


                                    {/* Wasseranalyse Button mit Schalter wenn Alte Daten geladen werden  */}
                                    {!this.state.newEntrySchalter && <button className="btn btn-success" type="submit" onClick={() => this.setState({
                                        doWateranalysis: true
                                    })} style={{ marginTop: `20px`, marginBottom: `60px`, marginRight: `10px` }}>Wasseranalyse</button>}





                                    {this.state.analysisCalculated &&
                                        <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>

                                            <legend className="w-auto text-left">Ergebnisse der Wasseranalyse</legend>




                                            <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>
                                                <legend className="w-auto text-left">Temperatur °C</legend>
                                                <div style={{ textAlign: 'left' }}>Temperaturwert: {this.state.analysisResults.data.temperatur.parameterValue}</div>
                                                <div style={{ textAlign: 'left' }}>Untere-Prüfschranke: {this.state.analysisResults.data.temperatur.unterepruefschrankeValue}</div>
                                                <div style={{ textAlign: 'left' }}>Obere-Prüfschranke: {this.state.analysisResults.data.temperatur.oberepruefschrankeValue}</div>
                                                <div style={{ textAlign: 'left' }}>Der Parameterwert unter- oder überschreitet die Prüfwerte: {this.state.analysisResults.data.temperatur.istOk ? "Nein" : "Ja"}</div>
                                            </fieldset>


                                            <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>
                                                <legend className="w-auto text-left">Elektronische Leitfähigkeit (bei 25°C) µS/cm</legend>
                                                <div style={{ textAlign: 'left' }}>Leitfähigkeitswert: {this.state.analysisResults.data.elektronischeLeitfaehigkeit.parameterValue}</div>
                                                <div style={{ textAlign: 'left' }}>Untere-Prüfschranke: {this.state.analysisResults.data.elektronischeLeitfaehigkeit.unterepruefschrankeValue}</div>
                                                <div style={{ textAlign: 'left' }}>Obere-Prüfschranke: {this.state.analysisResults.data.elektronischeLeitfaehigkeit.oberepruefschrankeValue}</div>
                                                <div style={{ textAlign: 'left' }}>Der Parameterwert unter- oder überschreitet die Prüfwerte: {this.state.analysisResults.data.elektronischeLeitfaehigkeit.istOk ? "Nein" : "Ja"}</div>
                                            </fieldset>


                                            <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>
                                                <legend className="w-auto text-left">PH-Wert</legend>
                                                <div style={{ textAlign: 'left' }}>PH-Wert: {this.state.analysisResults.data.phWert.parameterValue}</div>
                                                <div style={{ textAlign: 'left' }}>Untere-Prüfschranke: {this.state.analysisResults.data.phWert.unterepruefschrankeValue}</div>
                                                <div style={{ textAlign: 'left' }}>Obere-Prüfschranke: {this.state.analysisResults.data.phWert.oberepruefschrankeValue}</div>
                                                <div style={{ textAlign: 'left' }}>Der Parameterwert unter- oder überschreitet die Prüfwerte: {this.state.analysisResults.data.phWert.istOk ? "Nein" : "Ja"}</div>
                                            </fieldset>

                                            <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>
                                                <legend className="w-auto text-left">Sauerstoffgehalt mg/l</legend>
                                                <div style={{ textAlign: 'left' }}>Sauerstoffgehaltswert: {this.state.analysisResults.data.sauerstoffgehalt.parameterValue}</div>
                                                <div style={{ textAlign: 'left' }}>Untere-Prüfschranke: {this.state.analysisResults.data.sauerstoffgehalt.unterepruefschrankeValue}</div>
                                                <div style={{ textAlign: 'left' }}>Obere-Prüfschranke: {this.state.analysisResults.data.sauerstoffgehalt.oberepruefschrankeValue}</div>
                                                <div style={{ textAlign: 'left' }}>Der Parameterwert unter- oder überschreitet die Prüfwerte: {this.state.analysisResults.data.sauerstoffgehalt.istOk ? "Nein" : "Ja"}</div>
                                            </fieldset>


                                            <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>
                                                <legend className="w-auto text-left">Sauerstoffsättigung mg/l</legend>
                                                <div style={{ textAlign: 'left' }}>Sauerstoffsättigungswert: {this.state.analysisResults.data.sauerstoffsaettigung.parameterValue}</div>
                                                <div style={{ textAlign: 'left' }}>Untere-Prüfschranke: {this.state.analysisResults.data.sauerstoffsaettigung.unterepruefschrankeValue}</div>
                                                <div style={{ textAlign: 'left' }}>Obere-Prüfschranke: {this.state.analysisResults.data.sauerstoffsaettigung.oberepruefschrankeValue}</div>
                                                <div style={{ textAlign: 'left' }}>Der Parameterwert unter- oder überschreitet die Prüfwerte: {this.state.analysisResults.data.sauerstoffsaettigung.istOk ? "Nein" : "Ja"}</div>
                                            </fieldset>






                                            <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>
                                                <legend className="w-auto text-left">Färbung</legend>
                                                <div style={{ textAlign: 'left' }}>Färbungscode: {this.state.analysisResults.data.organoleptischeWerte.faerbung.parameterValue}</div>
                                                <div style={{ textAlign: 'left' }}>Färbung ist bis jetzt vorgefallen: {this.state.analysisResults.data.organoleptischeWerte.faerbung.bereitsVorgefallen ? "Ja" : "Nein"}</div>
                                                <div style={{ whiteSpace: 'pre-line', textAlign: 'left' }}>Färbungen die bis jetzt vorgefallen sind: {this.state.analysisResults.data.organoleptischeWerte.faerbung.vorgefalleneCodes}</div>



                                            </fieldset>

                                            <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>
                                                <legend className="w-auto text-left">Trübung</legend>
                                                <div style={{ textAlign: 'left' }}>Trübungscode: {this.state.analysisResults.data.organoleptischeWerte.truebung.parameterValue}</div>
                                                <div style={{ textAlign: 'left' }}>Trübung ist bis jetzt vorgefallen: {this.state.analysisResults.data.organoleptischeWerte.truebung.bereitsVorgefallen ? "Ja" : "Nein"}</div>
                                                <div style={{ whiteSpace: 'pre-line', textAlign: 'left' }}>Trübungen die bis jetzt vorgefallen sind: {this.state.analysisResults.data.organoleptischeWerte.truebung.vorgefalleneCodes}</div>

                                            </fieldset>


                                            <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>
                                                <legend className="w-auto text-left">Geruch</legend>
                                                <div style={{ textAlign: 'left' }}>Geruchscode: {this.state.analysisResults.data.organoleptischeWerte.geruch.parameterValue}</div>
                                                <div style={{ textAlign: 'left' }}>Geruch ist bis jetzt vorgefallen: {this.state.analysisResults.data.organoleptischeWerte.geruch.bereitsVorgefallen ? "Ja" : "Nein"}</div>
                                                <div style={{ whiteSpace: 'pre-line', textAlign: 'left' }}>Gerüche die bis jetzt vorgefallen sind: {this.state.analysisResults.data.organoleptischeWerte.geruch.vorgefalleneCodes}</div>

                                            </fieldset>

                                            <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>
                                                <legend className="w-auto text-left">Ölfilm</legend>
                                                <div style={{ textAlign: 'left' }}>Ölfilm identifiziert (0 = Nein | 1 = Ja): {this.state.analysisResults.data.organoleptischeWerte.oelfilm.parameterValue}</div>
                                                <div style={{ textAlign: 'left' }}>Ölfilm ist bis jetzt vorgefallen: {this.state.analysisResults.data.organoleptischeWerte.oelfilm.bereitsVorgefallen ? "Ja" : "Nein"}</div>
                                                <div style={{ whiteSpace: 'pre-line', textAlign: 'left' }}>Wie oft ist ein Ölfilm bereits vorgekommen: {this.state.analysisResults.data.organoleptischeWerte.oelfilm.vorgefalleneCodes}</div>

                                            </fieldset>


                                            <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>
                                                <legend className="w-auto text-left">Schaumbildung</legend>
                                                <div style={{ textAlign: 'left' }}>Schaumbildung identifiziert (0 = Nein | 1 = Ja): {this.state.analysisResults.data.organoleptischeWerte.schaumbildung.parameterValue}</div>
                                                <div style={{ textAlign: 'left' }}>Schaumbildung ist bis jetzt vorgefallen: {this.state.analysisResults.data.organoleptischeWerte.schaumbildung.bereitsVorgefallen ? "Ja" : "Nein"}</div>
                                                <div style={{ whiteSpace: 'pre-line', textAlign: 'left' }}>Wie oft ist eine Schaumbildung bereits vorgekommen: {this.state.analysisResults.data.organoleptischeWerte.schaumbildung.vorgefalleneCodes}</div>
                                            </fieldset>


















                                            <fieldset className="border p-2" style={{ marginTop: `20px`, marginBottom: `20px` }}>
                                                <legend className="w-auto text-left">Empfehlung für den Probenehmer</legend>
                                                <div style={{ whiteSpace: 'pre-line', textAlign: 'left' }}>{this.state.analysisResults.data.prologResultsMessage}</div>
                                            </fieldset>

                                        </fieldset>





                                    }




                                </Form>

                            )
                        }


                    </Formik>
                </div>





            </div >
        )






    }
}
export default ProbeComponent