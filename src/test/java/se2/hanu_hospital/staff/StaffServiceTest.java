package se2.hanu_hospital.staff;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se2.hanu_hospital.department.Department;

@ContextConfiguration(classes = {StaffService.class})
@ExtendWith(SpringExtension.class)
public class StaffServiceTest {
    @MockBean
    private StaffRepository staffRepository;

    @Autowired
    private StaffService staffService;

    @Test
    public void testGetStaffById() {
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
        when(this.staffRepository.getStaffById((Long) any())).thenReturn(staff);
        assertSame(staff, this.staffService.getStaffById(123L));
        verify(this.staffRepository).getStaffById((Long) any());
    }

    @Test
    public void testUpdateStaffDepartment() {
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
        Optional<Staff> ofResult = Optional.<Staff>of(staff);

        Department department1 = new Department();
        department1.setName("Name1");
        department1.setStaff(new HashSet<Staff>());

        Staff staff1 = new Staff();
        staff1.setEmail("jane.doe@example.org");
        staff1.setDob(LocalDate.ofEpochDay(1L));
        staff1.setId(123L);
        staff1.setName("Name");
        staff1.setPhone("4105551212");
        staff1.setSalary(10.0);
        staff1.setDepartment(department1);
        when(this.staffRepository.save((Staff) any())).thenReturn(staff1);
        when(this.staffRepository.findById((Long) any())).thenReturn(ofResult);
        this.staffService.updateStaffDepartment(123L, new Staff());
        verify(this.staffRepository).save((Staff) any());
        verify(this.staffRepository).findById((Long) any());
    }

    @Test
    public void testUpdateStaffDepartment2() {
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
        when(this.staffRepository.save((Staff) any())).thenReturn(staff);
        when(this.staffRepository.findById((Long) any())).thenReturn(Optional.<Staff>empty());
        assertThrows(IllegalStateException.class, () -> this.staffService.updateStaffDepartment(123L, new Staff()));
        verify(this.staffRepository).findById((Long) any());
    }

    @Test
    public void testUpdateStaffDepartment3() {
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
        Optional<Staff> ofResult = Optional.<Staff>of(staff);

        Department department1 = new Department();
        department1.setName("Name");
        department1.setStaff(new HashSet<Staff>());

        Staff staff1 = new Staff();
        staff1.setEmail("jane.doe@example.org");
        staff1.setDob(LocalDate.ofEpochDay(1L));
        staff1.setId(123L);
        staff1.setName("Name");
        staff1.setPhone("4105551212");
        staff1.setSalary(10.0);
        staff1.setDepartment(department1);
        when(this.staffRepository.save((Staff) any())).thenReturn(staff1);
        when(this.staffRepository.findById((Long) any())).thenReturn(ofResult);

        Department department2 = new Department();
        department2.setName("Name");
        department2.setStaff(new HashSet<Staff>());
        Staff staff2 = mock(Staff.class);
        when(staff2.getDepartment()).thenReturn(department2);
        this.staffService.updateStaffDepartment(123L, staff2);
        verify(staff2).getDepartment();
        verify(this.staffRepository).save((Staff) any());
        verify(this.staffRepository).findById((Long) any());
    }
}

