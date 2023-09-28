import React, { Component } from 'react'
import H2oService from '../../api/h2o/H2oService.js'
import AuthenticationService from './AuthenticationService.js'
import moment from 'moment'



class ProbenListComponent extends Component {



    constructor(props) {
        console.log('constructor')

        super(props)
        this.state = {
            proben:
                [
                    //when calling API it is better to not call the API in the Constructor
                    //it is better to call the constructor in   componentDidMount()
                ],
            message: null
        }

        this.viewProbeClicked = this.viewProbeClicked.bind(this)

        this.refreshProben = this.refreshProben.bind(this)
    }


    componentDidMount() {

        console.log('componentDidMount')
        this.refreshProben()
        console.log(this.state)


    }



    refreshProben() {

        const id = this.props.match.params.id;
        H2oService.retrieveAllProbenFromMesstelle(id)
            .then(
                response => {
                    this.setState({ proben: response.data })
                }


            )


    }





    viewProbeClicked(pid) {

        //get id from link

        const oldid = this.props.match.params.id;

        this.props.history.push(`/messstellen/${oldid}/proben/${pid}/parameter`)

    }

    render() {
        console.log('Render')
        return (
            <div>
                <h1>Proben</h1>
                {this.state.message && <div className="alert alert-success"> {this.state.message}</div>}
                <div className="container overflow-auto">

                    <table className="table">
                        <thead>
                            <tr>

                                <th>Probe-NR</th>
                                <th>Turnus-NR</th>
                                <th>Probenzuordnungs-ID</th>
                                <th>Probedatum</th>
                                <th>Probe betrachten</th>
                            </tr>
                        </thead>
                        <tbody >
                            {
                                this.state.proben.map(
                                    probe =>
                                        <tr key={probe.id}>

                                            <td>{probe.probenr}</td>
                                            <td>{probe.turnusnr}</td>
                                            <td>{probe.probenzuordnungid}</td>
                                            <td>{moment(probe.probedatum).format('YYYY-MM-DD')}</td>
                                            <td><button style={{ marginBottom: `20px` }} className="btn btn-info" onClick={() => this.viewProbeClicked(probe.id)}>Probe betrachten</button></td>

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

export default ProbenListComponent