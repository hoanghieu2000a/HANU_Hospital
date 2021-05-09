import React, { Component } from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

class AddMedicineDialog extends Component {
    constructor(props) {
        super(props);
        this.handleAdd = this.handleAdd.bind(this);
        this.state = {
            form: {
                name: null,
                quantity: null,
                expireDate: null,
                importPrice: null,
                sellPrice: null
            }
        }
        this.handleChangeInput = this.handleChangeInput.bind(this);
    }

    handleAdd() {
        const { addNewMedicine } = this.props;
        const { form } = this.state;
        addNewMedicine(form);
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
                <DialogTitle id="form-dialog-title">Add New Medicine</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Medicine Name
                        </DialogContentText>
                        <input name="name" type="text" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Quantity
                        </DialogContentText>
                        <input name="quantity" type="number" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Expire Date
                        </DialogContentText>
                        <input name="expireDate" type="date" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText >
                            Import Price
                        </DialogContentText>
                        <input name="importPrice" type="number" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Sell Price
                        </DialogContentText>
                        <input name="sellPrice" type="number" onChange={this.handleChangeInput} />
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

export default AddMedicineDialog;