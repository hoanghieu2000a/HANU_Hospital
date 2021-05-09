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
import IconButton from '@material-ui/core/IconButton';
import SearchIcon from '@material-ui/icons/Search';
import AddIcon from '@material-ui/icons/Add';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import {
    getDoctors, getReps, addDoctor, addRep,
    deleteRep, deleteDoc, getOneRep, getOneDoctor,
    editDoctor, editNurse
} from '../../utils/api';
import AddEmployeeDialog from './AddEmployeeDialog'
import EditEmployeeDialog from './EditEmployeeDialog'
const data = {
    active:
        [
            { title: "Dashboard", link: "dashboard" }
        ],
    current: "Employee"
}


class Employee extends Component {

    constructor(props) {
        super(props)
        this.state = {
            doctors: null,
            reps: null,
            isOpenDialogAdd: false,
            isOpenDialogEdit: false,
            selectedEmployee: null,
        }
        this.handleToggleDialogAdd = this.handleToggleDialogAdd.bind(this);
        this.handleToggleDialogEdit = this.handleToggleDialogEdit.bind(this);
        this.addNewEmployee = this.addNewEmployee.bind(this);
        this.editEmployee = this.editEmployee.bind(this);
    }

    async addNewEmployee(data) {
        const { job, ...restOfDoctor } = data;
        if (job === 'doctor') {
            await addDoctor({ ...restOfDoctor })
        } else if (job === 'receptionist') {
            const { speciality, ...restOfRec } = { ...restOfDoctor }
            await addRep({ ...restOfRec })
        }
        this.handleToggleDialogAdd();
        this.getAllDoctors();
        this.getAllReps();
    }

    async editEmployee(data) {
        console.log({ data })
        if (data.speciality) {
            await editDoctor(data.id, data)
        } else {
            await editNurse(data.id, data)
        }
        this.handleToggleDialogEdit();
        this.getAllDoctors();
        this.getAllReps();
    }


    componentDidMount() {
        this.getAllDoctors();
        this.getAllReps();
    }

    handleToggleDialogAdd() {
        this.setState((currentState) => ({
            isOpenDialogAdd: !currentState.isOpenDialogAdd
        }));
    }

    handleToggleDialogEdit() {
        this.setState((currentState) => ({
            isOpenDialogEdit: !currentState.isOpenDialogEdit
        }));
    }

    getAllDoctors = async () => {
        const doctors = await getDoctors();
        this.setState({
            doctors: doctors
        })
    }

    getAllReps = async () => {
        const reps = await getReps();
        this.setState({
            reps: reps
        })
    }

    deleteRep = async (id) => {
        await deleteRep(id)
        this.getAllDoctors();
        this.getAllReps();
    }

    deleteDoc = async (id) => {
        await deleteDoc(id)
        this.getAllDoctors();
        this.getAllReps();
    }

    getSelectedEmployee = async (type, id) => {
        const { doctors, reps } = this.state;
        let selectedEmployee = null;
        if (type === "doctor") {
            const result = doctors.filter(d => d.id === id)
            selectedEmployee = result[0]
        } else {
            const result = reps.filter(r => r.id === id)
            selectedEmployee = result[0]
        }
        this.setState({
            selectedEmployee: selectedEmployee
        })
    }



    render() {
        const { doctors, reps, isOpenDialogAdd, isOpenDialogEdit, selectedEmployee } = this.state;
        return <div className="employee full">
            <BreadCrumbs data={data} />
            {
                isOpenDialogAdd && <AddEmployeeDialog
                    open={isOpenDialogAdd}
                    handleToggleDialogAdd={this.handleToggleDialogAdd}
                    addNewEmployee={this.addNewEmployee}
                />
            }
            {
                isOpenDialogEdit && <EditEmployeeDialog
                    open={isOpenDialogEdit}
                    handleToggleDialogEdit={this.handleToggleDialogEdit}
                    editEmployee={this.editEmployee}
                    employee={selectedEmployee}
                />
            }
            <div className="add-area">
                <button className="report" onClick={this.handleToggleDialogAdd}><span>Add</span> <AddIcon /></button>
            </div>
            <section id="employee-section" className="main-section">
                <TableContainer component={Paper}>
                    <div className="search-area">
                        <InputBase
                            className="seach-bar"
                            placeholder="Search Employee"
                        />
                        <IconButton type="submit" className="search-button" aria-label="search">
                            <SearchIcon />
                        </IconButton>
                    </div>
                    <Table aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="left">Employee Name</TableCell>
                                <TableCell align="left">Job</TableCell>
                                <TableCell align="left">D.O.B</TableCell>
                                <TableCell align="left">Phone</TableCell>
                                <TableCell align="left">Email</TableCell>
                                <TableCell align="left">Salary</TableCell>
                                <TableCell align="left">Speciality</TableCell>
                                <TableCell align="left">Action</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {/*  */}
                            {doctors && doctors.map(d => {
                                return <TableRow key={d.id}>
                                    <TableCell align="left">{d.name}</TableCell>
                                    <TableCell align="left">Doctor</TableCell>
                                    <TableCell align="left">{d.dob}</TableCell>
                                    <TableCell align="left">{d.phone}</TableCell>
                                    <TableCell align="left">{d.email}</TableCell>
                                    <TableCell align="left">{d.salary}</TableCell>
                                    <TableCell align="left">{d.speciality}</TableCell>
                                    <TableCell align="left">
                                        <IconButton aria-label="edit" onClick={() => { this.handleToggleDialogEdit(); this.getSelectedEmployee('doctor', d.id) }}>
                                            <EditIcon fontSize="small" className="edit-button" />
                                        </IconButton>
                                    </TableCell>
                                </TableRow>
                            })}

                            {reps && reps.map(r => {
                                return <TableRow key={r.id}>
                                    <TableCell align="left">{r.name}</TableCell>
                                    <TableCell align="left">Nurse</TableCell>
                                    <TableCell align="left">{r.dob}</TableCell>
                                    <TableCell align="left">{r.phone}</TableCell>
                                    <TableCell align="left">{r.email}</TableCell>
                                    <TableCell align="left">{r.salary}</TableCell>
                                    <TableCell align="left"></TableCell>
                                    <TableCell align="left">
                                        <IconButton aria-label="edit" onClick={() => { this.handleToggleDialogEdit(); this.getSelectedEmployee('nurse', r.id) }}>
                                            <EditIcon fontSize="small" className="edit-button" />
                                        </IconButton>
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

export default Employee;