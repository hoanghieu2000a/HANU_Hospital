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
import IconButton from '@material-ui/core/IconButton';
import SearchIcon from '@material-ui/icons/Search';
import AddIcon from '@material-ui/icons/Add';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import { Link } from 'react-router-dom';
import AddPatientDialog from './AddPatientDialog';
import { addPatient, getAllPatients, deletePatient, editPatient } from '../../utils/api'
import './Patient.css'
import EditPatientDialog from './EditPatientDialog';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import TablePagination from '@material-ui/core/TablePagination';

const data = {
    active:
        [
            { title: "Dashboard", link: "dashboard" }
        ],
    current: "Patients"
}

class Patient extends Component {

    constructor(props) {
        super(props);
        this.state = {
            patients: null,
            currentPatient: null,
            isOpenDialogAdd: false,
            isOpenDialogEdit: false,
            page: 0,
            rowsPerPage: 5,
            orderBy: 'name',
            orderDirection: 'asc',
            headCells: [
                { id: 'name', label: 'Patient Name', sortable: true },
                { id: 'gender', label: 'Gender' },
                { id: 'dob', label: 'DOB', sortable: true },
                { id: 'address', label: 'Address' },
                { id: 'phoneNumber', label: 'Number' },
                { id: 'action', label: 'Action' },
            ],
        }
        this.handleToggleDialogAdd = this.handleToggleDialogAdd.bind(this);
        this.handleToggleDialogEdit = this.handleToggleDialogEdit.bind(this);
        this.getAllPatients = this.getAllPatients.bind(this);
        this.deletePatient = this.deletePatient.bind(this);
        this.addNewPatient = this.addNewPatient.bind(this);
        this.editPatient = this.editPatient.bind(this);
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

    async addNewPatient(data) {
        const patient = await addPatient(data);
        this.handleToggleDialogAdd();
        if (patient) {
            this.getAllPatients();
        }
    }

    async editPatient(id, data) {
        await editPatient(id, data)
        this.handleToggleDialogEdit();
        this.getAllPatients();
    }

    async componentDidMount() {
        this.getAllPatients();
    }

    async getAllPatients() {
        const patients = await getAllPatients();
        if (patients) {
            this.setState({ patients: patients });
        }
    }

    async deletePatient(id) {
        await deletePatient(id);
        this.getAllPatients()
    }

    handleEditButton(id) {
        const { patients } = this.state;
        const currentPatient = patients.filter((p) => p.id === id)
        this.setState({
            currentPatient: currentPatient[0]
        })
        this.handleToggleDialogEdit();
    }

    handleChangePage = (event, newPage) => {
        this.setState({
            page: newPage
        });
    };

    handleChangeRowsPerPage = (event) => {
        this.setState({
            page: 0,
            rowsPerPage: parseInt(event.target.value, 10)
        })
    };

    handleRequestSort = (event, property) => {
        const { orderBy, orderDirection } = this.state;
        const isAscending = (orderBy === property && orderDirection === 'asc');
        this.setState({
            orderBy: property,
            orderDirection: (isAscending) ? 'desc' : 'asc'
        })
    };

    createSortHandler = (property) => (event) => {
        this.handleRequestSort(event, property)
    };

    descendingComparator(a, b, orderBy) {
        if (b[orderBy] < a[orderBy]) {
            return -1;
        }
        if (b[orderBy] > a[orderBy]) {
            return 1;
        }
        return 0;
    }

    getComparator(order, orderBy) {
        return order === 'desc'
            ? (a, b) => this.descendingComparator(a, b, orderBy)
            : (a, b) => -this.descendingComparator(a, b, orderBy)
    }

    sortedPatient = (patient, comparator) => {
        const stabilizedRowArray = patient.map((el, index) => [el, index])
        stabilizedRowArray.sort((a, b) => {
            const order = comparator(a[0], b[0])
            if (order !== 0) return order
            return a[1] - b[1]
        })
        return stabilizedRowArray.map((el) => el[0])
    }

    render() {
        const { isOpenDialogAdd, isOpenDialogEdit, patients, currentPatient,
            orderBy, orderDirection, headCells, page, rowsPerPage, } = this.state;
        let emptyRows = null
        if (patients) {
            emptyRows = rowsPerPage - Math.min(rowsPerPage, patients.length - page * rowsPerPage);
        }
        return <div className="patient full">
            <BreadCrumbs data={data} />
            {
                isOpenDialogAdd && <AddPatientDialog
                    open={isOpenDialogAdd}
                    handleToggleDialogAdd={this.handleToggleDialogAdd}
                    addNewPatient={this.addNewPatient}
                />
            }
            {
                isOpenDialogEdit && <EditPatientDialog
                    open={isOpenDialogEdit}
                    handleToggleDialogEdit={this.handleToggleDialogEdit}
                    editPatient={this.editPatient}
                    patient={currentPatient}
                />
            }
            <div className="add-area">
                <button className="report" onClick={this.handleToggleDialogAdd}><span>Add</span> <AddIcon /></button>
            </div>
            <section id="patient-section" className="main-section">
                <TableContainer component={Paper}>
                    <div className="search-area">
                        <InputBase
                            className="seach-bar"
                            placeholder="Search Patient"
                        />
                        <IconButton type="submit" className="search-button" aria-label="search">
                            <SearchIcon />
                        </IconButton>
                    </div>
                    <Table aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                {headCells.map((headCell) => {
                                    return <TableCell key={headCell.id}>
                                        {(headCell.sortable)
                                            ? <TableSortLabel
                                                active={orderBy === headCell.id}
                                                direction={orderBy === headCell.id ? orderDirection : 'asc'}
                                                onClick={this.createSortHandler(headCell.id)}
                                            >
                                                {headCell.label}
                                            </TableSortLabel>
                                            : headCell.label
                                        }
                                    </TableCell>
                                })}
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {patients && patients.length > 0 && this.sortedPatient(patients, this.getComparator(orderDirection, orderBy)).slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((p) => <TableRow key={p.id}>
                                <TableCell align="left">
                                    <Link to={`/patient-profile/${p.id}`} className="user-link">{p.name}</Link>
                                </TableCell>
                                <TableCell align="left">{p.gender}</TableCell>
                                <TableCell align="left">{p.dob}</TableCell>
                                <TableCell align="left">{p.address}</TableCell>
                                <TableCell align="left">{p.phoneNumber}</TableCell>
                                <TableCell align="left">
                                    <Button color="primary" size="small" >
                                        <EditIcon fontSize="small" className="edit-button" onClick={() => { this.handleEditButton(p.id) }}></EditIcon>
                                    </Button>
                                    <Button color="secondary" size="small" onClick={() => { window.confirm('Do you want to delete ?') && this.deletePatient(p.id) }}>
                                        <DeleteIcon fontSize="small" color="secondary"></DeleteIcon>
                                    </Button>
                                </TableCell>
                            </TableRow>
                            )}
                            {emptyRows > 0 && (
                                <TableRow style={{ height: 59 * emptyRows }}>
                                    <TableCell colSpan={6} />
                                </TableRow>
                            )}
                            {/*  */}

                        </TableBody>
                    </Table>
                    <TablePagination
                        component="div"
                        count={patients && patients.length || 0}
                        page={page}
                        onChangePage={this.handleChangePage}
                        rowsPerPage={rowsPerPage}
                        onChangeRowsPerPage={this.handleChangeRowsPerPage}
                        rowsPerPageOptions={[5, 10, 15]}
                    />
                </TableContainer>
            </section>
        </div>
    }
}

export default Patient