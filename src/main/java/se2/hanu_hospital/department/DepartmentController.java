package se2.hanu_hospital.department;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<?> getDepartmentById (@PathVariable("id") Long id){
        try{
            return new ResponseEntity<>(departmentService.getDepartmentById(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getByName/{name}")
    public ResponseEntity<?> getDepartmentByName (@PathVariable("name") String name){
        try{
            return new ResponseEntity<>(departmentService.getDepartmentByName(name), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addDepartment(@RequestBody Department department){
        try {
            departmentService.addDepartment(department);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id, @RequestBody DepartmentPayload departmentPayload){
        try {
            departmentService.updateDepartment(id, departmentPayload);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/addStaff/{id}")
    public ResponseEntity<?> addStaffToDepartment(@PathVariable Long id, @RequestParam Long staffId){
        try {
            departmentService.addStaffToDepartment(id, staffId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/removeStaff")
    public ResponseEntity<?> removeStaffFromDepartment(@RequestParam Long staffId){
        try {
            departmentService.removeStaffFromDepartment(staffId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long id){
        try{
            departmentService.deleteDepartment(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
