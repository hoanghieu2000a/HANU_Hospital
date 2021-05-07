package se2.hanu_hospital.consumable;

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
@RequestMapping(path = {"/item"})
@Tag(name = "Consumable Items Controller", description = "Consumable Items API")
public class ConsumableController {
    private final ConsumableService consumableService;

    @Autowired
    public ConsumableController(ConsumableService consumableService){
        this.consumableService = consumableService;
    }

    @PostMapping("/add_item")
    @Operation(summary = "Create a new consumable item")
    public Consumable create(@Valid @RequestBody Consumable consumable){
        return consumableService.create(consumable);
    }

    @Operation(summary = "Find consumable item by keyword")
    @GetMapping("/getItems")
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
    @GetMapping("/{id}")
    public ResponseEntity<Consumable> findById(@PathVariable("id") Long id){
        Consumable itemData = consumableService.getById(id);
        if(itemData != null){
            return new ResponseEntity<>(itemData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Update item")
    @PutMapping("/{id}")
    public Consumable updateById(@PathVariable Long id, @Valid @RequestBody ConsumablePayload consumablePayload){
        return consumableService.updateById(id, consumablePayload);
    }

    @Operation(summary = "Delete a item by ID")
    @DeleteMapping(value = "/{id}")
    public void deleteByID(@PathVariable Long id) {
        consumableService.deleteById(id);
    }
}
