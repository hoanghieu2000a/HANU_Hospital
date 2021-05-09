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
import TablePagination from '@material-ui/core/TablePagination';
import {
    getAllServices, addNewService, deleteService,
    addEquipmentService, removeEquipmentService
} from '../../utils/api'
import { searchByIdArray } from '../../utils/utils';
import TableSortLabel from '@material-ui/core/TableSortLabel';
import AddServiceDialog from './AddServiceDialog'
import AddEquipmentDialog from './AddEquipmentDialog'
const breadcrumbs = {
    active:
        [
            { title: "Dashboard", link: "dashboard" },

        ],
    current: "Service"
}


class Service extends Component {

    constructor(props) {
        super(props);
        this.state = {
            services: null,
            isOpenAddDialog: false,
            isOpenAddEquipDialog: false,
            selectedService: null,
        }
        this.addNewService = this.addNewService.bind(this);
        this.addEquipment = this.addEquipment.bind(this);
    }

    componentDidMount() {
        this.getAllServices()
    }

    async getAllServices() {
        const services = await getAllServices();
        this.setState({
            services: services
        })
    }

    handleToggleAddDialog = () => {
        this.setState((currentState) => ({
            isOpenAddDialog: !currentState.isOpenAddDialog
        }));
    };

    handleToggleAddEquipmentDialog = (id) => {
        this.setState((currentState) => ({
            isOpenAddEquipDialog: !currentState.isOpenAddEquipDialog,
            selectedService: id
        }));
    };


    async addNewService(name) {
        await addNewService(name)
        this.handleToggleAddDialog()
        this.getAllServices()
    }

    async deleteService(id) {
        if (window.confirm("Do you want to delete this service ?")) {
            await deleteService(id)
            this.getAllServices()
        }
    }

    async addEquipment(id, equipmentId) {
        await addEquipmentService(id, equipmentId)
        this.handleToggleAddEquipmentDialog()
        this.getAllServices()
    }

    removeEquipment = async (id, equipmentId) => {
        await removeEquipmentService(id, equipmentId)
        this.getAllServices()
    }

    render() {
        const { services, isOpenAddDialog, isOpenAddEquipDialog, selectedService } = this.state;
        return <div className="service full">
            <BreadCrumbs data={breadcrumbs} />
            {isOpenAddDialog && <AddServiceDialog
                open={isOpenAddDialog}
                handleToggleAddDialog={this.handleToggleAddDialog}
                addNewService={this.addNewService}
            />}
            {isOpenAddEquipDialog && <AddEquipmentDialog
                open={isOpenAddEquipDialog}
                handleToggleAddDialog={this.handleToggleAddEquipmentDialog}
                addEquipment={this.addEquipment}
                selectedService={selectedService}
            />}
            <div className="add-area">
                <button className="report" onClick={this.handleToggleAddDialog}><span>Add</span> <AddIcon /></button>
            </div>
            <section id="medicine-section" className="main-section">
                <TableContainer component={Paper}>
                    <div className="search-area">
                        <InputBase className="seach-bar" placeholder="Search Service" />
                        <IconButton type="submit" className="search-button" aria-label="search">
                            <SearchIcon />
                        </IconButton>
                    </div>
                    <Table aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell align="left">Service Name</TableCell>
                                <TableCell align="left">Equipment</TableCell>
                                <TableCell align="left">Action</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {services && services.length > 0 && services.map(s => {
                                return <TableRow key={s.id}>
                                    <TableCell align="left">{s.name}</TableCell>
                                    <TableCell align="left">
                                        {s.equipments.map(e => {
                                            return <div>
                                                <span>{e.name}</span>
                                                <button className="equip-btn" onClick={() => { this.removeEquipment(s.id, e.id) }}>-</button>
                                            </div>
                                        })}
                                        <button className="equip-btn" onClick={() => { this.handleToggleAddEquipmentDialog(s.id) }}>+</button>
                                    </TableCell>
                                    <TableCell align="left">
                                        <IconButton id={s.id} color="primary" size="small" onClick={this.handleToggleEditDialog}>
                                            <EditIcon id={s.id} fontSize="small" className="edit-button"></EditIcon>
                                        </IconButton>
                                        <IconButton id={s.id} color="secondary" size="small" onClick={() => { this.deleteService(s.id) }} className="delete-btn">
                                            <DeleteIcon id={s.id} fontSize="small" color="secondary" ></DeleteIcon>
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

export default Service