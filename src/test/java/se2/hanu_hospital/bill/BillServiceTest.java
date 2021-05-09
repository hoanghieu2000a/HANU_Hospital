package se2.hanu_hospital.bill;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se2.hanu_hospital.billline.BillLine;
import se2.hanu_hospital.billline.BillLineService;
import se2.hanu_hospital.department.Department;
import se2.hanu_hospital.medical_procedure.MedicalProcedure;
import se2.hanu_hospital.medicine.Medicine;
import se2.hanu_hospital.medicine.MedicineService;
import se2.hanu_hospital.patient.Patient;
import se2.hanu_hospital.prescription.Prescription;
import se2.hanu_hospital.record.Record;
import se2.hanu_hospital.staff.Staff;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.staff.doctor.service.DoctorService;
import se2.hanu_hospital.util.Gender;

@ContextConfiguration(classes = {BillLineService.class, BillService.class, MedicineService.class})
@ExtendWith(SpringExtension.class)
public class BillServiceTest {
    @MockBean
    private BillLineService billLineService;

    @MockBean
    private BillRepository billRepository;

    @Autowired
    private BillService billService;

    @MockBean
    private MedicineService medicineService;

    @MockBean
    private DoctorService doctorService;

    @Test
    public void testGetBill() {
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
        Optional<Bill> ofResult = Optional.<Bill>of(bill1);
        when(this.billRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(bill1, this.billService.getBill(123L));
        verify(this.billRepository).findById((Long) any());
    }

    @Test
    public void testGetAllBill() {
        ArrayList<Bill> billList = new ArrayList<Bill>();
        when(this.billRepository.findAll()).thenReturn(billList);
        List<Bill> actualAllBill = this.billService.getAllBill();
        assertSame(billList, actualAllBill);
        assertTrue(actualAllBill.isEmpty());
        verify(this.billRepository).findAll();
    }

    @Test
    public void testGetIncome() {
        Bill bill = new Bill();
        bill.setCreatedAt(LocalDate.now());
        bill.setTotalPrice(100.0);
        bill.setBillLines(new HashSet<BillLine>());

        ArrayList<Bill> billList = new ArrayList<Bill>();
        billList.add(bill);
        when(this.billRepository.findAll()).thenReturn(billList);

        assertEquals(100.0, this.billService.getIncome());
    }

    @Test
    public void testGetExpend() {
        Medicine medicine = new Medicine();
        medicine.setExpireDate(LocalDate.now());
        medicine.setImportDate(LocalDate.now());
        medicine.setImportPrice(10.0);
        medicine.setId(123L);
        medicine.setSellPrice(10.0);
        medicine.setName("Name");
        medicine.setQuantity(1);

        ArrayList<Medicine> medicineList = new ArrayList<Medicine>();
        medicineList.add(medicine);
        when(this.medicineService.getMedicine()).thenReturn(medicineList);

        Doctor doctor = new Doctor();
        doctor.setSpeciality("Speciality");
        doctor.setAvailable(true);
        doctor.setEmail("jane.doe@example.org");
        doctor.setDob(LocalDate.now());
        doctor.setRecords(new HashSet<Record>());
        doctor.setId(123L);
        doctor.setPhone("4105551212");
        doctor.setName("Name");
        doctor.setSalary(10.0);

        ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
        doctorList.add(doctor);
        when(this.doctorService.getAllDoctors()).thenReturn(doctorList);

        assertEquals(20.0, this.billService.getExpend());
    }
}

