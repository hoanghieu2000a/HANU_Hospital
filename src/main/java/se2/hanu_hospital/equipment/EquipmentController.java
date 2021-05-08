package se2.hanu_hospital.equipment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/facility"})
@Tag(name = "Facilities Controller")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping("/add_facility")
    @Operation(summary = "Create a new facility")
    public Equipment create(@Valid @RequestBody Equipment equipment){
        return equipmentService.create(equipment);
    }

    @Operation(summary = "Find facility facility by keyword")
    @GetMapping("/getFacilities")
    public ResponseEntity<List<Equipment>> findAll(@RequestParam(required = false) String code){
        try {
            List<Equipment> facilities = new ArrayList<Equipment>();
      
            if (code == null)
                facilities.addAll(equipmentService.findAll());
            else
                facilities.addAll(equipmentService.findByName(code));
      
            if (facilities.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
      
            return new ResponseEntity<>(facilities, HttpStatus.OK);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    } 

    @Operation(summary = "Find facility by id")
    @GetMapping("/facility/{id}")
    public ResponseEntity<Equipment> findById(@PathVariable("id") Long id){
        Equipment equipmentData = equipmentService.getById(id);
        if(equipmentData != null){
            return new ResponseEntity<>(equipmentData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update facility")
    @PutMapping("/facility/{id}")
    public Equipment updateById(@PathVariable Long id, @Valid @RequestBody EquipmentPayload equipmentPayload){
        return equipmentService.updateById(id, equipmentPayload);
    }

    @Operation(summary = "Delete a facility by ID")
    @DeleteMapping(value = "/facility/{id}")
    public void deleteByID(@PathVariable Long id) {
        equipmentService.deleteById(id);
    }
}
