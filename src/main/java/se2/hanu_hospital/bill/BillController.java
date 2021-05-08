package se2.hanu_hospital.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bills")
public class BillController {

    @Autowired
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Bill>> getAllBill() {
        return ResponseEntity.ok(billService.getAllBill());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Bill> getBill(@PathVariable long id) {
        return ResponseEntity.ok(billService.getBill(id));
    }

    @GetMapping(value = "/income")
    public ResponseEntity<?> getIncome() {
        try{
            return new ResponseEntity<>(billService.getIncome(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/expend")
    public ResponseEntity<?> getExpend() {
        try{
            return new ResponseEntity<>(billService.getExpend(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/profit")
    public ResponseEntity<?> getProfit() {
        try{
            return new ResponseEntity<>(billService.getProfit(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
