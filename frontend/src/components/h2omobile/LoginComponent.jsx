import React, { Component } from 'react'
import AuthenticationService from './AuthenticationService.js'
class LoginComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: 'admin',
            password: 'test',
            hasLoginFailed: false,
            showSuccessMessage: false
        }
        this.handleChange = this.handleChange.bind(this)
        this.loginClicked = this.loginClicked.bind(this)

    }
    handleChange(event) {
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }
    loginClicked() {
        //admin,test
        if (this.state.username === 'admin' && this.state.password === 'test') {
            AuthenticationService.registerSuccessfulLogin(this.state.username, this.state.password)
            this.props.history.push(`/welcome/${this.state.username}`)
        }
        else {
            console.log('False')
            this.setState({ showSuccessMessage: false })
            this.setState({ hasLoginFailed: true })
        }
    }
    render() {
        return (
            <div>
                <h1>Login</h1>
                <div className="container"  >
                    {this.state.hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
                    <div>   <br></br>  </div>
                    {/*https://getbootstrap.com/docs/5.0/forms/overview/*/}

                    {/*https://getbootstrap.com/docs/4.0/utilities/text*/}
                    <form >
                        <div class="form-group">
                            <div class="text-left">
                                <label  >Login:</label>
                            </div>
                            <input type="text" class="form-control" name="username" value={this.state.username} onChange={this.handleChange} />
                        </div>
                        <div class="form-group">
                            <div class="text-left">
                                <label  >Kennwort:</label>
                            </div>
                            <input type="password" class="form-control" name="password" value={this.state.password} onChange={this.handleChange} />
                        </div>
                        <button className="btn btn-success" onClick={this.loginClicked}>Login</button>
                    </form>
                </div>
            </div >
        )
    }
}
export default LoginComponent