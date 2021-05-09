import React, { Component } from 'react'
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';
class AddDepartmentDialog extends Component {
    constructor(props) {
        super(props)
        this.state = {
            form: {
                name: '',
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
        const { addDepartment } = this.props;
        const { form } = this.state;
        addDepartment(form)
    }

    render() {
        const { open, handleToggleDialogAdd } = this.props;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Add New Employee</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Department Name
                        </DialogContentText>
                        <input type="text" name="name" onChange={this.handleChangeInput} />
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

export default AddDepartmentDialog;
