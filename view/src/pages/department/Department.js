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
import PersonAddIcon from '@material-ui/icons/PersonAdd';
import PersonAddDisabledIcon from '@material-ui/icons/PersonAddDisabled';
import DeleteIcon from '@material-ui/icons/Delete';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import AddDepartmentDialog from './AddDepartmentDialog';
import EditDepartmentDialog from './EditDepartmentDialog';

import {
    getDepartments, addDepartment, deleteDeparment, getOneDepartment,
    addStafftoDep, removeStaff
} from '../../utils/api';

const data = {
    active:
        [
            { title: "Dashboard", link: "dashboard" }
        ],
    current: "Department"
}

class Department extends Component {

    constructor(props) {
        super(props);
        this.state = {
            departments: null,
            headCells: [
                { id: 'name', label: 'Department Name', sortable: true },
                { id: 'doctors', label: 'Staff' },
                { id: 'action', label: 'Actions' },
            ],
            page: 0,
            rowsPerPage: 5,
            orderBy: 'name',
            orderDirection: 'asc',
            isOpenDialogAdd: false,
            isOpenDialogEdit: false,
            selectedID: null,
            isRemove: null
        }
        this.getAllDeps = this.getAllDeps.bind(this);
        this.addDepartment = this.addDepartment.bind(this);
        this.addStafftoDep = this.addStafftoDep.bind(this);
        this.removeStaff = this.removeStaff.bind(this);
        this.handleToggleDialogAdd = this.handleToggleDialogAdd.bind(this);
        this.handleToggleDialogEdit = this.handleToggleDialogEdit.bind(this);
        this.deleteDepartment = this.deleteDepartment.bind(this);
    }

    componentDidMount() {
        this.getAllDeps();
    }

    async getAllDeps() {
        const departments = await getDepartments();
        this.setState({
            departments: departments
        })
    }

    async addDepartment(name) {
        await addDepartment(name);
        this.handleToggleDialogAdd();
        this.getAllDeps();
    }

    async deleteDepartment(id) {
        const thisDep = await getOneDepartment(id);
        if (thisDep && thisDep.staff.length === 0) {
            await deleteDeparment(id);
            this.getAllDeps();
        } else {
            alert('Department must be empty first')
        }
    }

    async addStafftoDep(id, staffID) {
        await addStafftoDep(id, staffID)
        this.handleToggleDialogEdit();
        this.getAllDeps();
    }

    async removeStaff(id) {
        await removeStaff(id)
        this.handleToggleDialogEdit();
        this.getAllDeps();
    }

    handleToggleDialogAdd() {
        this.setState((currentState) => ({
            isOpenDialogAdd: !currentState.isOpenDialogAdd
        }));
    }

    handleToggleDialogEdit(id, isRemove) {
        this.setState((currentState) => ({
            isOpenDialogEdit: !currentState.isOpenDialogEdit,
            selectedID: id,
            isRemove: isRemove
        }));
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

    sortDepartment = (patient, comparator) => {
        const stabilizedRowArray = patient.map((el, index) => [el, index])
        stabilizedRowArray.sort((a, b) => {
            const order = comparator(a[0], b[0])
            if (order !== 0) return order
            return a[1] - b[1]
        })
        return stabilizedRowArray.map((el) => el[0])
    }


    render() {
        const { isOpenDialogAdd, isOpenDialogEdit, departments, selectedID,
            orderBy, orderDirection, headCells, page, rowsPerPage, isRemove } = this.state;
        return <div className="department full">
            <BreadCrumbs data={data} />
            {
                isOpenDialogAdd && <AddDepartmentDialog
                    open={isOpenDialogAdd}
                    handleToggleDialogAdd={this.handleToggleDialogAdd}
                    addDepartment={this.addDepartment}
                />
            }
            {isOpenDialogEdit && <EditDepartmentDialog
                open={isOpenDialogEdit}
                handleToggleEditDialog={this.handleToggleDialogEdit}
                addStafftoDep={this.addStafftoDep}
                isRemove={isRemove}
                selectedDepartment={selectedID}
                removeStaff={this.removeStaff}
            />}
            <div className="add-area">
                <button className="report" onClick={this.handleToggleDialogAdd}><span>Add</span> <AddIcon /></button>
            </div>
            <section id="department-section" className="main-section">
                <TableContainer component={Paper}>
                    <div className="search-area">
                        <InputBase
                            className="seach-bar"
                            placeholder="Search Department"
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
                            {departments && departments.length > 0 && this.sortDepartment(departments, this.getComparator(orderDirection, orderBy)).slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((d) => <TableRow key={d.id}>
                                <TableCell align="left">{d.name}</TableCell>
                                <TableCell align="left">
                                    <span>({d.staff.length}) </span>
                                    {d.staff.map(doctor => {
                                        return <>
                                            <span> {doctor.name}, </span>
                                        </>
                                    })}
                                </TableCell>
                                <TableCell align="left">
                                    <Button color="primary" size="small" >
                                        <PersonAddIcon fontSize="small" className="edit-button" onClick={() => { this.handleToggleDialogEdit(d.id, false) }}></PersonAddIcon>
                                    </Button>
                                    <Button color="primary" size="small" >
                                        <PersonAddDisabledIcon fontSize="small" className="remove-button" onClick={() => { this.handleToggleDialogEdit(d.id, true) }}></PersonAddDisabledIcon>
                                    </Button>
                                    <Button color="secondary" size="small" onClick={() => { window.confirm('Do you want to delete ?') && this.deleteDepartment(d.id) }}>
                                        <DeleteIcon fontSize="small" color="secondary"></DeleteIcon>
                                    </Button>
                                </TableCell>
                            </TableRow>
                            )}
                        </TableBody>
                    </Table>
                </TableContainer>
            </section>
        </div>
    }
}

export default Department