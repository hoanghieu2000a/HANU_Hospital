package se2.hanu_hospital.Medicine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/medicine"})
public class MedicineController {
    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getMedicines (){
        try {
            return new ResponseEntity<>(medicineService.getMedicine(), HttpStatus.OK);

        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addMedicine(@RequestBody Medicine medicine){
        try {
            medicineService.addMedicine(medicine);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity<?> deleteMedicine(@PathVariable("id") Long id){
        try{
            medicineService.deleteMedicine(id);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getProfit/{id}")
    public ResponseEntity<?> getProfit(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(medicineService.getProfit(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/isExprired/{id}")
    public ResponseEntity<?> isExpired(@PathVariable("id") Long id){
        try{
            return new ResponseEntity<>(medicineService.isExpired(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<?> getMedicineById (@PathVariable("id") Long id){
        try{
            return new ResponseEntity<>(medicineService.getMedicineById(id), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> updateMedicine(@RequestBody Medicine medicine){
        try {
            medicineService.updateMedicine(medicine);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
