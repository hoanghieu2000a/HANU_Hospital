package se2.hanu_hospital.facility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.facility.dto.CreateFacilityDTO;
import se2.hanu_hospital.facility.dto.UpdateFacilityDTO;
import se2.hanu_hospital.facility.entity.Facility;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@Tag(name = "Facilities Controller")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;




    @PostMapping("/add_facility")
    @Operation(summary = "Create a new facility")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
            @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
            @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Facility create(@Valid @RequestBody CreateFacilityDTO createFacilityDTO){
        return facilityService.create(createFacilityDTO);
    }

    @Operation(summary = "Find facility facility by keyword")
    @GetMapping("/facilities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
            @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
            @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public ResponseEntity<List<Facility>> findAll(@RequestParam(required = false) String code){
        try {
            List<Facility> facilities = new ArrayList<Facility>();

            if (code == null)
                facilityService.findAll().forEach(facilities::add);
            else
                facilityService.findByCode(code).forEach(facilities::add);

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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
            @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
            @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public ResponseEntity<Facility> findById(@PathVariable("id") Long id){
        Facility facilityData = facilityService.getById(id);
        if(facilityData != null){
            return new ResponseEntity<>(facilityData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update facility")
    @PutMapping("/facility/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
            @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
            @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Facility updateById(@PathVariable Long id, @Valid @RequestBody UpdateFacilityDTO updateFacilityDTO){
        return facilityService.updateById(id, updateFacilityDTO);
    }

    @Operation(summary = "Delete a facility by ID")
    @DeleteMapping(value = "/facility/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
            @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
            @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public void deleteByID(@PathVariable Long id) {
        facilityService.deleteById(id);
    }





}
