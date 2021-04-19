package se2.hanu_hospital.repository.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import se2.hanu_hospital.model.patient.PatientDetails;


public interface PatientDetailsRepository extends JpaRepository<PatientDetails,Long> {
    
}
