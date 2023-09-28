import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import AuthenticatedRoute from './AuthenticatedRoute.jsx'
import LoginComponent from './LoginComponent.jsx'
import MessstellenListComponent from './MessstellenListComponent.jsx'
import ErrorComponent from './ErrorComponent.jsx'
import WelcomeComponent from './WelcomeComponent.jsx'
import LogoutComponent from './LogoutComponent.jsx'
import FooterComponent from './FooterComponent.jsx'
import HeaderComponent from './HeaderComponent.jsx'
import ProbenListComponent from './ProbenListComponent.jsx'
import ProbeComponent from './ProbeComponent.jsx'
import UploadComponent from './UploadComponent.jsx'
class H2oApp extends Component {
    render() {
        return (
            <div className="H2oApp">
                <Router>
                    <HeaderComponent />
                    <Switch>
                        <Route path="/" exact component={LoginComponent} />
                        <Route path="/login" component={LoginComponent} />
                        <AuthenticatedRoute path="/welcome/:name" component={WelcomeComponent} />
                        <AuthenticatedRoute path="/messstellen/:id/proben/:idnew/parameter" component={ProbeComponent} />
                        <AuthenticatedRoute path="/messstellen/:id/proben" component={ProbenListComponent} />
                        <AuthenticatedRoute path="/messstellen" component={MessstellenListComponent} />
                        <AuthenticatedRoute path="/upload" component={UploadComponent} />
                        <AuthenticatedRoute path="/logout" component={LogoutComponent} />
                        <Route component={ErrorComponent} />
                    </Switch>
                    <FooterComponent />
                </Router>
            </div>
        )
    }
}
export default H2oApp