import React, { Component } from 'react'
import BreadCrumbs from '../../components/breadcrumbs/BreadCrumbs';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import InputBase from '@material-ui/core/InputBase';
import Button from '@material-ui/core/Button';
import RemoveRedEyeIcon from '@material-ui/icons/RemoveRedEye';
import VisibilityOffIcon from '@material-ui/icons/VisibilityOff';
import IconButton from '@material-ui/core/IconButton';
import SearchIcon from '@material-ui/icons/Search';
import AddIcon from '@material-ui/icons/Add';
import './Appointment.css';
import AddDialog from './AddAppointmentDialog';
import { Link } from 'react-router-dom'
import { getDoctors, getAllPatients, getAllAppointment, addNewAppointment } from '../../utils/api'
const data = {
    active:
        [
            { title: "Dashboard", link: "dashboard" }
        ],
    current: "Appointments"
}

class Appointment extends Component {

    constructor(props) {
        super(props);
        this.state = {
            appointment: null,
            isOpenDialogAdd: false,
            doctors: null,
            patients: null
        }
        this.handleToggleDialogAdd = this.handleToggleDialogAdd.bind(this);
        this.addNewReport = this.addNewReport.bind(this);

    }

    handleToggleDialogAdd() {
        this.setState((currentState) => ({
            isOpenDialogAdd: !currentState.isOpenDialogAdd
        }));
    }

    async addNewReport(data) {
        await addNewAppointment(data)
        this.handleToggleDialogAdd()
        this.getAllAppointment()
    }

    componentDidMount() {
        this.getAllAppointment();
    }

    async getAllAppointment() {
        const appointment = await getAllAppointment()
        const doctors = await getDoctors()
        const patients = await getAllPatients()
        this.setState({
            doctors: doctors,
            patients: patients,
            appointment: appointment
        })
    }


    findDoctor(id) {
        const { doctors } = this.state;
        if (doctors) {
            for (let i = 0; i < doctors.length; i++) {
                for (let j = 0; j < doctors[i].records.length; j++) {
                    if (doctors[i].records[j].id == id) {
                        return doctors[i]
                    }
                }
            }
        }
    }

    findPatient(id) {
        const { patients } = this.state;
        console.log(patients)
        if (patients) {
            for (let i = 0; i < patients.length; i++) {
                for (let j = 0; j < patients[i].records.length; j++) {
                    if (patients[i].records[j].id == id) {
                        return patients[i]
                    }
                }
            }
        }
    }

    render() {
        this.findDoctor(3)
        const { appointment, isOpenDialogAdd, doctors, patients } = this.state;
        return <div className="appointment full">
            <BreadCrumbs data={data} />
            {
                isOpenDialogAdd && <AddDialog
                    open={isOpenDialogAdd}
                    handleToggleDialogAdd={this.handleToggleDialogAdd}
                    addNewReport={this.addNewReport}
                />
            }
            <div className="add-area">
                <button className="report" onClick={this.handleToggleDialogAdd}><span>Add</span> <AddIcon /></button>
            </div>
            <section id="appointment-section" className="main-section">
                <TableContainer component={Paper}>
                    <div className="search-area">
                        <InputBase
                            className="seach-bar"
                            placeholder="Search Appointment"
                        />
                        <IconButton type="submit" className="search-button" aria-label="search">
                            <SearchIcon />
                        </IconButton>
                    </div>
                    <Table aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="left">Doctor Name</TableCell>
                                <TableCell align="left">Patient Name</TableCell>
                                <TableCell align="left">Action</TableCell>

                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {appointment && doctors && patients && appointment.length > 0 && appointment.map(a => {
                                return <TableRow>
                                    <TableCell align="left">{this.findDoctor(a.id).name}</TableCell>
                                    <TableCell align="left">{this.findPatient(a.id).name}</TableCell>
                                    <TableCell align="left">
                                        <Link to={`patient-profile/${this.findPatient(a.id).id}`}>
                                            <Button color="primary" size="small" >
                                                <RemoveRedEyeIcon></RemoveRedEyeIcon>
                                            </Button>
                                        </Link>
                                    </TableCell>
                                </TableRow>
                            })}
                        </TableBody>
                    </Table>
                </TableContainer>
            </section>
        </div >
    }
}

export default Appointment;