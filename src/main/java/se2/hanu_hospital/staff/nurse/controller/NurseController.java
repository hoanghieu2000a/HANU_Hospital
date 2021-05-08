package se2.hanu_hospital.staff.nurse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.staff.nurse.model.Nurse;
import se2.hanu_hospital.staff.nurse.nurseMapper.NurseDTO;
import se2.hanu_hospital.staff.nurse.service.NurseService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/nurse"})
public class NurseController {
    private final NurseService nurseService;

    @Autowired
    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(nurseService.getAllNurse(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(nurseService.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            nurseService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> add(@RequestBody Nurse nurse) {
        try {
            nurseService.addNurse(nurse);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateNurseById(@PathVariable Long id, @RequestBody NurseDTO nurseDTO) {
        try {
            nurseService.updateById(id, nurseDTO);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/assignDoctor/{id}")
    public ResponseEntity<?> assignDoctor(@PathVariable Long id) {
        try {
            nurseService.assignDoctor(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/checkAvailable")
    public ResponseEntity<?> checkAvailableDoctor() {
        try {
            nurseService.checkAvailableDoctor();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
