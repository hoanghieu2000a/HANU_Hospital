package se2.hanu_hospital.patient;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/patient"})
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/add_patient")
    @Operation(summary = "Create a new patient")
    public Patient create(@Valid @RequestBody Patient patient){
        return patientService.create(patient);
    }


    @Operation(summary = "Get patients by keyword")
    @GetMapping(value = "/all")
    public List<Patient> findAll(@RequestParam(required = false) String keyword) {
        if (keyword != null)
            return patientService.findByKeyword(keyword);
        else
            return patientService.findAll();
    }

    @Operation(summary = "Get patients by id")
    @GetMapping(value = "/{id}")
    public Patient getByID(@PathVariable Long id) {
        return patientService.getById(id);
    }

    @Operation(summary = "Update a patient by ID")
    @PutMapping(value = "/{id}")
    public Patient updateByID(@PathVariable Long id, @Valid @RequestBody PatientPayload patientPayload) {
        return patientService.updateById(id, patientPayload);
    }

    @Operation(summary = "Delete a patient by ID")
    @DeleteMapping(value = "/{id}")
    public void deleteByID(@PathVariable Long id) {
        patientService.deleteById(id);
    }
}
