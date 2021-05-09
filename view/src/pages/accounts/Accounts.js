import React, { Component } from 'react'
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
import BreadCrumbs from '../../components/breadcrumbs/BreadCrumbs';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import { getAllAccounts, deactivateAccount } from '../../utils/api';
import DeleteIcon from '@material-ui/icons/Delete';
import { Link } from 'react-router-dom';
import { withRouter } from "react-router";

const data = {
    active:
        [
            { title: "Dashboard", link: "dashboard" }
        ],
    current: "Accounts"
}

class Accounts extends Component {

    constructor(props) {
        super(props);
        this.state = {
            headCells: [
                { id: 'name', label: 'Name', sortable: true },
                { id: 'username', label: 'Username', sortable: true },
                { id: 'phoneNumber', label: 'Phone Number' },
                { id: 'address', label: 'Address' },
                { id: 'role', label: 'Role' },
                { id: 'status', label: 'Status' },
                { id: 'action', label: 'Actions' },
            ],
            page: 0,
            rowsPerPage: 5,
            orderBy: 'name',
            orderDirection: 'asc',
            accounts: null
        }
    }

    componentDidMount() {
        this.getAllAccounts();
    }

    async getAllAccounts() {
        const accounts = await getAllAccounts();
        this.setState({
            accounts: accounts
        })
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

    deactivate = async (id) => {
        if (window.confirm('Do you want to deactivate this account ?')) {
            await deactivateAccount(id)
            this.getAllAccounts();
        }
    }

    render() {
        const { isOpenDialogAdd, isOpenDialogEdit, accounts, selectedID,
            orderBy, orderDirection, headCells, page, rowsPerPage, isRemove } = this.state;
        return <div className="accounts full">
            <BreadCrumbs data={data} />
            <div className="add-area">
                <Link to="/signup">
                    <button className="report" ><span>Add</span> <AddIcon /></button>
                </Link>
            </div>
            <TableContainer component={Paper}>
                <div className="search-area">
                    <InputBase
                        className="seach-bar"
                        placeholder="Search Account"
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
                        {accounts && accounts.length > 0 && this.sortDepartment(accounts, this.getComparator(orderDirection, orderBy)).slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((a) => <TableRow key={a.id}>
                            <TableCell align="left">{a.name}</TableCell>
                            <TableCell align="left">{a.username}</TableCell>
                            <TableCell align="left">{a.phoneNumber}</TableCell>
                            <TableCell align="left">{a.address}</TableCell>
                            <TableCell align="left">{a.role.name}</TableCell>
                            <TableCell align="left">{a.status}</TableCell>
                            <TableCell align="left">
                                <IconButton aria-label="delete" onClick={() => { this.deactivate(a.id) }}>
                                    <DeleteIcon fontSize="small" color="secondary" />
                                </IconButton>
                            </TableCell>
                        </TableRow>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>
        </div >
    }
}

export default withRouter(Accounts);