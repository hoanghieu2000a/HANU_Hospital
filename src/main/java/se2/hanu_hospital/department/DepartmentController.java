package se2.hanu_hospital.department;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.department.DepartmentMapper.DepartmentDTO;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/department"})
public class DepartmentController {
    @Autowired
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> getDepartments (){
        try {
            return new ResponseEntity<>(departmentService.getDepartment(), HttpStatus.OK);

        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<?> getDepartmentById (@PathVariable("id") Long id){
        try{
            return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addDepartment(@RequestBody Department department){
        try {
            departmentService.addDepartment(department);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO){
        try {
            departmentService.updateDepartment(id,departmentDTO);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/addStaff/{id}")
    public ResponseEntity<?> addStaffToDepartment(@PathVariable Long id, @RequestParam Long staffId){
        try {
            departmentService.addStaffToDepartment(id, staffId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/removeStaff")
    public ResponseEntity<?> removeStaffFromDepartment(@RequestParam Long staffId){
        try {
            departmentService.removeStaffFromDepartment(staffId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long id){
        try{
            departmentService.deleteDepartment(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
