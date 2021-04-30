package com.example.api.core.bill;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bills")
public class    BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBill() {
        return ResponseEntity.ok(billService.getAllBill());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Bill> getBill(@PathVariable long id) {
        return ResponseEntity.ok(billService.getBill(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateBill(@PathVariable long id, @RequestBody Bill bill) {
        billService.updateBill(id, bill);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping
    public ResponseEntity<Void> addBill(@RequestBody Bill bill) {
        billService.addBill(bill);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable long id) {
        billService.deleteBill(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
