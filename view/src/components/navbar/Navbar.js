import React, { Component } from 'react';
import './Navbar.css';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import { Link } from 'react-router-dom'
import { checkStillLogin } from '../../utils/utils'
class Navbar extends Component {
    render() {
        return <nav className="main-nav">
            <div className="logo">
                <Link to="/dashboard">
                    <i className="fas fa-hospital-symbol"></i>
                    <span>HANU HOSPITAL</span>
                </Link>
            </div>
            <div className="actions" onClick={() => { localStorage.removeItem("token"); }}>
                {checkStillLogin() == true ? <Link to="/login">
                    <ExitToAppIcon color="secondary" />
                </Link> : <></>}

            </div>
        </nav>
    }
}

export default Navbar;