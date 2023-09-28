import React, { Component } from 'react'
import H2oService from '../../api/h2o/H2oService.js'
class MessstellenListComponent extends Component {
    constructor(props) {
        console.log('constructor')
        super(props)
        this.state = {
            messstellen:
                [   //when calling API it is better to not call the API in the Constructor
                    //it is better to call the constructor in   componentDidMount()
                ],
            message: null
        }
        this.viewProbenClicked = this.viewProbenClicked.bind(this)
        this.addProbeClicked = this.addProbeClicked.bind(this)
        this.refreshMessstellen = this.refreshMessstellen.bind(this)
    }
    componentDidMount() {
        console.log('componentDidMount')
        this.refreshMessstellen()
        console.log(this.state)
    }
    refreshMessstellen() {
        H2oService.retrieveAllmesstellen()
            .then(
                response => {
                    this.setState({ messstellen: response.data })
                }
            )
    }
    addProbeClicked(id) {
        this.props.history.push(`/messstellen/${id}/proben/-1/parameter`)
    }
    viewProbenClicked(id) {
        this.props.history.push(`/messstellen/${id}/proben`)
    }
    render() {
        console.log('Render')
        return (
            <div>
                <h1>Messstellen</h1>
                {this.state.message && <div className="alert alert-success"> {this.state.message}</div>}
                <div className="container overflow-auto">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Messobjekt-ID</th>
                                <th>Name</th>
                                <th>Gewässertyp</th>
                                <th>Aktiv</th>
                                <th>Proben betrachten</th>
                                <th>Neue Probe hinzufügen</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.messstellen.map(
                                    messstelle =>
                                        <tr key={messstelle.id}>
                                            <td>{messstelle.mobjwgevid}</td>
                                            <td>{messstelle.mobjname}</td>
                                            <td>{messstelle.mobjart}</td>
                                            <td>{messstelle.aktivjn}</td>
                                            <td><button className="btn btn-primary" onClick={() => this.viewProbenClicked(messstelle.id)}>Proben betrachten</button></td>
                                            <td><button className="btn btn-info" onClick={() => this.addProbeClicked(messstelle.id)}>Probe hinzufügen</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div >
        )
    }
}
export default MessstellenListComponent