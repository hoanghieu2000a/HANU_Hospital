import React, { Component } from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import { getFormattedDate } from '../../utils/utils'
class EditMedicineDialog extends Component {
    constructor(props) {
        super(props);
        this.state = {
            form: {
                id: props.selectedMedicine.id || '',
                name: props.selectedMedicine && props.selectedMedicine.name || '',
                quantity: props.selectedMedicine && props.selectedMedicine.quantity || '',
                expireDate: props.selectedMedicine && props.selectedMedicine.expireDate || getFormattedDate(new Date()),
                importPrice: props.selectedMedicine && props.selectedMedicine.importPrice || '',
                sellPrice: props.selectedMedicine && props.selectedMedicine.sellPrice || '',
            }
        };
        this.handleChangeInput = this.handleChangeInput.bind(this);
        this.handleEdit = this.handleEdit.bind(this);
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

    handleEdit() {
        const { editMedicine } = this.props;
        const { form } = this.state;
        editMedicine(form);
    }

    render() {
        const { form } = this.state;
        const { open, handleToggleEditDialog } = this.props;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Edit Medicine</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Medicine Name
                        </DialogContentText>
                        <input name="name" type="text" defaultValue={form.name} onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Quantity
                        </DialogContentText>
                        <input name="quantity" type="number" defaultValue={form.quantity} onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Expire Date
                        </DialogContentText>
                        <input name="expireDate" type="date" defaultValue={form.expireDate} onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText >
                            Import Price
                        </DialogContentText>
                        <input name="importPrice" type="number" defaultValue={form.importPrice} onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Sell Price
                        </DialogContentText>
                        <input name="sellPrice" type="number" defaultValue={form.sellPrice} onChange={this.handleChangeInput} />
                    </div>
                    <DialogActions className="full-size">
                        <button className="action-btn" id="save-btn" onClick={this.handleEdit}>Save</button>
                        <button className="action-btn" id="close-btn" onClick={handleToggleEditDialog} >Close</button>
                    </DialogActions>
                </DialogContent>
            </Dialog>
        )
    }

}

export default EditMedicineDialog;