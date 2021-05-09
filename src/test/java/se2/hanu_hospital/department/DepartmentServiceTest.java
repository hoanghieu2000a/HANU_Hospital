package se2.hanu_hospital.department;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se2.hanu_hospital.staff.Staff;
import se2.hanu_hospital.staff.StaffService;

@ContextConfiguration(classes = {DepartmentService.class, StaffService.class})
@ExtendWith(SpringExtension.class)
public class DepartmentServiceTest {
    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private StaffService staffService;

    @Test
    public void testGetDepartment() {
        ArrayList<Department> departmentList = new ArrayList<Department>();
        when(this.departmentRepository.findAll()).thenReturn(departmentList);
        List<Department> actualDepartment = this.departmentService.getDepartment();
        assertSame(departmentList, actualDepartment);
        assertTrue(actualDepartment.isEmpty());
        verify(this.departmentRepository).findAll();
    }

    @Test
    public void testGetDepartmentById() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());
        Optional<Department> ofResult = Optional.<Department>of(department);
        when(this.departmentRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(department, this.departmentService.getDepartmentById(123L));
        verify(this.departmentRepository).findById((Long) any());
    }

    @Test
    public void testGetDepartmentById2() {
        when(this.departmentRepository.findById((Long) any())).thenReturn(Optional.<Department>empty());
        assertThrows(IllegalStateException.class, () -> this.departmentService.getDepartmentById(123L));
        verify(this.departmentRepository).findById((Long) any());
    }

    @Test
    public void testGetDepartmentByName() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());
        when(this.departmentRepository.findByNameContaining(anyString())).thenReturn(department);
        assertSame(department, this.departmentService.getDepartmentByName("Name"));
        verify(this.departmentRepository).findByNameContaining(anyString());
    }

    @Test
    public void testAddDepartment() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());
        when(this.departmentRepository.save((Department) any())).thenReturn(department);
        this.departmentService.addDepartment(new Department());
        verify(this.departmentRepository).save((Department) any());
    }

    @Test
    public void testUpdateDepartment() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Department department1 = new Department();
        department1.setName("Name");
        department1.setStaff(new HashSet<Staff>());
        when(this.departmentRepository.save((Department) any())).thenReturn(department1);
        when(this.departmentRepository.getDepartmentById((Long) any())).thenReturn(department);
        when(this.departmentRepository.existsById((Long) any())).thenReturn(true);
        this.departmentService.updateDepartment(123L, new DepartmentPayload());
        verify(this.departmentRepository).save((Department) any());
        verify(this.departmentRepository).getDepartmentById((Long) any());
        verify(this.departmentRepository).existsById((Long) any());
    }

    @Test
    public void testUpdateDepartment2() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Department department1 = new Department();
        department1.setName("Name");
        department1.setStaff(new HashSet<Staff>());
        when(this.departmentRepository.save((Department) any())).thenReturn(department1);
        when(this.departmentRepository.getDepartmentById((Long) any())).thenReturn(department);
        when(this.departmentRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(IllegalStateException.class,
                () -> this.departmentService.updateDepartment(123L, new DepartmentPayload()));
        verify(this.departmentRepository).existsById((Long) any());
    }

    @Test
    public void testUpdateDepartment3() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Department department1 = new Department();
        department1.setName("Name");
        department1.setStaff(new HashSet<Staff>());
        when(this.departmentRepository.save((Department) any())).thenReturn(department1);
        when(this.departmentRepository.getDepartmentById((Long) any())).thenReturn(department);
        when(this.departmentRepository.existsById((Long) any())).thenReturn(true);

        DepartmentPayload departmentPayload = new DepartmentPayload();
        departmentPayload.setName("Name2");
        this.departmentService.updateDepartment(123L, departmentPayload);
        verify(this.departmentRepository).save((Department) any());
        verify(this.departmentRepository).getDepartmentById((Long) any());
        verify(this.departmentRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteDepartment() {
        doNothing().when(this.departmentRepository).deleteById((Long) any());
        when(this.departmentRepository.existsById((Long) any())).thenReturn(true);
        this.departmentService.deleteDepartment(123L);
        verify(this.departmentRepository).deleteById((Long) any());
        verify(this.departmentRepository).existsById((Long) any());
    }

    @Test
    public void testDeleteDepartment2() {
        doNothing().when(this.departmentRepository).deleteById((Long) any());
        when(this.departmentRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> this.departmentService.deleteDepartment(123L));
        verify(this.departmentRepository).existsById((Long) any());
    }

    @Test
    public void testAddStaffToDepartment() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Staff staff = new Staff();
        staff.setEmail("jane.doe@example.org");
        staff.setDob(LocalDate.ofEpochDay(1L));
        staff.setId(123L);
        staff.setName("Name");
        staff.setPhone("4105551212");
        staff.setSalary(10.0);
        staff.setDepartment(department);
        doNothing().when(this.staffService).updateStaffDepartment((Long) any(), (Staff) any());
        when(this.staffService.getStaffById((Long) any())).thenReturn(staff);

        Department department1 = new Department();
        department1.setName("Name");
        department1.setStaff(new HashSet<Staff>());
        Optional<Department> ofResult = Optional.<Department>of(department1);
        when(this.departmentRepository.findById((Long) any())).thenReturn(ofResult);
        this.departmentService.addStaffToDepartment(123L, 123L);
        verify(this.departmentRepository).findById((Long) any());
        verify(this.staffService).getStaffById((Long) any());
        verify(this.staffService).updateStaffDepartment((Long) any(), (Staff) any());
    }

    @Test
    public void testAddStaffToDepartment2() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Staff staff = new Staff();
        staff.setEmail("jane.doe@example.org");
        staff.setDob(LocalDate.ofEpochDay(1L));
        staff.setId(123L);
        staff.setName("Name");
        staff.setPhone("4105551212");
        staff.setSalary(10.0);
        staff.setDepartment(department);
        doNothing().when(this.staffService).updateStaffDepartment((Long) any(), (Staff) any());
        when(this.staffService.getStaffById((Long) any())).thenReturn(staff);
        when(this.departmentRepository.findById((Long) any())).thenReturn(Optional.<Department>empty());
        assertThrows(IllegalStateException.class, () -> this.departmentService.addStaffToDepartment(123L, 123L));
        verify(this.departmentRepository).findById((Long) any());
    }

    @Test
    public void testRemoveStaffFromDepartment() {
        Department department = new Department();
        department.setName("Name");
        department.setStaff(new HashSet<Staff>());

        Staff staff = new Staff();
        staff.setEmail("jane.doe@example.org");
        staff.setDob(LocalDate.ofEpochDay(1L));
        staff.setId(123L);
        staff.setName("Name");
        staff.setPhone("4105551212");
        staff.setSalary(10.0);
        staff.setDepartment(department);
        doNothing().when(this.staffService).updateStaffDepartment((Long) any(), (Staff) any());
        when(this.staffService.getStaffById((Long) any())).thenReturn(staff);
        this.departmentService.removeStaffFromDepartment(123L);
        verify(this.staffService).getStaffById((Long) any());
        verify(this.staffService).updateStaffDepartment((Long) any(), (Staff) any());
    }
}

