package se2.hanu_hospital.repository.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import se2.hanu_hospital.model.patient.Patient;


public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}

