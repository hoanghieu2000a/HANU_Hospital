import React, { Component } from 'react'
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';
class EditPatientDialog extends Component {
    constructor(props) {
        super(props)
        this.state = {
            form: {
                id: props.patient.id || '',
                name: props.patient.name || '',
                gender: props.patient.gender || 'MALE',
                dob: props.patient.dob || '',
                phoneNumber: props.patient.phoneNumber || '',
                address: props.patient.address || '',
            }
        };
        this.handleChangeInput = this.handleChangeInput.bind(this);
        this.handeEdit = this.handeEdit.bind(this);
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

    handeEdit() {
        const { form } = this.state;
        const { editPatient } = this.props;
        editPatient(form.id, form)
    }


    render() {
        const { form } = this.state;
        const { open, handleToggleDialogEdit } = this.props;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Edit Patient</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Patient Name
                        </DialogContentText>
                        <input defaultValue={form.name} type="text" name="name" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Gender
                        </DialogContentText>
                        <select id="gender-selection" name="gender" onChange={this.handleChangeInput} >
                            <option value="MALE" selected={(form.name === "MALE") && 'selected'}>Male</option>
                            <option value="FEMALE" selected={(form.name === "FEMALE") && 'selected'}>Female</option>
                        </select>
                    </div>
                    <div className="">
                        <DialogContentText>
                            DOB
                        </DialogContentText>
                        <input defaultValue={form.dob} type="date" name="dob" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Phone Number
                        </DialogContentText>
                        <input defaultValue={form.phoneNumber} type="number" name="phoneNumber" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Address
                        </DialogContentText>
                        <input defaultValue={form.address} type="text" name="address" onChange={this.handleChangeInput} />
                    </div>
                    <div style={{ width: '100%' }}>
                        <DialogActions>
                            <Button variant="contained" className="action-btn" id="save-btn" onClick={this.handeEdit}>
                                Save
                        </Button>
                            <Button variant="contained" color="secondary" className="action-btn" id="close-btn" onClick={handleToggleDialogEdit}>
                                Close
                        </Button>
                        </DialogActions>
                    </div>
                </DialogContent>
            </Dialog>
        )
    }
}

export default EditPatientDialog;
