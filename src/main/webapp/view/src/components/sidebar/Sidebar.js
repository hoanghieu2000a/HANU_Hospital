import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Divider from '@material-ui/core/Divider';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import './Sidebar.css'
import HomeIcon from '@material-ui/icons/Home';
import CalendarTodayIcon from '@material-ui/icons/CalendarToday';
import AccessibleForwardIcon from '@material-ui/icons/AccessibleForward';
import SupervisorAccountIcon from '@material-ui/icons/SupervisorAccount';
import BuildIcon from '@material-ui/icons/Build';
import LocalHospitalIcon from '@material-ui/icons/LocalHospital';
import AmpStoriesIcon from '@material-ui/icons/AmpStories';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import MonetizationOnIcon from '@material-ui/icons/MonetizationOn';
import BusinessCenterIcon from '@material-ui/icons/BusinessCenter';
const roles = {
    admin: "ROLE_ADMIN",
    nurse: "ROLE_NURSE",
    doctor: "ROLE_DOCTOR"
}

const menus = [
    {
        title: "Dashboard",
        icon: <HomeIcon />,
        link: "/dashboard",
        access: [roles.admin, roles.nurse, roles.doctor]
    },
    {
        title: "Appointments",
        icon: <CalendarTodayIcon />,
        link: "/appointments",
        access: [roles.nurse, roles.doctor, roles.admin]

    },
    {
        title: "Patients",
        icon: <AccessibleForwardIcon />,
        link: "/patients",
        access: [roles.admin, roles.nurse, roles.doctor]

    },
    {
        title: "Employee",
        icon: <SupervisorAccountIcon />,
        link: "/employee",
        access: [roles.admin]

    },
    {
        title: "Equipment",
        icon: <BuildIcon />,
        link: "/equipment",
        access: [roles.admin, roles.nurse]

    },
    {
        title: "Services",
        icon: <BusinessCenterIcon />,
        link: "/services",
        access: [roles.admin]

    },
    {
        title: "Medicine",
        icon: <LocalHospitalIcon />,
        link: "/medicine",
        access: [roles.admin, roles.nurse]

    },
    {
        title: "Department",
        icon: <AmpStoriesIcon />,
        link: "/department",
        access: [roles.admin]

    },
    {
        title: "Account",
        icon: <AccountCircleIcon />,
        link: "/account",
        access: [roles.admin]

    },
    {
        title: "Accounting",
        icon: <MonetizationOnIcon />,
        link: "/bill",
        access: [roles.admin]
    },

]

const temp_url = "https://www.wrraptheme.com/templates/lucid/hospital/assets/images/user.png";

class Sidebar extends Component {

    render() {
        const { role, user } = this.props;
        return <aside className="side-bar">
            <div className="user-account">
                <img src={temp_url} alt="doctor-img" />
                <div>
                    <p>Welcome ,</p>
                    <a href="user" className="doctor-anchor">{`${user.name}`}</a>
                </div>
            </div>
            <Divider />
            <List>
                {menus.map((menu, index) => {

                    return (menu.access.includes(role) && <ListItem button key={menu.title}>
                        <Link className="side-nav" to={menu.link}>
                            <ListItemIcon>{menu.icon}</ListItemIcon>
                            <ListItemText primary={menu.title} />
                        </Link>
                    </ListItem>)
                })}
            </List>
        </aside>
    }
}

export default Sidebar