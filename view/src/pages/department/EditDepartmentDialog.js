import React, { Component } from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import { getDoctors, getReps, getOneDepartment } from '../../utils/api'
class EditDepartmentDialog extends Component {
    constructor(props) {
        super(props);
        this.state = {
            staffID: null,
            doctors: null,
            reps: null
        };
        this.handleChangeInput = this.handleChangeInput.bind(this);
        this.handleEdit = this.handleEdit.bind(this);
    }

    async componentDidMount() {
        const { isRemove, selectedDepartment } = this.props;
        const department = await getOneDepartment(selectedDepartment)
        if (!isRemove) {
            let doctors = await getDoctors();
            let reps = await getReps();
            this.setState({
                staffID: (doctors.length > 0 && doctors[0].id) || (reps.length > 0 && reps[0].id) || null,
                doctors: doctors,
                reps: reps,
            })
        } else {
            this.setState({
                reps: department.staff,
                staffID: department.staff.length > 0 && department.staff[0].id
            })
        }
    }

    handleChangeInput(el) {
        const { value } = el.target
        console.log({ value })
        this.setState({
            staffID: value
        })
    }

    handleEdit() {
        const { addStafftoDep, selectedDepartment, isRemove, removeStaff } = this.props;
        const { staffID } = this.state;
        if (isRemove === false) {
            addStafftoDep(selectedDepartment, staffID);
        } else {
            removeStaff(staffID);
        }
    }

    render() {
        const { doctors, reps, staffID } = this.state;
        const { open, handleToggleEditDialog, isRemove } = this.props;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">{isRemove === true ? "Remove" : "Add"} Staff To Department</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Staff Name
                        </DialogContentText>
                        <select id="cars" onChange={this.handleChangeInput}>
                            {doctors && doctors.map(d => <option value={d.id} key={d.id}>{`Dr. ${d.name}`}</option>)}
                            {reps && reps.map(r => <option value={r.id} key={r.id}>{`${r.name}`}</option>)}
                        </select>
                    </div>
                    <DialogActions className="full-size">
                        <button className="action-btn" id="save-btn" onClick={staffID && this.handleEdit}>Save</button>
                        <button className="action-btn" id="close-btn" onClick={handleToggleEditDialog} >Close</button>
                    </DialogActions>
                </DialogContent>
            </Dialog>
        )
    }

}

export default EditDepartmentDialog;