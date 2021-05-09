import React, { Component } from 'react'
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';
import { getDoctors, getAllPatients } from '../../utils/api'

class AddDialog extends Component {
    constructor(props) {
        super(props)
        this.state = {
            form: {
                patientId: null,
                doctorId: null,
                diagnosis: '',
                description: '',
                id: 0,
            },
            doctors: null,
            patients: null
        };
        this.handleChangeInput = this.handleChangeInput.bind(this);
        this.handeAdd = this.handeAdd.bind(this);
    }

    async componentDidMount() {
        const doctors = await getDoctors();
        const patients = await getAllPatients();
        if (doctors.length > 0 && patients.length > 0) {
            this.setState((currentState) => ({
                form: {
                    ...currentState.form,
                    doctorId: doctors[0].id,
                    patientId: patients[0].id,
                },
                doctors: doctors,
                patients: patients
            }))
        }
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
        const { addNewReport } = this.props;
        addNewReport(this.state.form)
    }

    render() {
        const { open, handleToggleDialogAdd } = this.props;
        const { doctors, patients } = this.state;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Add New Appointment</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Doctor Name
                        </DialogContentText>
                        <select id="doctor" name="doctorId" onChange={this.handleChangeInput}>
                            {doctors && doctors.map(d => <option value={d.id} key={d.id}>{`Dr. ${d.name}`}</option>)}
                        </select>
                    </div>
                    <div>
                        <DialogContentText>
                            Patient Name
                        </DialogContentText>
                        <select id="patient" name="patientId" onChange={this.handleChangeInput}>
                            {patients && patients.map(p => <option value={p.id} key={p.id}>{`${p.name} (${p.dob})`}</option>)}
                        </select>
                    </div>
                    <DialogActions>
                        <Button variant="contained" className="action-btn" id="save-btn" onClick={this.handeAdd}>
                            Save
                        </Button>
                        <Button variant="contained" color="secondary" className="action-btn" id="close-btn" onClick={handleToggleDialogAdd}>
                            Close
                        </Button>
                    </DialogActions>
                </DialogContent>
            </Dialog>
        )
    }
}

export default AddDialog;