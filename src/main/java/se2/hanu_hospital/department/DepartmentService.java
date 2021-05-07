package se2.hanu_hospital.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.department.DepartmentMapper.DepartmentDTO;
import se2.hanu_hospital.department.DepartmentMapper.DepartmentsMapper;
import se2.hanu_hospital.staff.Staff;
import se2.hanu_hospital.staff.StaffService;


import java.io.IOException;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentsMapper mapper;
    @Autowired
    private StaffService staffService;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getDepartment() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Department does not exist!"));
    }

    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void updateDepartment(Long id, DepartmentDTO departmentDTO) throws IOException {
        if(!departmentRepository.existsById(id)){
            throw new IllegalStateException("There is no department with that id!");
        }

        Department departmentInDB =  departmentRepository.getDepartmentById(id);
        mapper.updateDoctorFromDto(departmentDTO, departmentInDB);
        departmentRepository.save(departmentInDB);
    }

    public void deleteDepartment(Long id) {
        if(!departmentRepository.existsById(id)){
            throw new IllegalStateException("There is no department with that id!");
        }

        departmentRepository.deleteById(id);
    }

    public void addStaffToDepartment(Long id, Long staffId) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Medicine does not exist!"));

        Staff staff = staffService.getStaffById(staffId);
        staff.setDepartment(department);
        staffService.updateStaffDepartment(staffId, staff);
    }

    public void removeStaffFromDepartment(Long staffId) {
        Staff staff = staffService.getStaffById(staffId);
        staff.setDepartment(null);
        staffService.updateStaffDepartment(staffId, staff);
    }
}
