import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import { withRouter } from 'react-router';
import AuthenticationService from './AuthenticationService.js'

class HeaderComponent extends Component {




    render() {

        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
        // const loggedUser = AuthenticationService.getLoggedInUsername();
        // console.log(isUserLoggedIn);

        return (

            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-primary">
                    <div><a href="https://wasser.umweltbundesamt.at/h2odb/" className="navbar-brand">H2O-DB</a></div>
                    <ul className="navbar-nav">
                        {isUserLoggedIn && <li ><Link className="nav-link" to={`/welcome/admin`}>Home</Link></li>}
                        {isUserLoggedIn && <li ><Link className="nav-link" to="/messstellen">Probe hinzufügen</Link></li>}
                        {isUserLoggedIn && <li ><Link className="nav-link" to="/upload">Daten hochladen</Link></li>}
                    </ul>
                    <ul className="navbar-nav navbar-collapse justify-content-end">
                        {!isUserLoggedIn && <li ><Link className="nav-link" to="/login">Login</Link></li>}
                        {isUserLoggedIn && <li ><Link className="nav-link" to="/logout" onClick={AuthenticationService.logout}>Logout</Link></li>}
                    </ul>
                </nav>
            </header>


        )
    }

}



export default withRouter(HeaderComponent)