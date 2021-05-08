package se2.hanu_hospital.facility;

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
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @PostMapping("/add_facility")
    @Operation(summary = "Create a new facility")
    public Facility create(@Valid @RequestBody Facility facility){
        return facilityService.create(facility);
    }

    @Operation(summary = "Find facility facility by keyword")
    @GetMapping("/getFacilities")
    public ResponseEntity<List<Facility>> findAll(@RequestParam(required = false) String code){
        try {
            List<Facility> facilities = new ArrayList<Facility>();
      
            if (code == null)
                facilities.addAll(facilityService.findAll());
            else
                facilities.addAll(facilityService.findByName(code));
      
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
    public ResponseEntity<Facility> findById(@PathVariable("id") Long id){
        Facility facilityData = facilityService.getById(id);
        if(facilityData != null){
            return new ResponseEntity<>(facilityData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update facility")
    @PutMapping("/facility/{id}")
    public Facility updateById(@PathVariable Long id, @Valid @RequestBody FacilityPayload facilityPayload){
        return facilityService.updateById(id, facilityPayload);
    }

    @Operation(summary = "Delete a facility by ID")
    @DeleteMapping(value = "/facility/{id}")
    public void deleteByID(@PathVariable Long id) {
        facilityService.deleteById(id);
    }
}
