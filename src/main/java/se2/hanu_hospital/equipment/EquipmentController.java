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
@RequestMapping(path = {"/equipment"})
@Tag(name = "Equipments Controller")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping("/add_equipment")
    @Operation(summary = "Create a new equipment")
    public Equipment create(@Valid @RequestBody Equipment equipment){
        return equipmentService.create(equipment);
    }

    @Operation(summary = "Find equipment by keyword")
    @GetMapping("/getEquipments")
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

    @Operation(summary = "Find equipment by id")
    @GetMapping("/equipment/{id}")
    public ResponseEntity<Equipment> findById(@PathVariable("id") Long id){
        Equipment equipmentData = equipmentService.getById(id);
        if(equipmentData != null){
            return new ResponseEntity<>(equipmentData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update equipment")
    @PutMapping("/equipment/{id}")
    public Equipment updateById(@PathVariable Long id, @Valid @RequestBody EquipmentPayload equipmentPayload){
        return equipmentService.updateById(id, equipmentPayload);
    }

    @Operation(summary = "Delete a equipment by ID")
    @DeleteMapping(value = "/equipment/{id}")
    public void deleteByID(@PathVariable Long id) {
        equipmentService.deleteById(id);
    }
}
