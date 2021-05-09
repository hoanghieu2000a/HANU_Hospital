import React, { Component } from 'react'
import Navbar from './components/navbar/Navbar'
import Sidebar from './components/sidebar/Sidebar'
import Patient from './pages/patient/Patient'
import PatientProfile from './pages/patient-profile/PatientProfile'
import Appointment from './pages/appointments/Appointment'
import Login from './pages/login/Login'
import Employee from './pages/employee/Employee'
import Equipment from './pages/equipment/Equipment'
import EmployeeProfile from './pages/employee-profile/EmployeeProfile'
import Medicine from './pages/medicine/Medicine'
import Register from './pages/register/Register'
import ProtectedRoute from './routes/ProtectedRoute'
import LoginRoute from './routes/LoginRoute'
import Department from './pages/department/Department'
import Accounts from './pages/accounts/Accounts'
import Service from './pages/service/Service'
import Bill from './pages/bill/Bill'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import { getAccount } from './utils/api'
import { getCookie } from './utils/utils'
import jwt_decode from "jwt-decode";
import './App.css'

const roles = {
  admin: "ROLE_ADMIN",
  nurse: "ROLE_NURSE",
  doctor: "ROLE_DOCTOR"
}

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      user: { role: "guest" },
    }
    this.setUser = this.setUser.bind(this);
  }

  async componentDidMount() {
    if (localStorage.getItem("token")) {
      const decode = jwt_decode(localStorage.getItem("token"))
      const user = await getAccount(decode.sub)
      this.setUser(user[0])
    }
  }

  setUser(user, callback) {
    this.setState({ user: user }, callback);
  }

  render() {
    const { user } = this.state;
    return <div className="app">

      {user && <Router>
        <Navbar />
        <section id="main-section">
          <Switch>
            <LoginRoute exact path="/login">
              <Login setUser={this.setUser} />
            </LoginRoute>
            <ProtectedRoute access={[roles.admin]} role={user.role.name} path="/signup">
              <Register />
            </ProtectedRoute>
            <ProtectedRoute exact path="/(|dashboard)/">
              <Sidebar user={user} role={user.role.name} />
            </ProtectedRoute>
            <ProtectedRoute exact path="/appointments">
              <Sidebar user={user} role={user.role.name} />
              <Appointment />
            </ProtectedRoute>
            <ProtectedRoute exact path="/patients">
              <Sidebar user={user} role={user.role.name} />
              <Patient />
            </ProtectedRoute>
            <ProtectedRoute exact path="/patient-profile/:id">
              <Sidebar user={user} role={user.role.name} />
              <PatientProfile />
            </ProtectedRoute>
            <ProtectedRoute access={[roles.admin]} role={user.role.name} exact path="/employee">
              <Employee />
              <Sidebar user={user} role={user.role.name} />
            </ProtectedRoute>
            <ProtectedRoute access={[roles.admin, roles.nurse]} role={user.role.name} exact path="/equipment">
              <Sidebar user={user} role={user.role.name} />
              <Equipment />
            </ProtectedRoute>
            <ProtectedRoute access={[roles.admin]} role={user.role.name} exact path="/employee-profile">
              <Sidebar user={user} role={user.role.name} />
              <EmployeeProfile />
            </ProtectedRoute>
            <ProtectedRoute access={[roles.admin, roles.nurse]} role={user.role.name} exact path="/medicine">
              <Sidebar user={user} role={user.role.name} />
              <Medicine />
            </ProtectedRoute>
            <ProtectedRoute access={[roles.admin]} role={user.role.name} exact path="/department">
              <Sidebar user={user} role={user.role.name} />
              <Department />
            </ProtectedRoute>
            <ProtectedRoute access={[roles.admin]} role={user.role.name} exact path="/account">
              <Sidebar user={user} role={user.role.name} />
              <Accounts />
            </ProtectedRoute>
            <ProtectedRoute access={[roles.admin]} role={user.role.name} exact path="/services">
              <Sidebar user={user} role={user.role.name} />
              <Service />
            </ProtectedRoute>
            <ProtectedRoute access={[roles.admin]} role={user.role.name} exact path="/bill">
              <Sidebar user={user} role={user.role.name} />
              <Bill />
            </ProtectedRoute>
          </Switch>
        </section>
      </Router>}
    </div >
  }
}

export default App;
