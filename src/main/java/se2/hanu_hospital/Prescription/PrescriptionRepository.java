package se2.hanu_hospital.Prescription;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findAllByNameContaining(String name);


}
