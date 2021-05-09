import React, { Component } from 'react'
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';

class AddPatientDialog extends Component {
    constructor(props) {
        super(props)
        this.state = {
            form: {
                name: '',
                gender: 'MALE',
                dob: '',
                phoneNumber: '',
                address: '',
            }
        };
        this.handleChangeInput = this.handleChangeInput.bind(this);
        this.handeAdd = this.handeAdd.bind(this);
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
        const { addNewPatient } = this.props;
        addNewPatient(this.state.form)
    }

    render() {
        const { open, handleToggleDialogAdd } = this.props;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Add New Patient</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Patient Name
                        </DialogContentText>
                        <input type="text" name="name" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Gender
                        </DialogContentText>
                        <select id="gender-selection" name="gender" onChange={this.handleChangeInput} >
                            <option value="MALE">Male</option>
                            <option value="FEMALE">Female</option>
                        </select>
                    </div>
                    <div className="">
                        <DialogContentText>
                            DOB
                        </DialogContentText>
                        <input type="date" name="dob" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Phone Number
                        </DialogContentText>
                        <input type="number" name="phoneNumber" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Address
                        </DialogContentText>
                        <input type="text" name="address" onChange={this.handleChangeInput} />
                    </div>
                    <div style={{ width: '100%' }}>
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

export default AddPatientDialog;
