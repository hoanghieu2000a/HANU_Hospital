import React, { Component } from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Typography from '@material-ui/core/Typography';
class DeleteMedicineDialog extends Component {
    constructor(props) {
        super(props)
        this.handeDelete = this.handeDelete.bind(this);
    }

    handeDelete() {
        const { deleteMedicine, selectedID } = this.props;
        deleteMedicine(selectedID);
    }

    render() {
        const { open, handleToggleDeleteDialog } = this.props;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Delete Medicine</DialogTitle>
                <DialogContent>
                    <div className="flex-center full-size">
                        <Typography variant="h5">
                            Do you want to delete this medicine?
                        </Typography>
                    </div>
                    <DialogActions className="full-size">
                        <button className="action-btn" id="delete-btn" onClick={this.handeDelete}>Delete</button>
                        <button className="action-btn" id="close-btn" onClick={() => handleToggleDeleteDialog()} >Cancel</button>
                    </DialogActions>
                </DialogContent>
            </Dialog>
        )
    }
}

export default DeleteMedicineDialog;