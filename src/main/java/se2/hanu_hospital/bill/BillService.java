package se2.hanu_hospital.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se2.hanu_hospital.record.Record;
import se2.hanu_hospital.record.RecordService;

import java.util.List;

@Service
public class BillService {
    @Autowired
    private final BillRepository billRepository;
    @Autowired
    private final RecordService recordService;

    public BillService(BillRepository billRepository, RecordService recordService) {
        this.billRepository = billRepository;
        this.recordService = recordService;
    }

    public Bill getBill(long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Department does not exist!"));
    }

    public void addBill(Long recordId) {
        Bill bill = new Bill();
        Record record = recordService.getRecordById(recordId);


        billRepository.save(bill);
    }

    public void deleteBill(long id) {
        if (billRepository.existsById(id)) {
            billRepository.deleteById(id);
        }
    }

    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }
}
