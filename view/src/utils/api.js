import axios from 'axios';
const localhost = 'http://localhost:8085'

export async function register(info) {
    const { data } = await axios.post(`${localhost}/auth/signup`, info)
    return data;
}

export async function login(info) {
    try {
        const { data } = await axios.post(`${localhost}/auth/signin`, info)
        return data;
    } catch (err) {
        return err.response.status;
    }
}


// MEDICINE

export async function getMedicine() {
    const { data } = await axios.get(`${localhost}/medicine/getAll`)
    return data;
}

export async function addNewMedicine(medicine) {
    const { data } = await axios.post(`${localhost}/medicine/add`, medicine)
    return data;
}

export async function deleteMedicine(id) {
    const { data } = await axios.delete(`${localhost}/medicine/delete/${id}`)
    return data;
}

export async function editMedicine(medicine) {
    const { data } = await axios.put(`${localhost}/medicine/update`, medicine)
    return data;
}

//RECORDS

export async function getAllRecords() {
    const { data } = await axios.get(`${localhost}/api/record/getAll`)
    return data;
}

export async function getRecord(id) {
    const { data } = await axios.get(`${localhost}/record/getById/${id}`)
    return data;
}

export async function getRecordByPatientId(id) {
    const { data } = await axios.get(`${localhost}/record/getRecordByPatientId/${id}`)
    return data;
}

export async function editRecord(info) {
    const { data } = await axios.put(`${localhost}/record/update`, info)
    return data;

}

export async function addProcedure(id, proId) {
    const { data } = await axios.put(`${localhost}/record/addMedicalProcedure/${id}?medicalProcedureId=${proId}`)
    return data;
}

export async function discharge(id) {
    const { data } = await axios.put(`${localhost}/record/discharge/${id}`)
    return data;
}

//PATIENTS

export async function getAllPatients() {
    const { data } = await axios.get(`${localhost}/patient/all`)
    return data;
}

export async function addPatient(info) {
    const { data } = await axios.post(`${localhost}/patient/add_patient`, info)
    return data;
}

export async function getPatientByID(id) {
    const { data } = await axios.get(`${localhost}/patient/${id}`)
    return data;
}

export async function editPatient(id, info) {
    const { data } = await axios.put(`${localhost}/patient/${id}`, info)
    return data;
}

export async function deletePatient(id) {
    const { data } = await axios.delete(`${localhost}/patient/${id}`)
    return data;
}

//DOCTORS

export async function getDoctors() {
    const { data } = await axios.get(`${localhost}/doctor/getAll`)
    return data;
}

export async function getOneDoctor(id) {
    const { data } = await axios.get(`${localhost}/doctor/get/${id}`)
    return data;
}


export async function addDoctor(info) {
    const { data } = await axios.post(`${localhost}/doctor/add`, info)
    return data;
}

export async function deleteDoc(id) {
    const { data } = await axios.delete(`${localhost}/doctor/delete/${id}`)
    return data;
}

export async function editDoctor(id, info) {
    const { data } = await axios.put(`${localhost}/doctor/update/${id}`, info)
    return data;
}

//RECEPTIONIST

export async function getReps() {
    const { data } = await axios.get(`${localhost}/nurse/getAll`)
    return data;
}

export async function getOneRep(id) {
    const { data } = await axios.get(`${localhost}/nurse/get/${id}`)
    return data;
}


export async function addRep(info) {
    const { data } = await axios.post(`${localhost}/nurse/add`, info)
    return data;
}

export async function deleteRep(id) {
    const { data } = await axios.delete(`${localhost}/nurse/delete/${id}`)
    return data;
}

export async function editNurse(id, info) {
    const { data } = await axios.put(`${localhost}/nurse/update/${id}`, info)
    return data;
}


//DEPARTMENT

export async function getOneDepartment(id) {
    const { data } = await axios.get(`${localhost}/department/getById/${id}`)
    return data;
}

export async function getDepartments() {
    const { data } = await axios.get(`${localhost}/department/all`)
    return data;
}

export async function addDepartment(name) {
    const { data } = await axios.post(`${localhost}/department/add`, name)
    return data;
}

export async function deleteDeparment(id) {
    const { data } = await axios.delete(`${localhost}/department/delete/${id}`)
    return data;
}

export async function addStafftoDep(id, staffID) {
    const { data } = await axios.put(`${localhost}/department/addStaff/${id}?staffId=${staffID}`)
    return data;
}

export async function removeStaff(id) {
    const { data } = await axios.put(`${localhost}/department/removeStaff?staffId=${id}`)
    return data;
}


//ACCOUNTS

export async function getAllAccounts() {
    const { data } = await axios.get(`${localhost}/account/getAll`)
    return data.content;
}

export async function getAccount(id) {
    const { data } = await axios.get(`${localhost}/account/getAll?id=${id}`)
    return data.content;
}

export async function deactivateAccount(id) {
    const { data } = await axios.get(`${localhost}/account/delete?id=${id}`)
    return data;
}

//APPOINTMENT

export async function getAllAppointment() {
    const { data } = await axios.get(`${localhost}/record/getRecordNotDischarged`)
    return data;
}

export async function addNewAppointment(info) {
    const { data } = await axios.post(`${localhost}/record/add`, info)
    return data
}

//SERVICES

export async function getAllServices() {
    const { data } = await axios.get(`${localhost}/services/all`)
    return data
}

export async function addNewService(info) {
    const { data } = await axios.post(`${localhost}/services/add`, info)
    return data
}

export async function deleteService(id) {
    const { data } = await axios.delete(`${localhost}/services/${id}`)
    return data
}

export async function addEquipmentService(id, equipmentId) {
    const { data } = await axios.put(`${localhost}/services/addEquipment/${id}?equipmentId=${equipmentId}`)
    return data
}

export async function removeEquipmentService(id, equipmentId) {
    const { data } = await axios.put(`${localhost}/services/removeEquipment/${1}?equipmentId=${equipmentId}`)
    return data
}



//EQUIPMENT

export async function getAllEquipment() {
    const { data } = await axios.get(`${localhost}/equipment/getEquipments`)
    return data

}

export async function addNewEquipment(info) {
    const { data } = await axios.post(`${localhost}/equipment/add_equipment`, info)
    return data
}

export async function deleteEquiment(id) {
    const { data } = await axios.delete(`${localhost}/equipment/equipment/${id}`)
    return data
}


//PRESCIPTION

export async function addPres(medicineId, recordId, body) {
    const { data } = await axios.post(`${localhost}/api/prescription/add?medicineId=${medicineId}&recordId=${recordId}`, body)
    return data;
}


//BILL

export async function billProfit() {
    const { data } = await axios.get(`${localhost}/bills/profit`)
    return data;
}

export async function billIncome() {
    const { data } = await axios.get(`${localhost}/bills/income`)
    return data;
}

export async function billExpend() {
    const { data } = await axios.get(`${localhost}/bills/expend`)
    return data;
}


