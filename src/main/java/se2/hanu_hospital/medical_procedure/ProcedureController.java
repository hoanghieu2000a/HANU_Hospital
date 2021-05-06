package se2.hanu_hospital.domain.medical_procedure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.domain.medical_procedure.dto.CreateProcedureDTO;
import se2.hanu_hospital.domain.medical_procedure.dto.UpdateProcedureDTO;
import se2.hanu_hospital.domain.medical_procedure.entity.MedicalProcedure;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
public class ProcedureController {
    @Autowired
    private ProcedureServiceImpl service;
    @Autowired
    private ProcedureRepository repo;

    @GetMapping("/services")
    @Operation(summary = "Return existed medical procedures")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public ResponseEntity<List<MedicalProcedure>> getAll(@RequestParam(required = false) String patientName) {
        try {
        List<MedicalProcedure> services = new ArrayList<MedicalProcedure>();

        if (patientName == null)
            service.findAll().forEach(services::add);
        else
            service.findByPatientName(patientName).forEach(services::add);

        if (services.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/services/{id}")
    @Operation(summary = "Find Medical procedure by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public ResponseEntity<MedicalProcedure> getMedicalProcedureById(@PathVariable("id") long id) {
        MedicalProcedure medicalServiceData = service.getById(id);

        if (medicalServiceData != null) {
        return new ResponseEntity<>(medicalServiceData, HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/services")
    @Operation(summary = "Cretae a new medical procedure")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public ResponseEntity<MedicalProcedure> createMedicalProcedure(@RequestBody CreateProcedureDTO createProcedureDTO) {
        try {

        return new ResponseEntity<>(service.create(createProcedureDTO), HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/services/{id}")
    @Operation(summary = "Update medical procedure")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public ResponseEntity<MedicalProcedure> updateMedicalProcedure(@PathVariable("id") long id, @Valid @RequestBody UpdateProcedureDTO updateProcedureDTO) {
        MedicalProcedure data = service.getById(id);

        if (data != null) {
        return new ResponseEntity<>(service.updateById(id, updateProcedureDTO), HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/services/{id}")
    @Operation(summary = "Delete a medical procedure")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public ResponseEntity<HttpStatus> deleteMedicalProcedure(@PathVariable("id") long id) {
        try {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/services")
    @Operation(summary = "Delete medical procedure (t test thử cái này, ô đừng dùng nhé)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public ResponseEntity<HttpStatus> deleteAllMedicalProcedure() {
        try {
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

