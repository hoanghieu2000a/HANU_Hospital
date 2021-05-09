import React, { Component } from 'react'
import BreadCrumbs from '../../components/breadcrumbs/BreadCrumbs';
import { Typography } from '@material-ui/core';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import userImage from '../../images/user.png';
import TextField from '@material-ui/core/TextField';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Radio from '@material-ui/core/Radio';
import './EmployeeProfile.css'
const data = {
    active:
        [
            { title: "Dashboard", link: "dashboard" },
            { title: "Employee", link: "employee" },
        ],
    current: "Employee-Profile"
}

class EmployeeProfile extends Component {
    render() {
        return <div className="employee-profie full">
            <BreadCrumbs data={data} />
            <section id="employee-profile-section">
                <aside>
                    <div id="employee-info">
                        <img src={userImage} alt="user-avatar" />
                        <List component="nav" aria-label="mailbox folders">
                            <ListItem >
                                <Typography variant="h5" color="primary">
                                    Doctor Name
                                </Typography>
                            </ListItem>
                        </List>
                    </div>
                </aside>
                <main>
                    <Typography variant="h6" color="primary">
                        Basic Info
                    </Typography>
                    <TextField id="outlined-basic" label="Outlined" variant="outlined" size="small" disabled />
                    <TextField id="outlined-basic" label="Outlined" variant="outlined" size="small" disabled />
                    <RadioGroup aria-label="gender" name="gender1" >
                        <FormControlLabel value="male" control={<Radio />} label="Male" checked />
                        <FormControlLabel value="female" control={<Radio />} label="Female" disabled />
                    </RadioGroup>
                    <TextField id="outlined-basic" label="Outlined" variant="outlined" size="small" disabled />
                    <TextField id="outlined-basic" label="Outlined" variant="outlined" size="small" disabled />
                    <TextField id="outlined-basic" label="Outlined" variant="outlined" size="small" disabled />
                </main>
            </section>
        </div>
    }
}
export default EmployeeProfile