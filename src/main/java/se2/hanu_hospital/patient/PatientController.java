package se2.hanu_hospital.patient;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.patient.dto.CreatePatientDTO;
import se2.hanu_hospital.patient.dto.UpdatePatientDTO;
import se2.hanu_hospital.patient.entity.Patient;
//import vn.daoanhthanh.hanu_hospital_my_part.patient.dto.CreatePatientDTO;
//import vn.daoanhthanh.hanu_hospital_my_part.patient.dto.UpdatePatientDTO;
//import vn.daoanhthanh.hanu_hospital_my_part.patient.entity.Patient;

import javax.validation.Valid;
import java.util.List;


@RestController
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/add_patient")
    @Operation(summary = "Create a new patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
            @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
            @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Patient create(@Valid @RequestBody CreatePatientDTO createPatientDTO){
        return patientService.create(createPatientDTO);
    }


    @Operation(summary = "Get patients by keyword")
    @GetMapping(value = "/patients/all")
    public List<Patient> findAll(@RequestParam(required = false) String keyword) {
        if (keyword != null)
            return patientService.findByKeyword(keyword);
        else
            return patientService.findAll();
    }

    @Operation(summary = "Get patients by id")
    @GetMapping(value = "/patients/{id}")
    public Patient getByID(@PathVariable Long id) {
        return patientService.getById(id);
    }

    @Operation(summary = "Update a patient by ID")
    @PutMapping(value = "/patients/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
            @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
            @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Patient updateByID(@PathVariable Long id, @Valid @RequestBody UpdatePatientDTO updatePatientDTO) {
        return patientService.updateById(id, updatePatientDTO);
    }

    @Operation(summary = "Delete a patient by ID")
    @DeleteMapping(value = "/patients/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public void deleteByID(@PathVariable Long id) {
        patientService.deleteById(id);
    }

    @Operation(summary = "Check if a Patient's phone number has not been added before in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Something went wrong"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "The username is not valid"),
            @ApiResponse(responseCode = "500", description = "Expired or invalid JWT token") })
    @GetMapping(value = "/patients/checkUniqueness/phoneNumber/{phoneNumber}")
    public boolean isPhoneNumberUnique(@PathVariable String phoneNumber) {
        return patientService.isPhoneNumberUnique(phoneNumber);
    }

}
