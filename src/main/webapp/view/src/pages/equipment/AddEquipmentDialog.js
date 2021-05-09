import React, { Component } from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

class AddServiceDialog extends Component {
    constructor(props) {
        super(props);
        this.handleAdd = this.handleAdd.bind(this);
        this.state = {
            form: {
                name: null,
                price: null,
                quantity: null
            }
        }
        this.handleChangeInput = this.handleChangeInput.bind(this);
    }

    handleAdd() {
        const { addNewEquipment } = this.props;
        const { form } = this.state;
        addNewEquipment(form);
    }


    handleChangeInput(el) {
        const { name, value } = el.target
        this.setState((currentState) => ({
            form: {
                ...currentState.form,
                [name]: value
            }
        }))
    }

    render() {
        const { open, handleToggleAddDialog } = this.props;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Add New Equipment</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                        Equipment Name
                        </DialogContentText>
                        <input name="name" type="text" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Price
                        </DialogContentText>
                        <input name="price" type="number" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Quantity
                        </DialogContentText>
                        <input name="quantity" type="number" onChange={this.handleChangeInput} />
                    </div>
                    <DialogActions className="full-size">
                        <button className="action-btn" id="save-btn" onClick={this.handleAdd}>Save</button>
                        <button className="action-btn" id="close-btn" onClick={handleToggleAddDialog} >Close</button>
                    </DialogActions>
                </DialogContent>
            </Dialog>
        )
    }
}

export default AddServiceDialog;