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
import { Link } from 'react-router-dom'
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import { getAllEquipment, addNewEquipment, deleteEquiment } from '../../utils/api';
import AddEquipmentDialog from './AddEquipmentDialog';
const data = {
    active:
        [
            { title: "Dashboard", link: "dashboard" }
        ],
    current: "Equipment"
}

class Equipment extends Component {


    constructor(props) {
        super(props)
        this.state = {
            equipment: null,
            isOpenAddDialog: false
        }
        this.addNewEquipment = this.addNewEquipment.bind(this);
        this.deleteEquiment = this.deleteEquiment.bind(this);
    }

    componentDidMount() {
        this.getAllEquipment();
    }

    async getAllEquipment() {
        const equipment = await getAllEquipment();
        this.setState({
            equipment: equipment
        })
    }

    handleToggleAddDialog = () => {
        this.setState((currentState) => ({
            isOpenAddDialog: !currentState.isOpenAddDialog
        }));
    };

    async addNewEquipment(name) {
        await addNewEquipment(name)
        this.handleToggleAddDialog()
        this.getAllEquipment()
    }

    async deleteEquiment(id) {
        if (window.confirm("Do you want to delete this service ?")) {
            await deleteEquiment(id)
            this.getAllEquipment()
        }
    }


    render() {
        const { equipment, isOpenAddDialog } = this.state;
        return <div className="equipment full">
            <BreadCrumbs data={data} />
            {AddEquipmentDialog && <AddEquipmentDialog
                open={isOpenAddDialog}
                handleToggleAddDialog={this.handleToggleAddDialog}
                addNewEquipment={this.addNewEquipment}
            />}
            <div className="add-area">
                <button className="report" onClick={this.handleToggleAddDialog}><span>Add</span> <AddIcon /></button>
            </div>
            <section id="patient-section" className="main-section">
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
                                <TableCell align="left">Name</TableCell>
                                <TableCell align="left">Price</TableCell>
                                <TableCell align="left">Quantity</TableCell>
                                <TableCell align="left">Action</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {equipment && equipment.length > 0 && equipment.map(e => {
                                return <TableRow key={e.id}>
                                    <TableCell align="left">{e.name}</TableCell>
                                    <TableCell align="left">{e.price}</TableCell>
                                    <TableCell align="left">{e.quantity}</TableCell>
                                    <TableCell align="left">
                                        <IconButton id={e.id} color="primary" size="small" onClick={this.handleToggleEditDialog}>
                                            <EditIcon id={e.id} fontSize="small" className="edit-button"></EditIcon>
                                        </IconButton>
                                        <IconButton id={e.id} color="secondary" size="small" onClick={() => { this.deleteEquiment(e.id)}} className="delete-btn">
                                            <DeleteIcon id={e.id} fontSize="small" color="secondary" ></DeleteIcon>
                                        </IconButton>
                                    </TableCell>
                                </TableRow>
                            })}
                        </TableBody>
                    </Table>
                </TableContainer>
            </section>
        </div>
    }
}

export default Equipment;