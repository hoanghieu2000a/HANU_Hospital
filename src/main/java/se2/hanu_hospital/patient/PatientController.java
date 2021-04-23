package se2.hanu_hospital.domain.patient;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se2.hanu.hospital.domain.patient.dto.CreatePatientDTO;
import se2.hanu.hospital.domain.patient.entity.Patient;

import javax.validation.Valid;


@RestController
@Tag(name = "Patient Controller", description = "The Patient API")
public class PatientController {
    @Autowired
    private PatientService patientService;

    public Patient create(@Valid @RequestBody CreatePatientDTO createPatientDTO){
        return patientService.create(createPatientDTO);
    }




}
