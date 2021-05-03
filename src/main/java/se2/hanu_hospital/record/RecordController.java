package se2.hanu_hospital.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.medicine.Medicine;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/record"})
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
    public ResponseEntity<?> addRecord(@RequestBody Record record){
        try {
            recordService.addRecord(record);
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
    public ResponseEntity<?> updateRecord(@RequestBody Record record){
        try {
            recordService.updateRecord(record);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
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
}
