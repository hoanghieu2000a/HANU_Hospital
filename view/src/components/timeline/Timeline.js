import React, { Component } from 'react'
import Timeline from '@material-ui/lab/Timeline';
import TimelineItem from '@material-ui/lab/TimelineItem';
import TimelineSeparator from '@material-ui/lab/TimelineSeparator';
import TimelineConnector from '@material-ui/lab/TimelineConnector';
import TimelineContent from '@material-ui/lab/TimelineContent';
import TimelineDot from '@material-ui/lab/TimelineDot';
import IconButton from '@material-ui/core/IconButton';
import AddIcon from '@material-ui/icons/Add';
import AddMedicineDialog from './AddMedicineDialog'
import EditRecordDialog from './EditRecordDialog'
import Button from '@material-ui/core/Button';
import { addPres, editRecord, addProcedure, discharge } from '../../utils/api'
import './Timeline.css'
class TimeLine extends Component {
    constructor(props) {
        super(props);
        this.state = {
            records: this.props.records,
            isOpenDialogAdd: false,
            isOpenDialogEdit: false,
            selectedRecord: null
        }
        this.handleOpenAddMedicine = this.handleOpenAddMedicine.bind(this);
        this.handleOpenEditRecord = this.handleOpenEditRecord.bind(this);
        this.addMedicineRecord = this.addMedicineRecord.bind(this)
    }

    handleOpenAddMedicine(id) {
        this.setState((currentState) => ({
            selectedRecord: id,
            isOpenDialogAdd: !currentState.isOpenDialogAdd
        }));
    }

    handleOpenEditRecord(id) {
        this.setState((currentState) => ({
            selectedRecord: id,
            isOpenDialogEdit: !currentState.isOpenDialogEdit
        }));
    }

    async addMedicineRecord(medicineId, recordId, body) {
        await addPres(medicineId, recordId, body)
        this.handleOpenAddMedicine()
        window.location.reload();
    }

    async editRecord(data) {
        await editRecord(data)
        window.location.reload();
    }

    async addProcedure(id, serviceId) {
        await addProcedure(id, serviceId)
        window.location.reload();
    }

    async discharge(id) {
        await discharge(id);
        window.location.reload();
    }

    render() {
        const { records, isOpenDialogAdd, isOpenDialogEdit, selectedRecord } = this.state;
        const { findDoctor } = this.props;
        const id = window.location.href.split("/").reverse()[0]
        return <Timeline>
            {
                isOpenDialogAdd && <AddMedicineDialog
                    open={isOpenDialogAdd}
                    handleToggleDialogAdd={this.handleOpenAddMedicine}
                    addMedicineRecord={this.addMedicineRecord}
                    recordId={selectedRecord}
                />
            }
            {
                isOpenDialogEdit && <EditRecordDialog
                    open={isOpenDialogEdit}
                    handleToggleDialogAdd={this.handleOpenEditRecord}
                    editRecord={this.editRecord}
                    recordId={selectedRecord}
                    patientId={id}
                    doctorId={findDoctor(selectedRecord) && findDoctor(selectedRecord).id}
                    addProcedure={this.addProcedure}
                />
            }
            {records && records.map(record => <TimelineItem>
                <TimelineSeparator>
                    <TimelineDot color="secondary" variant="outlined" />
                    <TimelineConnector />
                </TimelineSeparator>
                <TimelineContent>
                    <div className={`timeline ${record.dischargePatient === true ? "discharged" : "not-discharge"}`}>
                        <h1 className="timeline-content underline">{record.bill && `Total Price: $${record.bill.totalPrice}`}</h1>
                        <p className="timeline-content date">{record.dischargePatient === true ? <p className="discharged">DISCHARGED</p> : <p className="not-discharge">NOT DISCHARGE</p>}</p>
                        <p className="timeline-content date">{record.date}</p>
                        <p className="timeline-content doctor-name"><b>Doctor:</b> Dr. {findDoctor(record.id) && findDoctor(record.id).name}</p>
                        <p className="timeline-content diagnosis"><b>Medical Procedure:</b> {record.medicalProcedure && record.medicalProcedure.length > 0 && record.medicalProcedure[0].name} </p>
                        <p className="timeline-content diagnosis"><b>Diagnosis:</b> {record.diagnosis}</p>
                        <p className="timeline-content note"><b>Note:</b> {record.description}</p>
                        <p className="timeline-content prescribe">
                            <div className="flex-between">
                                <div style={{ fontWeight: 'bold' }} className="flex-center">Prescribe</div>
                                {record.dischargePatient === false && <IconButton aria-label="add" onClick={() => { this.handleOpenAddMedicine(record.id) }} ><AddIcon id="add-btn" /></IconButton>}
                            </div>
                            <table className="pres-table" style={{ width: "100%" }}>
                                <tr>
                                    <th>Name</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Dosage</th>
                                </tr>
                                {record.prescriptionMedicine && record.prescriptionMedicine.map(p => {
                                    return <tr>
                                        <td>{p.medicine.name}</td>
                                        <td>{p.startDate}</td>
                                        <td>{p.endDate}</td>
                                        <td>{p.dosage}</td>
                                    </tr>
                                })
                                }
                            </table>
                        </p>
                        {record.dischargePatient === false &&
                            <>
                                <Button variant="outlined" color="primary" onClick={() => { this.handleOpenEditRecord(record.id) }}><b className="green-color">Edit</b></Button >
                                <Button variant="outlined" color="secondary" onClick={() => { this.discharge(record.id) }}><b className="red-color">DISCHARGE</b></Button >
                            </>
                        }
                    </div>
                </TimelineContent>
            </TimelineItem>
            )}

        </Timeline>

    }
}

export default TimeLine;