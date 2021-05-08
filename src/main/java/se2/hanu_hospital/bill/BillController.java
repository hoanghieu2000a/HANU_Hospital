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
}
