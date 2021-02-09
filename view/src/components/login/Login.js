import React, { Component } from 'react'
const axios = require('axios')
class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            status: ""
        }
        this.fetchData = this.fetchData.bind(this);
        this.usernameOnChange = this.usernameOnChange.bind(this);
        this.passwordOnChange = this.passwordOnChange.bind(this);
    }


    async fetchData(e) {
        e.preventDefault();
        const { username, password } = this.state
        const req = {
            username: username,
            password: password
        }
        try {
            const res = await axios.post('http://localhost:8090/login', req);
            if (res.data.isAuthen) {
                this.setState({
                    status: "correct"
                })
            } else {
                this.setState({
                    status: "wrong"
                })
            }
        } catch (err) {
            // Handle Error Here
            console.error(err);
        }
    }

    usernameOnChange() {
        const username_input = document.querySelector("#username").value;
        this.setState({
            username: username_input
        })
    }

    passwordOnChange() {
        const password_input = document.querySelector("#password").value;
        this.setState({
            password: password_input
        })
    }

    render() {
        return <div className='Login'>
            <form >
                <div className="username_block">
                    <label htmlFor="username">Username</label>
                    <input type="text" name="username" id="username" onInput={this.usernameOnChange} />
                </div>
                <div className="password_block">
                    <label htmlFor="password">Password</label>
                    <input type="password" name="password" id="password" onInput={this.passwordOnChange} />
                </div>
                <div className="button_block">
                    <button className="login_button" onClick={this.fetchData}>Login</button>
                </div>
                {/* test */}
                <div className="status">{this.state.status}</div>
            </form>
        </div>
    }
}

export default Login