import React, { Component } from 'react'
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';

class AddEmployeeDialog extends Component {
    constructor(props) {
        super(props)
        this.state = {
            form: {
                name: '',
                job: 'doctor',
                dob: '',
                phone: '',
                email: '',
                speciality: '',
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
        const { addNewEmployee } = this.props;
        const { form } = this.state;
        addNewEmployee(form)
    }

    render() {
        const { open, handleToggleDialogAdd } = this.props;
        const { form } = this.state;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Add New Employee</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Employee Name
                        </DialogContentText>
                        <input type="text" name="name" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Job
                        </DialogContentText>
                        <select id="gender-selection" name="job" onChange={this.handleChangeInput} >
                            <option value="doctor">Doctor</option>
                            <option value="receptionist">Nurse</option>
                        </select>
                    </div>
                    <div >
                        <DialogContentText>
                            Date of birth
                        </DialogContentText>
                        <input type="date" name="dob" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Phone Number
                        </DialogContentText>
                        <input type="number" name="phone" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Email
                        </DialogContentText>
                        <input type="email" name="email" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Salary
                        </DialogContentText>
                        <input type="number" name="salary" onChange={this.handleChangeInput} />
                    </div>
                    {form.job && form.job === "doctor" && <div>
                        <DialogContentText>
                            Speciality
                        </DialogContentText>
                        <input type="text" name="speciality" onChange={this.handleChangeInput} />
                    </div>
                    }
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

export default AddEmployeeDialog;
