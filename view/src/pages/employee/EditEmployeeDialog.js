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
                id: props.employee.id || '',
                name: props.employee.name || '',
                dob: props.employee.dob || '',
                phone: props.employee.phone || '',
                email: props.employee.email || '',
                salary: props.employee.salary || '',
                speciality: props.employee.speciality || null,
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
        const { editEmployee } = this.props;
        const { form } = this.state;
        editEmployee(form)
    }

    render() {
        const { open, handleToggleDialogEdit } = this.props;
        const { form } = this.state;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Add New Employee</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Employee Name
                        </DialogContentText>
                        <input defaultValue={form.name} type="text" name="name" onChange={this.handleChangeInput} />
                    </div>
                    <div >
                        <DialogContentText>
                            Age
                        </DialogContentText>
                        <input defaultValue={form.dob} type="date" name="dob" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Phone Number
                        </DialogContentText>
                        <input defaultValue={form.phone} type="number" name="phone" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Email
                        </DialogContentText>
                        <input defaultValue={form.email} type="email" name="email" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Salary
                        </DialogContentText>
                        <input defaultValue={form.salary} type="number" name="salary" onChange={this.handleChangeInput} />
                    </div>
                    {form.speciality && <div>
                        <DialogContentText>
                            Speciality
                        </DialogContentText>
                        <input defaultValue={form.speciality} type="text" name="speciality" onChange={this.handleChangeInput} />
                    </div>
                    }
                    <div style={{ width: '100%' }}>
                        <DialogActions>
                            <Button variant="contained" className="action-btn" id="save-btn" onClick={this.handeAdd}>
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

export default AddEmployeeDialog;
