import React, { Component } from 'react'
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import './Login.css'
import Button from '@material-ui/core/Button';
import { Link } from 'react-router-dom';
import { login } from '../../utils/api';
import { withRouter } from "react-router";

const axios = require('axios');

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            usernameOrEmail: "",
            password: "",
            status: ""
        }
        this.handleInput = this.handleInput.bind(this);
        this.handleOnSubmit = this.handleOnSubmit.bind(this);
    }

    handleInput(type, e) {
        this.setState({
            [type]: e.target.value
        })
    }

    async handleOnSubmit(e) {
        e.preventDefault();
        const { usernameOrEmail, password } = this.state;
        const { setUser } = this.props;
        const result = await login({ usernameOrEmail, password });
        if (result.accessToken) {
            localStorage.setItem('token', result.accessToken);
            setUser(result.user, this.props.history.push('/'))
        } else if (result == 401) {
            this.setState({ status: "Wrong username/email or password" })
        }
    }

    render() {
        const { status } = this.state;
        return <div className="login">
            <form className="form" onSubmit={this.handleOnSubmit}>
                <Typography variant="h4" gutterBottom>
                    Login HANU+
                </Typography>
                <div className="flex-center">
                    <p style={{ padding: '10px 0px', color: 'red' }}>{status}</p>
                </div>
                <TextField id="outlined-basic" label="Username Or Email" variant="outlined" onInput={(e) => { this.handleInput("usernameOrEmail", e) }} />
                <TextField id="outlined-basic" label="Password" type="password" variant="outlined" onInput={(e) => { this.handleInput("password", e) }} />
                <Button variant="contained" id="green-text" type="submit">
                    Log In
                </Button>
            </form>
        </div>
    }
}

export default withRouter(Login)