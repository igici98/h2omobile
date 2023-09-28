import axios from "axios"
import { API_URL, JPA_API_URL } from '../../Constants'

class H2oService {
    retrieveAllmesstellen() {
        return axios.get(`${API_URL}/messstellen`)
    }
    //retrieve probe or sample-taking data
    retrieveMesstelleById(messstellenid) {
        return axios.get(`${API_URL}/messstellen/${messstellenid}`)
    }
    retrieveAllProbenFromMesstelle(id) {
        return axios.get(`${API_URL}/messstellen/${id}/proben`)
    }
    //Retrieve all Probedaten and Parameter for specific Probe
    retrieveAllParameterFromProbe(probenid, messstellenid) {
        return axios.get(`${API_URL}/messstellen/${messstellenid}/proben/${probenid}/parameter`)

    }
    //retrieve probe or sample-taking data
    retrieveProbe(probenid) {
        return axios.get(`${API_URL}/proben/${probenid}`)

    }
    //execute classic wateranalysis mit return von analyse Object
    executeWateranalysisClassic(messstellenid, temperaturNeu, leitfaehigkeitNeu, phwertNeu, sauerstoffgehaltNeu,
        sauerstoffsaettigungNeu, iteration, faerbung, truebung, geruch, oelfilm, schaumbildung) {

        return axios.get(`${API_URL}/wasseranalyse/${messstellenid}/${temperaturNeu}/${leitfaehigkeitNeu}/${phwertNeu}/${sauerstoffgehaltNeu}/${sauerstoffsaettigungNeu}/${iteration}/${faerbung}/${truebung}/${geruch}/${oelfilm}/${schaumbildung}`)
    }
    //create new Probe
    createProbedaten(id, h2oProbe) {
        return axios.post(`${API_URL}/messstellen/${id}/proben`, h2oProbe)
    }
    //upload new data
    uploadData(uploadData) {
        return axios.post(`${API_URL}/upload`, uploadData, {
            headers: {
                'Content-type': 'multipart/form-data',
            },
        });
    }


    //execute classic wateranalysis ohne Rückgabeparameter, analyse wird berechnet im backend
    //
    /*
        executeWateranalysisClassic(messstellenid, temperaturNeu, leitfaehigkeitNeu, phwertNeu, sauerstoffgehaltNeu,
            sauerstoffsaettigungNeu) {
    
            axios.get(`${API_URL}/wasseranalyse/${messstellenid}/${temperaturNeu}/${leitfaehigkeitNeu}/${phwertNeu}/${sauerstoffgehaltNeu}/${sauerstoffsaettigungNeu}`)
    
            // http://localhost:8080/wasseranalyse/8ae5e2f31a15a9e4011a15aa06f900a0/1/1/1/1/1
        }
    */







    /*
    retrieveAllTodos(name) {
        return axios.get(`${JPA_API_URL}/users/${name}/todos`)

    }

    retrieveTodo(name, id) {
        return axios.get(`${JPA_API_URL}/users/${name}/todos/${id}`)

    }

    deleteTodo(name, id) {
        return axios.delete(`${JPA_API_URL}/users/${name}/todos/${id}`)

    }

    updateTodo(name, id, todo) {
        return axios.put(`${JPA_API_URL}/users/${name}/todos/${id}`, todo)

    }

    createTodo(name, todo) {
        return axios.post(`${JPA_API_URL}/users/${name}/todos`, todo)

    }
    */







}

export default new H2oService()