package se2.hanu_hospital.record;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
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
import se2.hanu_hospital.bill.BillService;
import se2.hanu_hospital.billline.BillLine;
import se2.hanu_hospital.department.Department;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;
import se2.hanu_hospital.medical_procedure.ProcedureService;
import se2.hanu_hospital.patient.Patient;
import se2.hanu_hospital.patient.PatientService;
import se2.hanu_hospital.prescription.Prescription;
import se2.hanu_hospital.prescription.PrescriptionService;
import se2.hanu_hospital.staff.Staff;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.staff.doctor.service.DoctorService;
import se2.hanu_hospital.util.Gender;

@ContextConfiguration(classes = {DoctorService.class, RecordService.class, ProcedureService.class, BillService.class,
        PrescriptionService.class, PatientService.class})
@ExtendWith(SpringExtension.class)
public class RecordServiceTest {
    @MockBean
    private BillService billService;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private PatientService patientService;

    @MockBean
    private PrescriptionService prescriptionService;

    @MockBean
    private ProcedureService procedureService;

    @MockBean
    private RecordRepository recordRepository;

    @Autowired
    private RecordService recordService;

    @Test
    public void testGetAllRecord() {
        ArrayList<Record> recordList = new ArrayList<Record>();
        when(this.recordRepository.findAll()).thenReturn(recordList);
        List<Record> actualAllRecord = this.recordService.getAllRecord();
        assertSame(recordList, actualAllRecord);
        assertTrue(actualAllRecord.isEmpty());
        verify(this.recordRepository).findAll();
    }

    @Test
    public void testAddRecord() throws IOException {
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

        Bill bill1 = new Bill();
        bill1.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill1.setTotalPrice(10.0);
        bill1.setRecord(record1);
        bill1.setBillLines(new HashSet<BillLine>());

        Department department1 = new Department();
        department1.setName("Name");
        department1.setStaff(new HashSet<Staff>());

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
        doctor1.setDepartment(department1);

        Patient patient1 = new Patient();
        patient1.setDob(LocalDate.ofEpochDay(1L));
        patient1.setRecords(new HashSet<Record>());
        patient1.setGender(Gender.MALE);
        patient1.setName("Name");
        patient1.setPhoneNumber("4105551212");
        patient1.setAddress("42 Main St");

        Record record2 = new Record();
        record2.setDiagnosis("Diagnosis");
        record2.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record2.setPrescriptionMedicine(new HashSet<Prescription>());
        record2.setId(123L);
        record2.setBill(bill1);
        record2.setDescription("The characteristics of someone or something");
        record2.setDoctor(doctor1);
        record2.setPatient(patient1);
        record2.setDischargePatient(true);
        when(this.recordRepository.save((Record) any())).thenReturn(record2);

        Patient patient2 = new Patient();
        patient2.setDob(LocalDate.ofEpochDay(1L));
        patient2.setRecords(new HashSet<Record>());
        patient2.setGender(Gender.MALE);
        patient2.setName("Name");
        patient2.setPhoneNumber("4105551212");
        patient2.setAddress("42 Main St");
        when(this.patientService.getById((Long) any())).thenReturn(patient2);

        Department department2 = new Department();
        department2.setName("Name");
        department2.setStaff(new HashSet<Staff>());

        Doctor doctor2 = new Doctor();
        doctor2.setSpeciality("Speciality");
        doctor2.setAvailable(true);
        doctor2.setEmail("jane.doe@example.org");
        doctor2.setDob(LocalDate.ofEpochDay(1L));
        doctor2.setRecords(new HashSet<Record>());
        doctor2.setId(123L);
        doctor2.setName("Name");
        doctor2.setPhone("4105551212");
        doctor2.setSalary(10.0);
        doctor2.setDepartment(department2);
        doNothing().when(this.doctorService)
                .updateById((Long) any(), (se2.hanu_hospital.staff.doctor.doctorMapper.DoctorDTO) any());
        when(this.doctorService.getById((Long) any())).thenReturn(doctor2);
        this.recordService.addRecord(new RecordPayload());
        verify(this.doctorService).getById((Long) any());
        verify(this.doctorService).updateById((Long) any(), (se2.hanu_hospital.staff.doctor.doctorMapper.DoctorDTO) any());
        verify(this.patientService).getById((Long) any());
        verify(this.recordRepository).save((Record) any());
    }

    @Test
    public void testDeleteRecord() throws IOException {
        doNothing().when(this.recordRepository).deleteById((Long) any());
        when(this.recordRepository.existsById((Long) any())).thenReturn(true);
        doNothing().when(this.prescriptionService).deleteAllPresOfRecord((Long) any());
        this.recordService.deleteRecord(123L);
        verify(this.prescriptionService).deleteAllPresOfRecord((Long) any());
        verify(this.recordRepository).deleteById((Long) any());
        verify(this.recordRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteRecord2() throws IOException {
        doNothing().when(this.recordRepository).deleteById((Long) any());
        when(this.recordRepository.existsById((Long) any())).thenReturn(false);
        doNothing().when(this.prescriptionService).deleteAllPresOfRecord((Long) any());
        assertThrows(IllegalStateException.class, () -> this.recordService.deleteRecord(123L));
        verify(this.recordRepository).existsById((Long) any());
    }

    @Test
    public void testGetRecordById() {
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
        assertSame(record1, this.recordService.getRecordById(123L));
        verify(this.recordRepository).findById((Long) any());
    }

    @Test
    public void testGetRecordByPatientId() {
        Patient patient = new Patient();
        patient.setDob(LocalDate.ofEpochDay(1L));
        HashSet<Record> recordSet = new HashSet<Record>();
        patient.setRecords(recordSet);
        patient.setGender(Gender.MALE);
        patient.setName("Name");
        patient.setPhoneNumber("4105551212");
        patient.setAddress("42 Main St");
        when(this.patientService.getById((Long) any())).thenReturn(patient);
        Set<Record> actualRecordByPatientId = this.recordService.getRecordByPatientId(123L);
        assertSame(recordSet, actualRecordByPatientId);
        assertTrue(actualRecordByPatientId.isEmpty());
        verify(this.patientService).getById((Long) any());
    }

    @Test
    public void testGetRecordByDoctorId() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Doctor doctor = new Doctor();
        doctor.setSpeciality("Speciality");
        doctor.setAvailable(true);
        doctor.setEmail("jane.doe@example.org");
        doctor.setDob(LocalDate.ofEpochDay(1L));
        HashSet<Record> recordSet = new HashSet<Record>();
        doctor.setRecords(recordSet);
        doctor.setId(123L);
        doctor.setName("Name");
        doctor.setPhone("4105551212");
        doctor.setSalary(10.0);
        doctor.setDepartment(department);
        when(this.doctorService.getById((Long) any())).thenReturn(doctor);
        Set<Record> actualRecordByDoctorId = this.recordService.getRecordByDoctorId(123L);
        assertSame(recordSet, actualRecordByDoctorId);
        assertTrue(actualRecordByDoctorId.isEmpty());
        verify(this.doctorService).getById((Long) any());
    }

    @Test
    public void testGetRecordNotDischarged() {
        ArrayList<Record> recordList = new ArrayList<Record>();
        when(this.recordRepository.findByDischargePatient(anyBoolean())).thenReturn(recordList);
        List<Record> actualRecordNotDischarged = this.recordService.getRecordNotDischarged();
        assertSame(recordList, actualRecordNotDischarged);
        assertTrue(actualRecordNotDischarged.isEmpty());
        verify(this.recordRepository).findByDischargePatient(anyBoolean());
    }
}

