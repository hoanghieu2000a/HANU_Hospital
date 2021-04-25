package se2.hanu_hospital.Prescription;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public void add(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    public List<Prescription> getAll(){
        return prescriptionRepository.findAll();
    }

    public void delete (Long id){
        if(!prescriptionRepository.existsById(id)){
            throw new IllegalStateException("Prescription does not Exist");
        }
        prescriptionRepository.deleteById(id);
    }

    public void update (Prescription prescription){
        if(!prescriptionRepository.existsById(prescription.getId())){
            throw new IllegalStateException("Prescription does not Exist");
        }
        prescriptionRepository.save(prescription);
    }

    public Prescription getById(Long id){
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Medicine does not exist!"));
        return prescription;
    }
}
