package se2.hanu_hospital.staff.doctor.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se2.hanu_hospital.department.Department;
import se2.hanu_hospital.record.Record;
import se2.hanu_hospital.staff.Staff;
import se2.hanu_hospital.staff.doctor.doctorMapper.DoctorDTO;
import se2.hanu_hospital.staff.doctor.doctorMapper.DoctorMapper;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.staff.doctor.repository.DoctorRepository;

@ContextConfiguration(classes = {DoctorService.class})
@ExtendWith(SpringExtension.class)
public class DoctorServiceTest {
    @MockBean
    private DoctorMapper doctorMapper;

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    @Test
    public void testGetAllDoctors() {
        ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
        when(this.doctorRepository.getAllDoctors()).thenReturn(doctorList);
        List<Doctor> actualAllDoctors = this.doctorService.getAllDoctors();
        assertSame(doctorList, actualAllDoctors);
        assertTrue(actualAllDoctors.isEmpty());
        verify(this.doctorRepository).getAllDoctors();
    }

    @Test
    public void testAddDoctor() {
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
        when(this.doctorRepository.save((Doctor) any())).thenReturn(doctor);
        this.doctorService.addDoctor(new Doctor());
        verify(this.doctorRepository).save((Doctor) any());
    }

    @Test
    public void testUpdateById() throws IOException {
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
        when(this.doctorRepository.save((Doctor) any())).thenReturn(doctor1);
        when(this.doctorRepository.getDoctorById((Long) any())).thenReturn(doctor);
        when(this.doctorRepository.existsById((Long) any())).thenReturn(true);
        doNothing().when(this.doctorMapper).updateDoctorFromDto((DoctorDTO) any(), (Doctor) any());
        this.doctorService.updateById(123L, new DoctorDTO());
        verify(this.doctorMapper).updateDoctorFromDto((DoctorDTO) any(), (Doctor) any());
        verify(this.doctorRepository).save((Doctor) any());
        verify(this.doctorRepository).getDoctorById((Long) any());
        verify(this.doctorRepository).existsById((Long) any());
    }

    @Test
    public void testUpdateById2() throws IOException {
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
        when(this.doctorRepository.save((Doctor) any())).thenReturn(doctor1);
        when(this.doctorRepository.getDoctorById((Long) any())).thenReturn(doctor);
        when(this.doctorRepository.existsById((Long) any())).thenReturn(false);
        doNothing().when(this.doctorMapper).updateDoctorFromDto((DoctorDTO) any(), (Doctor) any());
        assertThrows(IllegalStateException.class, () -> this.doctorService.updateById(123L, new DoctorDTO()));
        verify(this.doctorRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(this.doctorRepository).deleteById((Long) any());
        this.doctorService.deleteById(123L);
        verify(this.doctorRepository).deleteById((Long) any());
    }

    @Test
    public void testGetById() {
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
        when(this.doctorRepository.getDoctorById((Long) any())).thenReturn(doctor);
        assertSame(doctor, this.doctorService.getById(123L));
        verify(this.doctorRepository).getDoctorById((Long) any());
    }

    @Test
    public void testGetAvailableDoctors() {
        ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
        when(this.doctorRepository.getDoctorByAvailableIsTrue()).thenReturn(doctorList);
        List<Doctor> actualAvailableDoctors = this.doctorService.getAvailableDoctors();
        assertSame(doctorList, actualAvailableDoctors);
        assertTrue(actualAvailableDoctors.isEmpty());
        verify(this.doctorRepository).getDoctorByAvailableIsTrue();
    }
}

