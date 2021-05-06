package se2.hanu_hospital.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public Staff getStaffById(Long id){
        return staffRepository.getStaffById(id);
    }

    public void updateStaffDepartment(Long id, Staff staff){
        Staff staffInDB = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Department does not exist!"));

        staffInDB.setDepartment(staff.getDepartment());
        staffRepository.save(staffInDB);
    }
}
