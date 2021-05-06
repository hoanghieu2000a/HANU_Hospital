package se2.hanu_hospital.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se2.hanu_hospital.patient.PatientService;
import se2.hanu_hospital.patient.entity.Patient;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRoom(){
        return roomRepository.findAll();
    }

    public void addRoom(Room room){
        if(validateRoom(room)){
        roomRepository.save(room);
        } else {
            throw new IllegalStateException("invalid");
        }
    }

    public void deleteRoom(Long id){
        Room room = roomRepository.findById(id).orElseThrow(() -> new IllegalStateException("Room does not exist!"));
        roomRepository.delete(room);
    }

    public void updateRoom(Room room){
        if(!roomRepository.existsById(room.getId()) && !validateRoom(room)){
            throw new IllegalStateException("Room does not exist!");
        }
        roomRepository.save(room);
    }


    public void addPatient(Long id ,Long  patientId){

    }

    public void removePatient(Long id ,Long patientId){

    }

    public Room getRoomById(Long id){
        return roomRepository.findById(id).orElseThrow(() -> new IllegalStateException("Room does not exist!"));
    }

    public Room getRoomByName(int name){
        return roomRepository.findRoomByName(name);
    }

    public boolean isFull(Long id){
        Room room = roomRepository.findById(id).orElseThrow(() -> new IllegalStateException("Room does not exist!"));
        return room.getPatients().size() == room.getCapacity();
    }

    private boolean validateRoom(Room room){
        return room.getId() > 0 && room.getCapacity() > 0;
    }
}
