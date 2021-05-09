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
import AddMedicineDialog from './AddMedicineDialog';
import DeleteMedicineDialog from './DeleteMedicineDialog';
import EditMedicineDialog from './EditMedicineDialog';
import TablePagination from '@material-ui/core/TablePagination';
import { getMedicine, addNewMedicine, deleteMedicine, editMedicine } from '../../utils/api'
import { searchByIdArray } from '../../utils/utils';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import './Medicine.css'
const breadcrumbs = {
    active:
        [
            { title: "Dashboard", link: "dashboard" },

        ],
    current: "Medicine"
}

class Medicine extends Component {

    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            rowsPerPage: 5,
            medicine: null,
            selectedID: null,
            isOpenAddDialog: false,
            isOpenDeleteDialog: false,
            isOpenEditDialog: false,
            selectedMedicine: null,
            headCells: [
                { id: 'name', label: 'Medicine Name', sortable: true },
                { id: 'quantity', label: 'Quantity', sortable: true },
                { id: 'expireDate', label: 'Expire Date' },
                { id: 'importPrice', label: 'Import Price', sortable: true },
                { id: 'sellPrice', label: 'Sell Price', sortable: true },
                { id: 'action', label: 'Action' },
            ],
            orderBy: 'name',
            orderDirection: 'asc'
        }
        this.getMedicineList = this.getMedicineList.bind(this);
        this.addNewMedicine = this.addNewMedicine.bind(this);
        this.deleteMedicine = this.deleteMedicine.bind(this);
        this.editMedicine = this.editMedicine.bind(this);
    }

    async componentDidMount() {
        this.getMedicineList();
    };

    async getMedicineList() {
        const medicine = await getMedicine();
        this.setState({
            medicine: medicine
        })
    };

    async addNewMedicine(data) {
        await addNewMedicine(data);
        this.handleToggleAddDialog();
        this.getMedicineList();
    };

    async deleteMedicine(data) {
        await deleteMedicine(data);
        this.handleToggleDeleteDialog();
        this.getMedicineList();
    };

    async editMedicine(data) {
        await editMedicine(data);
        this.handleToggleEditDialog();
        this.getMedicineList();
    };

    handleToggleAddDialog = () => {
        this.setState((currentState) => ({
            isOpenAddDialog: !currentState.isOpenAddDialog
        }));
    };

    handleToggleDeleteDialog = (el) => {
        this.setSelectedID(el)
        this.setState((currentState) => ({
            isOpenDeleteDialog: !currentState.isOpenDeleteDialog
        }))
    };

    handleToggleEditDialog = (el) => {
        this.setSelectedID(el)
        this.setState((currentState) => ({
            isOpenEditDialog: !currentState.isOpenEditDialog
        }));
    };

    setSelectedID = (el) => {
        const { medicine } = this.state;
        let id = null
        if (el) {
            id = (el.target.id) ? el.target.id : el.target.parentNode.id;
        }
        this.setState({
            selectedID: (id) ? id : null,
            selectedMedicine: (id) ? searchByIdArray(medicine, id) : null
        })
    };

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

    sortedMedicine = (medicine, comparator) => {
        const stabilizedRowArray = medicine.map((el, index) => [el, index])
        stabilizedRowArray.sort((a, b) => {
            const order = comparator(a[0], b[0])
            if (order !== 0) return order
            return a[1] - b[1]
        })
        return stabilizedRowArray.map((el) => el[0])
    }


    render() {
        const { orderBy, orderDirection, headCells,
            page, rowsPerPage, medicine,
            selectedMedicine, isOpenAddDialog, isOpenDeleteDialog,
            isOpenEditDialog, selectedID } = this.state;
        let emptyRows = null
        if (medicine) {
            emptyRows = rowsPerPage - Math.min(rowsPerPage, medicine.length - page * rowsPerPage);
        }
        return <div className="medicine full">
            <BreadCrumbs data={breadcrumbs} />
            {isOpenAddDialog && <AddMedicineDialog
                open={isOpenAddDialog}
                handleToggleAddDialog={this.handleToggleAddDialog}
                addNewMedicine={this.addNewMedicine}
            />}
            {isOpenDeleteDialog && selectedID && <DeleteMedicineDialog
                open={isOpenDeleteDialog}
                selectedID={selectedID}
                handleToggleDeleteDialog={this.handleToggleDeleteDialog}
                deleteMedicine={this.deleteMedicine}
            />}
            {isOpenEditDialog && <EditMedicineDialog
                open={isOpenEditDialog}
                handleToggleEditDialog={this.handleToggleEditDialog}
                editMedicine={this.editMedicine}
                selectedMedicine={selectedMedicine}
            />}
            <div className="add-area">
                <button className="report" onClick={this.handleToggleAddDialog}><span>Add</span> <AddIcon /></button>
            </div>
            <section id="medicine-section" className="main-section">
                <TableContainer component={Paper}>
                    <div className="search-area">
                        <InputBase className="seach-bar" placeholder="Search Medicine" />
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
                            {medicine && medicine.length > 0 && this.sortedMedicine(medicine, this.getComparator(orderDirection, orderBy)).slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map(med => {
                                return <TableRow key={med.id}>
                                    <TableCell align="left">{med.name}</TableCell>
                                    <TableCell align="left">{med.quantity}</TableCell>
                                    <TableCell align="left">{med.expireDate}</TableCell>
                                    <TableCell align="left">{`$ ${med.importPrice}`}</TableCell>
                                    <TableCell align="left">{`$ ${med.sellPrice}`}</TableCell>
                                    <TableCell align="left">
                                        <IconButton id={med.id} color="primary" size="small" onClick={this.handleToggleEditDialog}>
                                            <EditIcon id={med.id} fontSize="small" className="edit-button"></EditIcon>
                                        </IconButton>
                                        <IconButton id={med.id} color="secondary" size="small" onClick={this.handleToggleDeleteDialog} className="delete-btn">
                                            <DeleteIcon id={med.id} fontSize="small" color="secondary" ></DeleteIcon>
                                        </IconButton>
                                    </TableCell>
                                </TableRow>
                            })}
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
                        count={medicine && medicine.length || 0}
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

export default Medicine;