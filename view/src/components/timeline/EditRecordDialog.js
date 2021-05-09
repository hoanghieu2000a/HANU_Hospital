import React, { Component } from 'react'
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';
import { getRecord, getAllServices, addPres } from '../../utils/api'

class EditDialog extends Component {
    constructor(props) {
        super(props)
        this.state = {
            form: {
                doctorId: null,
                diagnosis: '',
                description: '',
            },
            record: null,
            services: null,
            serviceId: null
        };
        this.handleChangeInput = this.handleChangeInput.bind(this);
        this.handeAdd = this.handeAdd.bind(this);
    }

    async componentDidMount() {
        this.getRecordById()
        this.getServices()
    }

    async getRecordById() {
        const record = await getRecord(this.props.recordId)
        this.setState((currentState) => ({
            form: {
                ...currentState.form,
                doctorId: this.props.doctorId,
                diagnosis: record.diagnosis,
                description: record.description,
                id: record.id,
            },
            record: record
        }))
    }

    async getServices() {
        const services = await getAllServices();
        this.setState((currentState) => ({
            services: services,
            serviceId: services.length > 0 && services[0].id
        }))
    }

    handleChangeInput(e) {
        const { name, value } = e.target;
        this.setState((currentState) => ({
            form: {
                ...currentState.form,
                [name]: value
            }
        }))
    }

    handeAdd() {
        const { editRecord, addProcedure } = this.props;
        const { record, serviceId } = this.state;
        addProcedure(record.id, serviceId)
        editRecord(this.state.form)
    }

    handleChangePres(e) {
        const { value } = e.target;
        this.setState({
            serviceId: value
        })
    }

    render() {
        const { open, handleToggleDialogAdd } = this.props;
        const { services } = this.state;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Edit Record</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Service Name
                        </DialogContentText>
                        <select id="doctor" name="doctorId" onChange={this.handleChangeInput}>
                            {services && services.map(d => {
                                return <option value={d.id} key={d.id}>{`${d.name}`}</option>
                            })}
                        </select>
                    </div>
                    <div>
                        <DialogContentText>
                            Description
                        </DialogContentText>
                        <input name="description" type="text" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Diagnosis
                        </DialogContentText>
                        <input name="diagnosis" type="text" onChange={this.handleChangeInput} />
                    </div>
                    <div style={{ width: "100%" }}>
                        <DialogActions>
                            <Button variant="contained" className="action-btn" id="save-btn" onClick={this.handeAdd}>
                                Save
                        </Button>
                            <Button variant="contained" color="secondary" className="action-btn" id="close-btn" onClick={handleToggleDialogAdd}>
                                Close
                        </Button>
                        </DialogActions>
                    </div>
                </DialogContent>
            </Dialog>
        )
    }
}

export default EditDialog;