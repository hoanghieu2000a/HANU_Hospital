import React, { Component } from 'react'
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Button from '@material-ui/core/Button';
class AddDialog extends Component {
    constructor(props) {
        super(props)
        this.state = {
            form: {
                name: '',
                gender: '',
                price: '',
                address: '',
                description: '',
                roomtype: [],
                image_link: [],
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
        const { addNewReport } = this.props;
        addNewReport(this.state.form)
    }

    render() {
        const { open, handleToggleDialogAdd } = this.props;
        return (
            <Dialog open={open} >
                <DialogTitle id="form-dialog-title">Add New Report</DialogTitle>
                <DialogContent>
                    <div>
                        <DialogContentText>
                            Patient ID
                        </DialogContentText>
                        <input name="catalog_name" type="text" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Patient Name
                        </DialogContentText>
                        <input type="text" name="name" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Faculty
                        </DialogContentText>
                        <input type="number" name="price" onChange={this.handleChangeInput} />
                    </div>
                    <div>
                        <DialogContentText>
                            Doctor
                        </DialogContentText>
                        <input type="text" name="address" onChange={this.handleChangeInput} />
                    </div>
                    <div className="full-size">
                        <DialogContentText>
                            Diagnosis
                        </DialogContentText>
                        <textarea name="description" onChange={this.handleChangeInput}></textarea>
                    </div>
                    <div className="full-size">
                        <DialogContentText>
                            Note
                        </DialogContentText>
                        <textarea name="description" onChange={this.handleChangeInput} ></textarea>
                    </div>
                    <div className="full-size">
                        <DialogContentText>
                            Prescribe
                        </DialogContentText>
                        <textarea name="description" onChange={this.handleChangeInput} ></textarea>
                    </div>
                    <DialogActions>
                        <Button variant="contained" className="action-btn" id="save-btn" onClick={this.handeAdd}>
                            Save
                        </Button>
                        <Button variant="contained" color="secondary" className="action-btn" id="close-btn" onClick={handleToggleDialogAdd}>
                            Close
                        </Button>
                    </DialogActions>
                </DialogContent>
            </Dialog>
        )
    }
}

export default AddDialog;