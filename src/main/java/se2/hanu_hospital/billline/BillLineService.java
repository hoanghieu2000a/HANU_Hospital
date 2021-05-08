package se2.hanu_hospital.billline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillLineService {

    private final BillLineRepository repository;

    public BillLineService(BillLineRepository repository) {
        this.repository = repository;
    }

    public BillLine getBillLine(long id) {
        Optional<BillLine> opt = repository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }

        return null;
    }

    public void addBillLine(BillLine billLine) {
        repository.save(billLine);
    }

    public List<BillLine> getAll() {
        return repository.findAll();
    }
}
