package se2.hanu_hospital.consumable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.consumable.dto.CreateConsumableDTO;
import se2.hanu_hospital.consumable.dto.UpdateConsumableDTO;
import se2.hanu_hospital.consumable.entity.Consumable;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@Tag(name = "Consumable Items Controller", description = "Consumable Items API")
public class ConsumableController {
    private final ConsumableService consumableService;

    @Autowired
    public ConsumableController(ConsumableService consumableService){
        this.consumableService = consumableService;
    }

    @PostMapping("/add_item")
    @Operation(summary = "Create a new consumable item")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Consumable create(@Valid @RequestBody CreateConsumableDTO createConsumableDTO){
        return consumableService.create(createConsumableDTO);
    }

    @Operation(summary = "Find consumable item by keyword")
    @GetMapping("/items")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public ResponseEntity<List<Consumable>> findAll(@RequestParam(required = false) String keyword){
        try {
            List<Consumable> items = new ArrayList<Consumable>();
      
            if (keyword == null)
              consumableService.findAll().forEach(items::add);
            else
              consumableService.findAllByName(keyword).forEach(items::add);
      
            if (items.isEmpty()) {
              return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
      
            return new ResponseEntity<>(items, HttpStatus.OK);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    } 

    @Operation(summary = "Find item by id")
    @GetMapping("/item/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public ResponseEntity<Consumable> findById(@PathVariable("id") Long id){
        Consumable itemData = consumableService.getById(id);
        if(itemData != null){
            return new ResponseEntity<>(itemData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update item")
    @PutMapping("/item/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
            @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
            @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public Consumable updateById(@PathVariable Long id, @Valid @RequestBody UpdateConsumableDTO updateConsumableDTO){
        return consumableService.updateById(id, updateConsumableDTO);
    }

    @Operation(summary = "Delete a item by ID")
    @DeleteMapping(value = "/item/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Missing Request Parameter"),
        @ApiResponse(responseCode = "422", description = "Input validation(s) failed"),
        @ApiResponse(responseCode = "409", description = "Field value(s) already exists")
    })
    public void deleteByID(@PathVariable Long id) {
        consumableService.deleteById(id);
    }
}
