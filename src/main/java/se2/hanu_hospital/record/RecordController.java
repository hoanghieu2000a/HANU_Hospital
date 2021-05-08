package se2.hanu_hospital.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.medicine.Medicine;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/record"})
public class RecordController {
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAllRecord (){
        try {
            return new ResponseEntity<>(recordService.getAllRecord(), HttpStatus.OK);

        } catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addRecord(@RequestBody RecordPayload recordPayload){
        try {
            recordService.addRecord(recordPayload);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity<?> deleteRecord(@PathVariable("id") Long id){
        try{
            recordService.deleteRecord(id);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<?> getRecordById (@PathVariable("id") Long id){
        try{
            return new ResponseEntity<>(recordService.getRecordById(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> updateRecord(@RequestBody RecordPayload recordPayload){
        try {
            recordService.updateRecord(recordPayload);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/addPrescription/{id}")
    public ResponseEntity<?> addPrescriptionToRecord(@PathVariable Long id, @RequestParam Long prescriptionId){
        try {
            recordService.addPrescriptionToRecord(id, prescriptionId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/addMedicalProcedure/{id}")
    public ResponseEntity<?> addMedicalProcedureToRecord(@PathVariable Long id, @RequestParam Long medicalProcedureId){
        try {
            recordService.addMedicalProcedureToRecord(id, medicalProcedureId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getRecordByPatientId/{id}")
    public ResponseEntity<?> getRecordByPatientId (@PathVariable("id") Long id){
        try{
            return new ResponseEntity<>(recordService.getRecordByPatientId(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getRecordByDoctorId/{id}")
    public ResponseEntity<?> getRecordByDoctorId (@PathVariable("id") Long id){
        try{
            return new ResponseEntity<>(recordService.getRecordByDoctorId(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getRecordNotDischarged")
    public ResponseEntity<?> getRecordNotDischarged (){
        try{
            return new ResponseEntity<>(recordService.getRecordNotDischarged(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/discharge/{id}")
    public ResponseEntity<?> dischargePatient (@PathVariable("id") Long id){
        try{
            recordService.dischargePatient(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
