import React, { Component } from 'react'
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';
import { getDoctors, getAllPatients, getMedicine } from '../../utils/api'
import { searchByIdArray } from '../../utils/utils'

class AddMedicine extends Component {
    constructor(props) {
        super(props)
        this.state = {
            form: {
                medicine: null,
                startDate: null,
                endDate: '',
                dosage: '',
                id: 0,
                recordId: props.recordId
            },
            medicine: null
        };
        this.handleChangeInput = this.handleChangeInput.bind(this);
        this.handeAdd = this.handeAdd.bind(this);
        this.handleChangeMedicine = this.handleChangeMedicine.bind(this);
    }

    async componentDidMount() {
        const medicine = await getMedicine();
        this.setState((currentState) => ({
            medicine: medicine,
            form: {
                ...currentState.form,
                medicine: medicine[0]
            }
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

    handleChangeMedicine(e) {
        const { name, value } = e.target;
        const { medicine } = this.state;
        const selectedMedicine = searchByIdArray(medicine, value)
        this.setState((currentState) => ({
            form: {
                ...currentState.form,
                medicine: selectedMedicine
            }
        }))
    }


    handeAdd() {
        const { addMedicineRecord } = this.props;
        const { form } = this.state;
        addMedicineRecord(form.medicine.id, form.recordId, {
            id: 0,
            medicine: form.medicine,
            startDate: form.startDate,
            endDate: form.endDate,
            dosage: form.dosage
        })
    }

    render() {
        const { open, handleToggleDialogAdd } = this.props;
        const { medicine } = this.state;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Add New Appointment</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Medicine Name
                        </DialogContentText>
                        <select id="doctor" name="doctorId" onChange={this.handleChangeMedicine}>
                            {medicine && medicine.map(d => <option value={d.id} key={d.id}>{`${d.name}`}</option>)}
                        </select>
                    </div>
                    <div>
                        <DialogContentText>
                            Start Date
                        </DialogContentText>
                        <input name="startDate" type="date" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            End Date
                        </DialogContentText>
                        <input name="endDate" type="date" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Dosage
                        </DialogContentText>
                        <input name="dosage" type="number" onChange={this.handleChangeInput} />
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

export default AddMedicine;