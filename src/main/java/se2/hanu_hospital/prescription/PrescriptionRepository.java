package se2.hanu_hospital.prescription;

import org.springframework.data.jpa.repository.JpaRepository;
import se2.hanu_hospital.record.Record;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    void deletePrescriptionsByRecord(Record record);
}
