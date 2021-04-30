package com.example.api.core.billline;

import com.example.api.exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillLineService {

    private final BillLineRepository repository;

    public BillLine getBillLine(long id) {
        Optional<BillLine> opt = repository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ResourceNotFound();
    }

    public void updateBillLine(long id, BillLine billLine) {
        if (repository.existsById(id)) {
            billLine.setId(id);
            repository.save(billLine);
        } else {
            throw new ResourceNotFound();
        }
    }

    public void addBillLine(BillLine billLine) {
        billLine.setId(null);
        repository.save(billLine);
    }

    public void deleteBillLine(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFound();
        }
    }

    public List<BillLine> getAll() {
        return repository.findAll();
    }
}
