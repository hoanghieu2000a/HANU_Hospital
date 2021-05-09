import React, { Component } from 'react'
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import { Link } from 'react-router-dom';
import { register, addDoctor, addRep } from '../../utils/api';
import { withRouter } from "react-router";
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            username: "",
            phoneNumber: "",
            address: "",
            password: "",
            status: "",
            role: ""
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
        const { name, username, phoneNumber, address, password } = this.state;
        const role = this.roleFormat()
        const result = await register({ name, username, phoneNumber, address, password, role });
        if (result.success === true) {
            console.log("move")
            this.props.history.push('/account');
            alert("Register successfully");
        }
    }

    roleFormat() {
        const { role } = this.state;
        switch (role) {
            case "doctor":
                return { id: "3", name: "ROLE_ADMIN" }
            case "nurse":
                return { id: "2", name: "ROLE_NURSE" }
        }
    }


    render() {
        const { role } = this.state;
        return <div className="login register">
            <form className="form" onSubmit={this.handleOnSubmit}>
                <Typography variant="h4" gutterBottom>
                    Register HANU+
                </Typography>
                <TextField id="outlined-basic" label="Name" variant="outlined" onInput={(e) => { this.handleInput("name", e) }} />
                <TextField id="outlined-basic" label="Username" variant="outlined" onInput={(e) => { this.handleInput("username", e) }} />
                <TextField id="outlined-basic" label="Phonenumber" variant="outlined" type="number" onInput={(e) => { this.handleInput("phoneNumber", e) }} />
                <TextField id="outlined-basic" label="Address" variant="outlined" onInput={(e) => { this.handleInput("address", e) }} />
                <TextField id="outlined-basic" label="Password" variant="outlined" type="password" onInput={(e) => { this.handleInput("password", e) }} />
                <select id="roles" name="cars" onChange={(e) => { this.handleInput("role", e) }}>
                    <option value="nurse">Nurse</option>
                    <option value="doctor">Doctor</option>
                </select>
                <Button variant="contained" id="green-text" type="submit" >
                    Sign Up
                </Button>
            </form>

        </div>
    }
}

export default withRouter(Register);