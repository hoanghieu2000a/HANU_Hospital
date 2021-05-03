package se2.hanu_hospital.record;

import org.springframework.data.jpa.repository.JpaRepository;
import se2.hanu_hospital.medicine.Medicine;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findRecordByPatientId(Long id);

    List<Record> findRecordByDoctorId(Long id);
}
