package se2.hanu_hospital.billline;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/billlines")
@RequiredArgsConstructor
public class BillLineController {

    private final BillLineService service;

    public BillLineController(BillLineService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BillLine>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BillLine> getBillLine(@PathVariable long id ) {
        return ResponseEntity.ok(service.getBillLine(id));
    }

    @PostMapping
    public ResponseEntity<Void> addBillLine(@RequestBody BillLine billLine) {
        service.addBillLine(billLine);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateBillLine(@PathVariable long id, @RequestBody BillLine billLine) {
        service.updateBillLine(id, billLine);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBillLine(@PathVariable long id) {
        service.deleteBillLine(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
