package se2.hanu_hospital.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se2.hanu_hospital.patient.entity.Patient;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = {"/api/room"})
public class RoomController {
    private RoomService roomService;
    
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAllRoom (){
        try {
            return new ResponseEntity<>(roomService.getAllRoom(), HttpStatus.OK);

        } catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<?> addRoom(@RequestBody Room record){
        try {
            roomService.addRoom(record);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = {"/delete/{id}"})
    public ResponseEntity<?> deleteRoom(@PathVariable("id") Long id){
        try{
            roomService.deleteRoom(id);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = {"/update"})
    public ResponseEntity<?> updateRoom (@RequestBody Room room){
        try {
            roomService.updateRoom(room);
            return new ResponseEntity<>("ok", HttpStatus.OK);

        } catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = {"/isFull/{id}"})
    public ResponseEntity<?> isFull(@PathVariable long id){
            try{
                return new ResponseEntity<>(roomService.isFull(id), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = {"/getRoomById/{id}"})
    public ResponseEntity<?> getRoomById(@PathVariable long id){
        try{

            return new ResponseEntity<>(roomService.getRoomById(id), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = {"/getRoomByName/{name}"})
    public ResponseEntity<?> getRoomByName(@PathVariable int name){
        try{

            return new ResponseEntity<>(roomService.getRoomByName(name), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = {"/addPatient/{id}"})
    public ResponseEntity<?> addPatient(@PathVariable Long id, @RequestParam Long patientId){
        try{
            roomService.addPatient(id, patientId);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = {"/removePatient/{id}"})
    public ResponseEntity<?> removePatient(@PathVariable Long id, @RequestParam Long patientId){
        try{
            roomService.removePatient(id, patientId);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
