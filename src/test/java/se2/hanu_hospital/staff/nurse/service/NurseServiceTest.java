package se2.hanu_hospital.staff.nurse.service;

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
import se2.hanu_hospital.record.RecordService;
import se2.hanu_hospital.staff.Staff;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.staff.doctor.repository.DoctorRepository;
import se2.hanu_hospital.staff.nurse.model.Nurse;
import se2.hanu_hospital.staff.nurse.nurseMapper.NurseDTO;
import se2.hanu_hospital.staff.nurse.nurseMapper.NurseMapper;
import se2.hanu_hospital.staff.nurse.repository.NurseRepository;

@ContextConfiguration(classes = {NurseService.class, RecordService.class})
@ExtendWith(SpringExtension.class)
public class NurseServiceTest {
    @MockBean
    private DoctorRepository doctorRepository;

    @MockBean
    private NurseMapper nurseMapper;

    @MockBean
    private NurseRepository nurseRepository;

    @Autowired
    private NurseService nurseService;

    @MockBean
    private RecordService recordService;

    @Test
    public void testGetAllNurse() {
        ArrayList<Nurse> nurseList = new ArrayList<Nurse>();
        when(this.nurseRepository.getAllNurse()).thenReturn(nurseList);
        List<Nurse> actualAllNurse = this.nurseService.getAllNurse();
        assertSame(nurseList, actualAllNurse);
        assertTrue(actualAllNurse.isEmpty());
        verify(this.nurseRepository).getAllNurse();
    }

    @Test
    public void testAddNurse() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Nurse nurse = new Nurse();
        nurse.setEmail("jane.doe@example.org");
        nurse.setDob(LocalDate.ofEpochDay(1L));
        nurse.setId(123L);
        nurse.setName("Name");
        nurse.setPhone("4105551212");
        nurse.setSalary(10.0);
        nurse.setDepartment(department);
        when(this.nurseRepository.save((Nurse) any())).thenReturn(nurse);
        this.nurseService.addNurse(new Nurse());
        verify(this.nurseRepository).save((Nurse) any());
    }

    @Test
    public void testUpdateById() throws IOException {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Nurse nurse = new Nurse();
        nurse.setEmail("jane.doe@example.org");
        nurse.setDob(LocalDate.ofEpochDay(1L));
        nurse.setId(123L);
        nurse.setName("Name");
        nurse.setPhone("4105551212");
        nurse.setSalary(10.0);
        nurse.setDepartment(department);

        Department department1 = new Department();
        department1.setName("Name");
        department1.setStaff(new HashSet<Staff>());

        Nurse nurse1 = new Nurse();
        nurse1.setEmail("jane.doe@example.org");
        nurse1.setDob(LocalDate.ofEpochDay(1L));
        nurse1.setId(123L);
        nurse1.setName("Name");
        nurse1.setPhone("4105551212");
        nurse1.setSalary(10.0);
        nurse1.setDepartment(department1);
        when(this.nurseRepository.save((Nurse) any())).thenReturn(nurse1);
        when(this.nurseRepository.getNurseById((Long) any())).thenReturn(nurse);
        when(this.nurseRepository.existsById((Long) any())).thenReturn(true);
        doNothing().when(this.nurseMapper).updateNurseFromDto((NurseDTO) any(), (Nurse) any());
        this.nurseService.updateById(123L, new NurseDTO());
        verify(this.nurseMapper).updateNurseFromDto((NurseDTO) any(), (Nurse) any());
        verify(this.nurseRepository).save((Nurse) any());
        verify(this.nurseRepository).getNurseById((Long) any());
        verify(this.nurseRepository).existsById((Long) any());
    }

    @Test
    public void testUpdateById2() throws IOException {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Nurse nurse = new Nurse();
        nurse.setEmail("jane.doe@example.org");
        nurse.setDob(LocalDate.ofEpochDay(1L));
        nurse.setId(123L);
        nurse.setName("Name");
        nurse.setPhone("4105551212");
        nurse.setSalary(10.0);
        nurse.setDepartment(department);

        Department department1 = new Department();
        department1.setName("Name");
        department1.setStaff(new HashSet<Staff>());

        Nurse nurse1 = new Nurse();
        nurse1.setEmail("jane.doe@example.org");
        nurse1.setDob(LocalDate.ofEpochDay(1L));
        nurse1.setId(123L);
        nurse1.setName("Name");
        nurse1.setPhone("4105551212");
        nurse1.setSalary(10.0);
        nurse1.setDepartment(department1);
        when(this.nurseRepository.save((Nurse) any())).thenReturn(nurse1);
        when(this.nurseRepository.getNurseById((Long) any())).thenReturn(nurse);
        when(this.nurseRepository.existsById((Long) any())).thenReturn(false);
        doNothing().when(this.nurseMapper).updateNurseFromDto((NurseDTO) any(), (Nurse) any());
        assertThrows(IllegalStateException.class, () -> this.nurseService.updateById(123L, new NurseDTO()));
        verify(this.nurseRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(this.nurseRepository).deleteById((Long) any());
        this.nurseService.deleteById(123L);
        verify(this.nurseRepository).deleteById((Long) any());
    }

    @Test
    public void testGetById() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Nurse nurse = new Nurse();
        nurse.setEmail("jane.doe@example.org");
        nurse.setDob(LocalDate.ofEpochDay(1L));
        nurse.setId(123L);
        nurse.setName("Name");
        nurse.setPhone("4105551212");
        nurse.setSalary(10.0);
        nurse.setDepartment(department);
        when(this.nurseRepository.getNurseById((Long) any())).thenReturn(nurse);
        assertSame(nurse, this.nurseService.getById(123L));
        verify(this.nurseRepository).getNurseById((Long) any());
    }

    @Test
    public void testCheckAvailableDoctor() {
        ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
        when(this.doctorRepository.getDoctorByAvailableIsTrue()).thenReturn(doctorList);
        List<Doctor> actualCheckAvailableDoctorResult = this.nurseService.checkAvailableDoctor();
        assertSame(doctorList, actualCheckAvailableDoctorResult);
        assertTrue(actualCheckAvailableDoctorResult.isEmpty());
        verify(this.doctorRepository).getDoctorByAvailableIsTrue();
    }
}

