import React, { Component } from 'react'




class WelcomeComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            welcomeMessage: ''

        }

    }
    render() {
        return (

            <div>
                <h1>Willkommen!</h1>
                < div className="container">
                    Sehr geehrte/er Frau/Herr {this.props.match.params.name} der H2O Mobile Prototyp begrüßt Sie ganz herzlich!

                </div >



            </div>
        )
    }



}

export default WelcomeComponent