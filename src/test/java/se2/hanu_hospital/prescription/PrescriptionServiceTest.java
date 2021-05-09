package se2.hanu_hospital.prescription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se2.hanu_hospital.bill.Bill;
import se2.hanu_hospital.billline.BillLine;
import se2.hanu_hospital.department.Department;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;
import se2.hanu_hospital.medicine.Medicine;
import se2.hanu_hospital.medicine.MedicineService;
import se2.hanu_hospital.patient.Patient;
import se2.hanu_hospital.record.Record;
import se2.hanu_hospital.record.RecordRepository;
import se2.hanu_hospital.staff.Staff;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.util.Gender;

@ContextConfiguration(classes = {PrescriptionService.class, MedicineService.class})
@ExtendWith(SpringExtension.class)
public class PrescriptionServiceTest {
    @MockBean
    private MedicineService medicineService;

    @MockBean
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PrescriptionService prescriptionService;

    @MockBean
    private RecordRepository recordRepository;

    @Test
    public void testGetAll() {
        ArrayList<Prescription> prescriptionList = new ArrayList<Prescription>();
        when(this.prescriptionRepository.findAll()).thenReturn(prescriptionList);
        List<Prescription> actualAll = this.prescriptionService.getAll();
        assertSame(prescriptionList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(this.prescriptionRepository).findAll();
    }

    @Test
    public void testDeleteAllPresOfRecord() {
        Bill bill = new Bill();
        bill.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill.setTotalPrice(10.0);
        bill.setRecord(new Record());
        bill.setBillLines(new HashSet<BillLine>());

        Doctor doctor = new Doctor();
        doctor.setSpeciality("Speciality");
        doctor.setAvailable(true);
        doctor.setEmail("jane.doe@example.org");
        doctor.setDob(LocalDate.ofEpochDay(1L));
        doctor.setRecords(new HashSet<Record>());
        doctor.setId(123L);
        doctor.setName("Name");
        doctor.setPhone("4105551212");
        doctor.setSalary(10.0);
        doctor.setDepartment(new Department());

        Patient patient = new Patient();
        patient.setDob(LocalDate.ofEpochDay(1L));
        patient.setRecords(new HashSet<Record>());
        patient.setGender(Gender.MALE);
        patient.setName("Name");
        patient.setPhoneNumber("4105551212");
        patient.setAddress("42 Main St");

        Record record = new Record();
        record.setDiagnosis("Diagnosis");
        record.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record.setPrescriptionMedicine(new HashSet<Prescription>());
        record.setId(123L);
        record.setBill(bill);
        record.setDescription("The characteristics of someone or something");
        record.setDoctor(doctor);
        record.setPatient(patient);
        record.setDischargePatient(true);

        Bill bill1 = new Bill();
        bill1.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill1.setTotalPrice(10.0);
        bill1.setRecord(record);
        bill1.setBillLines(new HashSet<BillLine>());

        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Doctor doctor1 = new Doctor();
        doctor1.setSpeciality("Speciality");
        doctor1.setAvailable(true);
        doctor1.setEmail("jane.doe@example.org");
        doctor1.setDob(LocalDate.ofEpochDay(1L));
        doctor1.setRecords(new HashSet<Record>());
        doctor1.setId(123L);
        doctor1.setName("Name");
        doctor1.setPhone("4105551212");
        doctor1.setSalary(10.0);
        doctor1.setDepartment(department);

        Patient patient1 = new Patient();
        patient1.setDob(LocalDate.ofEpochDay(1L));
        patient1.setRecords(new HashSet<Record>());
        patient1.setGender(Gender.MALE);
        patient1.setName("Name");
        patient1.setPhoneNumber("4105551212");
        patient1.setAddress("42 Main St");

        Record record1 = new Record();
        record1.setDiagnosis("Diagnosis");
        record1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record1.setPrescriptionMedicine(new HashSet<Prescription>());
        record1.setId(123L);
        record1.setBill(bill1);
        record1.setDescription("The characteristics of someone or something");
        record1.setDoctor(doctor1);
        record1.setPatient(patient1);
        record1.setDischargePatient(true);
        Optional<Record> ofResult = Optional.<Record>of(record1);
        when(this.recordRepository.findById((Long) any())).thenReturn(ofResult);
        this.prescriptionService.deleteAllPresOfRecord(123L);
        verify(this.recordRepository).findById((Long) any());
    }

    @Test
    public void testGetById() {
        Medicine medicine = new Medicine();
        medicine.setExpireDate(LocalDate.ofEpochDay(1L));
        medicine.setSellPrice(10.0);
        medicine.setId(123L);
        medicine.setImportPrice(10.0);
        medicine.setImportDate(LocalDate.ofEpochDay(1L));
        medicine.setName("Name");
        medicine.setQuantity(1);

        Record record = new Record();
        record.setDiagnosis("Diagnosis");
        record.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record.setPrescriptionMedicine(new HashSet<Prescription>());
        record.setId(123L);
        record.setBill(new Bill());
        record.setDescription("The characteristics of someone or something");
        record.setDoctor(new Doctor());
        record.setPatient(new Patient());
        record.setDischargePatient(true);

        Bill bill = new Bill();
        bill.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill.setTotalPrice(10.0);
        bill.setRecord(record);
        bill.setBillLines(new HashSet<BillLine>());

        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Doctor doctor = new Doctor();
        doctor.setSpeciality("Speciality");
        doctor.setAvailable(true);
        doctor.setEmail("jane.doe@example.org");
        doctor.setDob(LocalDate.ofEpochDay(1L));
        doctor.setRecords(new HashSet<Record>());
        doctor.setId(123L);
        doctor.setName("Name");
        doctor.setPhone("4105551212");
        doctor.setSalary(10.0);
        doctor.setDepartment(department);

        Patient patient = new Patient();
        patient.setDob(LocalDate.ofEpochDay(1L));
        patient.setRecords(new HashSet<Record>());
        patient.setGender(Gender.MALE);
        patient.setName("Name");
        patient.setPhoneNumber("4105551212");
        patient.setAddress("42 Main St");

        Record record1 = new Record();
        record1.setDiagnosis("Diagnosis");
        record1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record1.setPrescriptionMedicine(new HashSet<Prescription>());
        record1.setId(123L);
        record1.setBill(bill);
        record1.setDescription("The characteristics of someone or something");
        record1.setDoctor(doctor);
        record1.setPatient(patient);
        record1.setDischargePatient(true);

        Prescription prescription = new Prescription();
        prescription.setStartDate(LocalDate.ofEpochDay(1L));
        prescription.setMedicine(medicine);
        prescription.setEndDate(LocalDate.ofEpochDay(1L));
        prescription.setId(123L);
        prescription.setDosage(1);
        prescription.setRecord(record1);
        Optional<Prescription> ofResult = Optional.<Prescription>of(prescription);
        when(this.prescriptionRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(prescription, this.prescriptionService.getById(123L));
        verify(this.prescriptionRepository).findById((Long) any());
    }

    @Test
    public void testGetAllByRecordId() {
        Bill bill = new Bill();
        bill.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill.setTotalPrice(10.0);
        bill.setRecord(new Record());
        bill.setBillLines(new HashSet<BillLine>());

        Doctor doctor = new Doctor();
        doctor.setSpeciality("Speciality");
        doctor.setAvailable(true);
        doctor.setEmail("jane.doe@example.org");
        doctor.setDob(LocalDate.ofEpochDay(1L));
        doctor.setRecords(new HashSet<Record>());
        doctor.setId(123L);
        doctor.setName("Name");
        doctor.setPhone("4105551212");
        doctor.setSalary(10.0);
        doctor.setDepartment(new Department());

        Patient patient = new Patient();
        patient.setDob(LocalDate.ofEpochDay(1L));
        patient.setRecords(new HashSet<Record>());
        patient.setGender(Gender.MALE);
        patient.setName("Name");
        patient.setPhoneNumber("4105551212");
        patient.setAddress("42 Main St");

        Record record = new Record();
        record.setDiagnosis("Diagnosis");
        record.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record.setPrescriptionMedicine(new HashSet<Prescription>());
        record.setId(123L);
        record.setBill(bill);
        record.setDescription("The characteristics of someone or something");
        record.setDoctor(doctor);
        record.setPatient(patient);
        record.setDischargePatient(true);

        Bill bill1 = new Bill();
        bill1.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill1.setTotalPrice(10.0);
        bill1.setRecord(record);
        bill1.setBillLines(new HashSet<BillLine>());

        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Doctor doctor1 = new Doctor();
        doctor1.setSpeciality("Speciality");
        doctor1.setAvailable(true);
        doctor1.setEmail("jane.doe@example.org");
        doctor1.setDob(LocalDate.ofEpochDay(1L));
        doctor1.setRecords(new HashSet<Record>());
        doctor1.setId(123L);
        doctor1.setName("Name");
        doctor1.setPhone("4105551212");
        doctor1.setSalary(10.0);
        doctor1.setDepartment(department);

        Patient patient1 = new Patient();
        patient1.setDob(LocalDate.ofEpochDay(1L));
        patient1.setRecords(new HashSet<Record>());
        patient1.setGender(Gender.MALE);
        patient1.setName("Name");
        patient1.setPhoneNumber("4105551212");
        patient1.setAddress("42 Main St");

        Record record1 = new Record();
        record1.setDiagnosis("Diagnosis");
        record1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        HashSet<Prescription> prescriptionSet = new HashSet<Prescription>();
        record1.setPrescriptionMedicine(prescriptionSet);
        record1.setId(123L);
        record1.setBill(bill1);
        record1.setDescription("The characteristics of someone or something");
        record1.setDoctor(doctor1);
        record1.setPatient(patient1);
        record1.setDischargePatient(true);
        Optional<Record> ofResult = Optional.<Record>of(record1);
        when(this.recordRepository.findById((Long) any())).thenReturn(ofResult);
        Set<Prescription> actualAllByRecordId = this.prescriptionService.getAllByRecordId(123L);
        assertSame(prescriptionSet, actualAllByRecordId);
        assertTrue(actualAllByRecordId.isEmpty());
        verify(this.recordRepository).findById((Long) any());
    }

    @Test
    public void testUpdateMedicineQty() throws IOException {
        ArrayList<Prescription> prescriptionList = new ArrayList<Prescription>();
        when(this.prescriptionRepository.findAll()).thenReturn(prescriptionList);

        Medicine medicine = new Medicine();
        medicine.setExpireDate(LocalDate.ofEpochDay(1L));
        medicine.setSellPrice(10.0);
        medicine.setId(123L);
        medicine.setImportPrice(10.0);
        medicine.setImportDate(LocalDate.ofEpochDay(1L));
        medicine.setName("Name");
        medicine.setQuantity(1);
        doNothing().when(this.medicineService).updateMedicine((Medicine) any());
        when(this.medicineService.getMedicineById((Long) any())).thenReturn(medicine);
        this.prescriptionService.updateMedicineQty(123L);
        verify(this.medicineService).getMedicineById((Long) any());
        verify(this.medicineService).updateMedicine((Medicine) any());
        verify(this.prescriptionRepository).findAll();
        assertSame(prescriptionList, this.prescriptionService.getAll());
    }

    @Test
    public void testUpdateMedicineQty2() throws IOException {
        Medicine medicine = new Medicine();
        medicine.setExpireDate(LocalDate.ofEpochDay(1L));
        medicine.setSellPrice(10.0);
        medicine.setId(123L);
        medicine.setImportPrice(10.0);
        medicine.setImportDate(LocalDate.ofEpochDay(1L));
        medicine.setName("Name");
        medicine.setQuantity(0);

        Record record = new Record();
        record.setDiagnosis("Diagnosis");
        record.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record.setPrescriptionMedicine(new HashSet<Prescription>());
        record.setId(123L);
        record.setBill(new Bill());
        record.setDescription("The characteristics of someone or something");
        record.setDoctor(new Doctor());
        record.setPatient(new Patient());
        record.setDischargePatient(true);

        Bill bill = new Bill();
        bill.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill.setTotalPrice(10.0);
        bill.setRecord(record);
        bill.setBillLines(new HashSet<BillLine>());

        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Doctor doctor = new Doctor();
        doctor.setSpeciality("Speciality");
        doctor.setAvailable(true);
        doctor.setEmail("jane.doe@example.org");
        doctor.setDob(LocalDate.ofEpochDay(1L));
        doctor.setRecords(new HashSet<Record>());
        doctor.setId(123L);
        doctor.setName("Name");
        doctor.setPhone("4105551212");
        doctor.setSalary(10.0);
        doctor.setDepartment(department);

        Patient patient = new Patient();
        patient.setDob(LocalDate.ofEpochDay(1L));
        patient.setRecords(new HashSet<Record>());
        patient.setGender(Gender.MALE);
        patient.setName("Name");
        patient.setPhoneNumber("4105551212");
        patient.setAddress("42 Main St");

        Record record1 = new Record();
        record1.setDiagnosis("Diagnosis");
        record1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record1.setPrescriptionMedicine(new HashSet<Prescription>());
        record1.setId(123L);
        record1.setBill(bill);
        record1.setDescription("The characteristics of someone or something");
        record1.setDoctor(doctor);
        record1.setPatient(patient);
        record1.setDischargePatient(true);

        Prescription prescription = new Prescription();
        prescription.setStartDate(LocalDate.ofEpochDay(1L));
        prescription.setMedicine(medicine);
        prescription.setEndDate(LocalDate.ofEpochDay(1L));
        prescription.setId(123L);
        prescription.setDosage(0);
        prescription.setRecord(record1);

        ArrayList<Prescription> prescriptionList = new ArrayList<Prescription>();
        prescriptionList.add(prescription);
        when(this.prescriptionRepository.findAll()).thenReturn(prescriptionList);

        Medicine medicine1 = new Medicine();
        medicine1.setExpireDate(LocalDate.ofEpochDay(1L));
        medicine1.setSellPrice(10.0);
        medicine1.setId(123L);
        medicine1.setImportPrice(10.0);
        medicine1.setImportDate(LocalDate.ofEpochDay(1L));
        medicine1.setName("Name");
        medicine1.setQuantity(1);
        doNothing().when(this.medicineService).updateMedicine((Medicine) any());
        when(this.medicineService.getMedicineById((Long) any())).thenReturn(medicine1);
        this.prescriptionService.updateMedicineQty(123L);
        verify(this.medicineService).getMedicineById((Long) any());
        verify(this.medicineService).updateMedicine((Medicine) any());
        verify(this.prescriptionRepository).findAll();
        assertSame(prescriptionList, this.prescriptionService.getAll());
    }

    @Test
    public void testGetQuantity() {
        Medicine medicine = new Medicine();
        medicine.setExpireDate(LocalDate.ofEpochDay(1L));
        medicine.setSellPrice(10.0);
        medicine.setId(123L);
        medicine.setImportPrice(10.0);
        medicine.setImportDate(LocalDate.ofEpochDay(1L));
        medicine.setName("Name");
        medicine.setQuantity(1);

        Record record = new Record();
        record.setDiagnosis("Diagnosis");
        record.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record.setPrescriptionMedicine(new HashSet<Prescription>());
        record.setId(123L);
        record.setBill(new Bill());
        record.setDescription("The characteristics of someone or something");
        record.setDoctor(new Doctor());
        record.setPatient(new Patient());
        record.setDischargePatient(true);

        Bill bill = new Bill();
        bill.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill.setTotalPrice(10.0);
        bill.setRecord(record);
        bill.setBillLines(new HashSet<BillLine>());

        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Doctor doctor = new Doctor();
        doctor.setSpeciality("Speciality");
        doctor.setAvailable(true);
        doctor.setEmail("jane.doe@example.org");
        doctor.setDob(LocalDate.ofEpochDay(1L));
        doctor.setRecords(new HashSet<Record>());
        doctor.setId(123L);
        doctor.setName("Name");
        doctor.setPhone("4105551212");
        doctor.setSalary(10.0);
        doctor.setDepartment(department);

        Patient patient = new Patient();
        patient.setDob(LocalDate.ofEpochDay(1L));
        patient.setRecords(new HashSet<Record>());
        patient.setGender(Gender.MALE);
        patient.setName("Name");
        patient.setPhoneNumber("4105551212");
        patient.setAddress("42 Main St");

        Record record1 = new Record();
        record1.setDiagnosis("Diagnosis");
        record1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record1.setPrescriptionMedicine(new HashSet<Prescription>());
        record1.setId(123L);
        record1.setBill(bill);
        record1.setDescription("The characteristics of someone or something");
        record1.setDoctor(doctor);
        record1.setPatient(patient);
        record1.setDischargePatient(true);

        Prescription prescription = new Prescription();
        prescription.setStartDate(LocalDate.ofEpochDay(1L));
        prescription.setMedicine(medicine);
        prescription.setEndDate(LocalDate.ofEpochDay(1L));
        prescription.setId(123L);
        prescription.setDosage(1);
        prescription.setRecord(record1);
        Optional<Prescription> ofResult = Optional.<Prescription>of(prescription);
        when(this.prescriptionRepository.findById((Long) any())).thenReturn(ofResult);
        assertEquals(1, this.prescriptionService.getQuantity(123L));
        verify(this.prescriptionRepository).findById((Long) any());
    }
}

