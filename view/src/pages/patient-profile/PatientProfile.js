import React, { Component } from 'react'
import BreadCrumbs from '../../components/breadcrumbs/BreadCrumbs';
import AddDialog from './AddDialog';
import './PatientProfile.css';
import { Typography } from '@material-ui/core';
import Divider from '@material-ui/core/Divider';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import TimeLine from '../../components/timeline/Timeline'
import userImage from '../../images/user.png';
import AddIcon from '@material-ui/icons/Add';
import { getRecordByPatientId, getPatientByID, getDoctors } from '../../utils/api'
const breadcrumbs = {
    active:
        [
            { title: "Dashboard", link: "dashboard" },
            { title: "Patients", link: "patients" },
        ],
    current: "Patient-Profile"
}

class PatientProfile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpenDialogAdd: false,
            records: null,
            patient: null,
            doctors: null
        }
        this.handleToggleDialogAdd = this.handleToggleDialogAdd.bind(this);
        this.findDoctor = this.findDoctor.bind(this);
        this.getAll = this.getAll.bind(this);

    }

    handleToggleDialogAdd() {
        this.setState((currentState) => ({
            isOpenDialogAdd: !currentState.isOpenDialogAdd
        }));
    }

    addNewReport(data) {
    }

    componentDidMount() {
        this.getAll()
    }

    getAll() {
        this.getPatientByID()
        this.getRecordByPatientId()
        this.getAllDoctor()
    }

    async getPatientByID() {
        const id = window.location.href.split("/").reverse()[0]
        const patient = await getPatientByID(id);
        this.setState({
            patient: patient
        })
    }

    async getRecordByPatientId() {
        const id = window.location.href.split("/").reverse()[0]
        const records = await getRecordByPatientId(id);
        this.setState({
            records: records
        })
    }

    async getAllDoctor() {
        const doctors = await getDoctors()
        this.setState({
            doctors: doctors
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

    dynamicSort(property) {
        return function (a, b) {
            return (a[property] < b[property]) ? 1 : (a[property] > b[property]) ? -1 : 0;
        }
    }



    render() {
        const { isOpenDialogAdd, records, patient } = this.state;
        return <div className="patient-profile full">
            <BreadCrumbs data={breadcrumbs} />
            {
                isOpenDialogAdd && <AddDialog
                    open={isOpenDialogAdd}
                    handleToggleDialogAdd={this.handleToggleDialogAdd}
                    addNewReport={this.addNewReport}
                />
            }
            <section id="patient-profile-section">
                <aside>
                    <div id="patient-info">
                        <img src={userImage} alt="user-avatar" />
                        {patient && <List component="nav" aria-label="mailbox folders">
                            <ListItem >
                                <Typography variant="h5" color="primary">
                                    {patient.name}
                                </Typography>
                            </ListItem>
                            <Divider />
                            <ListItem >
                                <Typography variant="h6" >
                                    DOB
                                </Typography>
                                <Typography  >
                                    {patient.dob}
                                </Typography>
                            </ListItem>
                            <ListItem >
                                <Typography variant="h6" >
                                    Phone
                                </Typography>
                                <Typography  >
                                    {patient.phoneNumber}
                                </Typography>
                            </ListItem>
                            <ListItem >
                                <Typography variant="h6" >
                                    Address
                                </Typography>
                                <Typography  >
                                    {patient.address}
                                </Typography>
                            </ListItem>
                        </List>}
                    </div>

                </aside>
                <main>
                    <Typography variant="h6" color="primary">
                        Activity
                    </Typography>
                    {records && <TimeLine records={records.sort(this.dynamicSort('id'))} findDoctor={this.findDoctor} getAll={this.getAll} />}
                </main>
            </section>
        </div>
    }
}
export default PatientProfile;