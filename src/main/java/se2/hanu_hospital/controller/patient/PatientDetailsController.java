package se2.hanu_hospital.controller.patient;

import org.springframework.web.bind.annotation.RestController;

import se2.hanu_hospital.error.patient.PatientDetailsNotFoundException;
import se2.hanu_hospital.model.patient.Patient;
import se2.hanu_hospital.model.patient.PatientDetails;
import se2.hanu_hospital.payload.PatientDetailsRequestBuilder;
import se2.hanu_hospital.repository.patient.PatientDetailsRepository;
import se2.hanu_hospital.repository.patient.PatientRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/hanu_hospital")
class PatientDetailsController {

  @Autowired
  private PatientDetailsRepository repository;
  @Autowired
  private PatientRepository repository2;
  

 

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/patientDetails")
  List<PatientDetails> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/patientDetails")
  PatientDetails newPatientDetails(@RequestBody PatientDetailsRequestBuilder newPatientDetailsRequest) {
    PatientDetails patientDetails = new PatientDetails();
    Optional<Patient> p = repository2.findById(newPatientDetailsRequest.getPatientId());
    if(!p.isPresent()){
      return null;
    }

    patientDetails.setBloodtype(newPatientDetailsRequest.getBloodtype());
    patientDetails.setHeigth(newPatientDetailsRequest.getHeigth());
    patientDetails.setWeigth(newPatientDetailsRequest.getWeigth());
    patientDetails.setPatient(p.get());
    return repository.save(patientDetails );
  }

  // Single item
  @GetMapping("/patientDetails/{id}")
  PatientDetails one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new PatientDetailsNotFoundException(id));
  }

  @PutMapping("/patientDetails/{id}")
  PatientDetails replacePatientDetails(@RequestBody PatientDetails newPatientDetails, @PathVariable Long id) {
  
    return repository.findById(id)
      .map(PatientDetails -> {

        //TODO
        PatientDetails.setBloodtype(newPatientDetails.getBloodtype());
        PatientDetails.setHeigth(newPatientDetails.getHeigth());
        PatientDetails.setWeigth(newPatientDetails.getWeigth());
        return repository.save(PatientDetails);
      })
      .orElseGet(() -> {
        newPatientDetails.setId(id);
        return repository.save(newPatientDetails);
      });
  }

  @DeleteMapping("/patientDetails/{id}")
  void deletePatientDetails(@PathVariable Long id) {
    repository.deleteById(id);
  }
}