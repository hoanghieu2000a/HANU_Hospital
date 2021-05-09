package se2.hanu_hospital.medical_procedure;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se2.hanu_hospital.bill.Bill;
import se2.hanu_hospital.billline.BillLine;
import se2.hanu_hospital.billline.ServiceBillLine;
import se2.hanu_hospital.department.Department;
import se2.hanu_hospital.equipment.Equipment;
import se2.hanu_hospital.equipment.EquipmentService;
import se2.hanu_hospital.patient.Patient;
import se2.hanu_hospital.prescription.Prescription;
import se2.hanu_hospital.record.Record;
import se2.hanu_hospital.staff.Staff;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.util.Gender;

@ContextConfiguration(classes = {ProcedureService.class, EquipmentService.class})
@ExtendWith(SpringExtension.class)
public class ProcedureServiceTest {
    @MockBean
    private EquipmentService equipmentService;

    @MockBean
    private ProcedureRepository procedureRepository;

    @Autowired
    private ProcedureService procedureService;

    @Test
    public void testAddProcedure() {
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

        Bill bill2 = new Bill();
        bill2.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill2.setTotalPrice(10.0);
        bill2.setRecord(new Record());
        bill2.setBillLines(new HashSet<BillLine>());

        MedicalProcedure medicalProcedure = new MedicalProcedure();
        medicalProcedure.setName("Name");
        medicalProcedure.setBillLine(new ServiceBillLine());
        medicalProcedure.setEquipments(new ArrayList<Equipment>());
        medicalProcedure.setRecord(new Record());

        ServiceBillLine serviceBillLine = new ServiceBillLine();
        serviceBillLine.setId(123L);
        serviceBillLine.setPrice(10.0);
        serviceBillLine.setBill(bill2);
        serviceBillLine.setMedicalProcedure(medicalProcedure);

        Bill bill3 = new Bill();
        bill3.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill3.setTotalPrice(10.0);
        bill3.setRecord(new Record());
        bill3.setBillLines(new HashSet<BillLine>());

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
        doctor1.setDepartment(new Department());

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
        record1.setBill(bill3);
        record1.setDescription("The characteristics of someone or something");
        record1.setDoctor(doctor1);
        record1.setPatient(patient1);
        record1.setDischargePatient(true);

        MedicalProcedure medicalProcedure1 = new MedicalProcedure();
        medicalProcedure1.setName("Name");
        medicalProcedure1.setBillLine(serviceBillLine);
        medicalProcedure1.setEquipments(new ArrayList<Equipment>());
        medicalProcedure1.setRecord(record1);

        ServiceBillLine serviceBillLine1 = new ServiceBillLine();
        serviceBillLine1.setId(123L);
        serviceBillLine1.setPrice(10.0);
        serviceBillLine1.setBill(bill1);
        serviceBillLine1.setMedicalProcedure(medicalProcedure1);

        Bill bill4 = new Bill();
        bill4.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill4.setTotalPrice(10.0);
        bill4.setRecord(new Record());
        bill4.setBillLines(new HashSet<BillLine>());

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
        doctor2.setDepartment(new Department());

        Patient patient2 = new Patient();
        patient2.setDob(LocalDate.ofEpochDay(1L));
        patient2.setRecords(new HashSet<Record>());
        patient2.setGender(Gender.MALE);
        patient2.setName("Name");
        patient2.setPhoneNumber("4105551212");
        patient2.setAddress("42 Main St");

        Record record2 = new Record();
        record2.setDiagnosis("Diagnosis");
        record2.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record2.setPrescriptionMedicine(new HashSet<Prescription>());
        record2.setId(123L);
        record2.setBill(bill4);
        record2.setDescription("The characteristics of someone or something");
        record2.setDoctor(doctor2);
        record2.setPatient(patient2);
        record2.setDischargePatient(true);

        Bill bill5 = new Bill();
        bill5.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill5.setTotalPrice(10.0);
        bill5.setRecord(record2);
        bill5.setBillLines(new HashSet<BillLine>());

        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Doctor doctor3 = new Doctor();
        doctor3.setSpeciality("Speciality");
        doctor3.setAvailable(true);
        doctor3.setEmail("jane.doe@example.org");
        doctor3.setDob(LocalDate.ofEpochDay(1L));
        doctor3.setRecords(new HashSet<Record>());
        doctor3.setId(123L);
        doctor3.setName("Name");
        doctor3.setPhone("4105551212");
        doctor3.setSalary(10.0);
        doctor3.setDepartment(department);

        Patient patient3 = new Patient();
        patient3.setDob(LocalDate.ofEpochDay(1L));
        patient3.setRecords(new HashSet<Record>());
        patient3.setGender(Gender.MALE);
        patient3.setName("Name");
        patient3.setPhoneNumber("4105551212");
        patient3.setAddress("42 Main St");

        Record record3 = new Record();
        record3.setDiagnosis("Diagnosis");
        record3.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record3.setPrescriptionMedicine(new HashSet<Prescription>());
        record3.setId(123L);
        record3.setBill(bill5);
        record3.setDescription("The characteristics of someone or something");
        record3.setDoctor(doctor3);
        record3.setPatient(patient3);
        record3.setDischargePatient(true);

        MedicalProcedure medicalProcedure2 = new MedicalProcedure();
        medicalProcedure2.setName("Name");
        medicalProcedure2.setBillLine(serviceBillLine1);
        medicalProcedure2.setEquipments(new ArrayList<Equipment>());
        medicalProcedure2.setRecord(record3);
        when(this.procedureRepository.save((MedicalProcedure) any())).thenReturn(medicalProcedure2);
        assertSame(medicalProcedure2, this.procedureService.addProcedure(new MedicalProcedure()));
        verify(this.procedureRepository).save((MedicalProcedure) any());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(this.procedureRepository).deleteById((Long) any());
        this.procedureService.deleteById(123L);
        verify(this.procedureRepository).deleteById((Long) any());
    }

    @Test
    public void testFindAll() {
        ArrayList<MedicalProcedure> medicalProcedureList = new ArrayList<MedicalProcedure>();
        when(this.procedureRepository.findAll()).thenReturn(medicalProcedureList);
        List<MedicalProcedure> actualFindAllResult = this.procedureService.findAll();
        assertSame(medicalProcedureList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.procedureRepository).findAll();
    }

    @Test
    public void testFindAll2() {
        PageImpl<MedicalProcedure> pageImpl = new PageImpl<MedicalProcedure>(new ArrayList<MedicalProcedure>());
        when(this.procedureRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        Page<MedicalProcedure> actualFindAllResult = this.procedureService.findAll(null);
        assertSame(pageImpl, actualFindAllResult);
        assertTrue(actualFindAllResult.toList().isEmpty());
        verify(this.procedureRepository).findAll((Pageable) any());
    }

    @Test
    public void testGetById() {
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

        ServiceBillLine serviceBillLine = new ServiceBillLine();
        serviceBillLine.setId(123L);
        serviceBillLine.setPrice(10.0);
        serviceBillLine.setBill(new Bill());
        serviceBillLine.setMedicalProcedure(new MedicalProcedure());

        Record record1 = new Record();
        record1.setDiagnosis("Diagnosis");
        record1.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record1.setPrescriptionMedicine(new HashSet<Prescription>());
        record1.setId(123L);
        record1.setBill(new Bill());
        record1.setDescription("The characteristics of someone or something");
        record1.setDoctor(new Doctor());
        record1.setPatient(new Patient());
        record1.setDischargePatient(true);

        MedicalProcedure medicalProcedure = new MedicalProcedure();
        medicalProcedure.setName("Name");
        medicalProcedure.setBillLine(serviceBillLine);
        medicalProcedure.setEquipments(new ArrayList<Equipment>());
        medicalProcedure.setRecord(record1);

        ServiceBillLine serviceBillLine1 = new ServiceBillLine();
        serviceBillLine1.setId(123L);
        serviceBillLine1.setPrice(10.0);
        serviceBillLine1.setBill(bill);
        serviceBillLine1.setMedicalProcedure(medicalProcedure);

        Record record2 = new Record();
        record2.setDiagnosis("Diagnosis");
        record2.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record2.setPrescriptionMedicine(new HashSet<Prescription>());
        record2.setId(123L);
        record2.setBill(new Bill());
        record2.setDescription("The characteristics of someone or something");
        record2.setDoctor(new Doctor());
        record2.setPatient(new Patient());
        record2.setDischargePatient(true);

        Bill bill1 = new Bill();
        bill1.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill1.setTotalPrice(10.0);
        bill1.setRecord(record2);
        bill1.setBillLines(new HashSet<BillLine>());

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

        Record record3 = new Record();
        record3.setDiagnosis("Diagnosis");
        record3.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record3.setPrescriptionMedicine(new HashSet<Prescription>());
        record3.setId(123L);
        record3.setBill(bill1);
        record3.setDescription("The characteristics of someone or something");
        record3.setDoctor(doctor);
        record3.setPatient(patient);
        record3.setDischargePatient(true);

        MedicalProcedure medicalProcedure1 = new MedicalProcedure();
        medicalProcedure1.setName("Name");
        medicalProcedure1.setBillLine(serviceBillLine1);
        medicalProcedure1.setEquipments(new ArrayList<Equipment>());
        medicalProcedure1.setRecord(record3);
        Optional<MedicalProcedure> ofResult = Optional.<MedicalProcedure>of(medicalProcedure1);
        when(this.procedureRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(medicalProcedure1, this.procedureService.getById(123L));
        verify(this.procedureRepository).findById((Long) any());
    }
}

