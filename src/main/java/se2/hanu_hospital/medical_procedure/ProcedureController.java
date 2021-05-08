package se2.hanu_hospital.medical_procedure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/services"})
@Tag(name = "Service Controller", description = "Service API")
public class ProcedureController {
    @Autowired
    private ProcedureService service;
    @Autowired
    private ProcedureRepository repo;

    @GetMapping("/all")
    @Operation(summary = "Find all services")
    public ResponseEntity<List<MedicalProcedure>> getAll() {
        try {
            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);

        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find service by id")
    public ResponseEntity<MedicalProcedure> getMedicalProcedureById(@PathVariable("id") long id) {
        MedicalProcedure medicalServiceData = service.getById(id);

        if (medicalServiceData != null) {
        return new ResponseEntity<>(medicalServiceData, HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Cretae a new medical procedure")
    public ResponseEntity<MedicalProcedure> createMedicalProcedure(@RequestBody MedicalProcedure medicalProcedure) {
        try {
        return new ResponseEntity<>(service.addProcedure(medicalProcedure), HttpStatus.CREATED);
        } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update medical procedure")
    public ResponseEntity<MedicalProcedure> updateMedicalProcedure(@PathVariable("id") long id, @Valid @RequestBody MedicalProcedurePayload medicalProcedurePayload) {
        MedicalProcedure data = service.getById(id);

        if (data != null) {
        return new ResponseEntity<>(service.updateById(id, medicalProcedurePayload), HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/addEquipment/{id}")
    public ResponseEntity<?> addEquipmentToProcedure(@PathVariable Long id, @RequestParam Long equipmentId){
        try {
            service.addEquipmentToProcedure(id, equipmentId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/removeEquipment/{id}")
    public ResponseEntity<?> removeEquipmentFromProcedure(@PathVariable Long id, @RequestParam Long equipmentId){
        try {
            service.removeEquipmentFromProcedure(id, equipmentId);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a medical procedure")
    public ResponseEntity<HttpStatus> deleteMedicalProcedure(@PathVariable("id") long id) {
        try {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    @Operation(summary = "Delete medical procedure (t test thử cái này, ô đừng dùng nhé)")
    public ResponseEntity<HttpStatus> deleteAllMedicalProcedure() {
        try {
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}

