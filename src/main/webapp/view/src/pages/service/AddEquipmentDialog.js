import React, { Component } from 'react';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import { getAllEquipment } from '../../utils/api';
class AddEquipmentDialog extends Component {
    constructor(props) {
        super(props);
        this.handleAdd = this.handleAdd.bind(this);
        this.state = {
            id: props.selectedService,
            equipments: null,
            equipmentId: null,
        }
        this.handleChangeInput = this.handleChangeInput.bind(this);
    }

    handleAdd() {
        const { addEquipment } = this.props;
        const { id, equipmentId } = this.state;
        addEquipment(id, equipmentId);
    }


    handleChangeInput(el) {
        const { name, value } = el.target
        this.setState({
            [name]: value
        })
    }

    async componentDidMount() {
        const equipments = await getAllEquipment();
        if (equipments) {
            this.setState({
                equipments: equipments,
                equipmentId: equipments[0].id
            })
        }
    }

    render() {
        const { open, handleToggleAddDialog } = this.props;
        const { equipments } = this.state;
        console.log(equipments)
        return < Dialog open={open} >
            <DialogTitle id="form-dialog-title">Add Equipment</DialogTitle>
            <DialogContent>
                <div>
                    <DialogContentText>
                        Equipment Name
                    </DialogContentText>
                    <select id="cars" name="equipmentId" onChange={this.handleChangeInput}>
                        {equipments && equipments.map(d => <option value={d.id} key={d.id}>{`${d.name}`}</option>)}
                    </select>
                </div>
                <DialogActions className="full-size">
                    <button className="action-btn" id="save-btn" onClick={this.handleAdd}>Save</button>
                    <button className="action-btn" id="close-btn" onClick={handleToggleAddDialog} >Close</button>
                </DialogActions>
            </DialogContent>
        </Dialog >
    }
}

export default AddEquipmentDialog;