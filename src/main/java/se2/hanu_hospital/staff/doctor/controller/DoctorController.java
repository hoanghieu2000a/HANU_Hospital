package se2.hanu_hospital.staff.doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.prescription.Prescription;
import se2.hanu_hospital.staff.doctor.doctorMapper.DoctorDTO;
import se2.hanu_hospital.staff.doctor.model.Doctor;
import se2.hanu_hospital.staff.doctor.service.DoctorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/doctor"})
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            doctorService.getById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            doctorService.deleteById(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> add(@RequestBody Doctor doctor) {
        try {
            doctorService.addDoctor(doctor);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateDoctorById(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        try {
            doctorService.updateById(id, doctorDTO);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/prescribe")
    public ResponseEntity<?> prescribe(@RequestBody Prescription prescription) {
        try {
            doctorService.prescribeMedicine(prescription);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/prescibe/update")
    public ResponseEntity<?> updatePrescription(@RequestBody Prescription prescription) {
        try {
            doctorService.updatePrescription(prescription);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/prescibe/delete/{id}")
    public ResponseEntity<?> deletePrescription(@PathVariable Long id) {
        try {
            doctorService.deletePrescription(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/available")
    public ResponseEntity<?> viewAvailableDoctor() {
        try {
            doctorService.getAvailableDoctors();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
