package com.example.api.core.bill;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill getBill(long id) {
        return billRepository.findById(id)
                .orElseThrow();
    }

    public void updateBill(long id, Bill bill) {
        if (billRepository.existsById(id)) {
            bill.setId(id);
            billRepository.save(bill);
        }
    }

    public void addBill(Bill bill) {
        bill.setId(null);
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
