package se2.hanu_hospital.billline;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se2.hanu_hospital.bill.Bill;
import se2.hanu_hospital.department.Department;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;
import se2.hanu_hospital.patient.Patient;
import se2.hanu_hospital.prescription.Prescription;
import se2.hanu_hospital.record.Record;
import se2.hanu_hospital.staff.Staff;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.util.Gender;

@ContextConfiguration(classes = {BillLineService.class})
@ExtendWith(SpringExtension.class)
public class BillLineServiceTest {
    @MockBean
    private BillLineRepository billLineRepository;

    @Autowired
    private BillLineService billLineService;

    @Test
    public void testGetBillLine() {
        Bill bill = new Bill();
        bill.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill.setTotalPrice(10.0);
        bill.setBillLines(new HashSet<BillLine>());
        bill.setRecord(new Record());

        Doctor doctor = new Doctor();
        doctor.setSpeciality("Speciality");
        doctor.setAvailable(true);
        doctor.setEmail("jane.doe@example.org");
        doctor.setDob(LocalDate.ofEpochDay(1L));
        doctor.setRecords(new HashSet<Record>());
        doctor.setId(123L);
        doctor.setPhone("4105551212");
        doctor.setName("Name");
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
        record.setId(123L);
        record.setPrescriptionMedicine(new HashSet<Prescription>());
        record.setBill(bill);
        record.setDoctor(doctor);
        record.setDescription("The characteristics of someone or something");
        record.setPatient(patient);
        record.setDischargePatient(true);

        Bill bill1 = new Bill();
        bill1.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill1.setTotalPrice(10.0);
        bill1.setBillLines(new HashSet<BillLine>());
        bill1.setRecord(record);

        BillLine billLine = new BillLine();
        billLine.setPrice(10.0);
        billLine.setId(123L);
        billLine.setBill(bill1);
        Optional<BillLine> ofResult = Optional.<BillLine>of(billLine);
        when(this.billLineRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(billLine, this.billLineService.getBillLine(123L));
        verify(this.billLineRepository).findById((Long) any());
    }

    @Test
    public void testAddBillLine() {
        Record record = new Record();
        record.setDiagnosis("Diagnosis");
        record.setMedicalProcedure(new HashSet<MedicalProcedure>());
        record.setId(123L);
        record.setPrescriptionMedicine(new HashSet<Prescription>());
        record.setBill(new Bill());
        record.setDoctor(new Doctor());
        record.setDescription("The characteristics of someone or something");
        record.setPatient(new Patient());
        record.setDischargePatient(true);

        Bill bill = new Bill();
        bill.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill.setTotalPrice(10.0);
        bill.setBillLines(new HashSet<BillLine>());
        bill.setRecord(record);

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
        doctor.setPhone("4105551212");
        doctor.setName("Name");
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
        record1.setId(123L);
        record1.setPrescriptionMedicine(new HashSet<Prescription>());
        record1.setBill(bill);
        record1.setDoctor(doctor);
        record1.setDescription("The characteristics of someone or something");
        record1.setPatient(patient);
        record1.setDischargePatient(true);

        Bill bill1 = new Bill();
        bill1.setCreatedAt(LocalDate.ofEpochDay(1L));
        bill1.setTotalPrice(10.0);
        bill1.setBillLines(new HashSet<BillLine>());
        bill1.setRecord(record1);

        BillLine billLine = new BillLine();
        billLine.setPrice(10.0);
        billLine.setId(123L);
        billLine.setBill(bill1);
        when(this.billLineRepository.save((BillLine) any())).thenReturn(billLine);
        this.billLineService.addBillLine(new BillLine());
        verify(this.billLineRepository).save((BillLine) any());
    }

    @Test
    public void testGetAll() {
        MedicalBillLine billLine = new MedicalBillLine();
        billLine.setPrice(10.0);

        ServiceBillLine billLine2 = new ServiceBillLine();
        billLine2.setPrice(20.0);

        ArrayList<BillLine> billLineList = new ArrayList<BillLine>();
        billLineList.add(billLine);
        billLineList.add(billLine2);

        when(this.billLineRepository.findAll()).thenReturn(billLineList);
        List<BillLine> actualAll = this.billLineService.getAll();
        assertSame(billLineList, actualAll);
        verify(this.billLineRepository).findAll();
    }
}

