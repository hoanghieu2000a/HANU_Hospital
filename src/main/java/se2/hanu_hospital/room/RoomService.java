package se2.hanu_hospital.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se2.hanu_hospital.patient.PatientRepository;
import se2.hanu_hospital.patient.entity.Patient;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private PatientRepository patientRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, PatientRepository patientRepository) {
        this.roomRepository = roomRepository;
        this.patientRepository = patientRepository;
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
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Medicine does not exist!"));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalStateException("Medicine does not exist!"));

        if(room.isFull()){
            throw new IllegalStateException("Room is full!");
        }

        patient.setRoom(room);
        patientRepository.save(patient);
    }

    public void removePatient(Long patientId){

    }

    public Room getRoomById(Long id){
        return roomRepository.findById(id).orElseThrow(() -> new IllegalStateException("Room does not exist!"));
    }

    public Room getRoomByRoomNo(int roomNo){
        return roomRepository.findRoomByRoomNo(roomNo);
    }

    public boolean isFull(Long id){
        Room room = roomRepository.findById(id).orElseThrow(() -> new IllegalStateException("Room does not exist!"));
        return room.getPatients().size() == room.getCapacity();
    }

    private boolean validateRoom(Room room){
        return room.getId() > 0 && room.getCapacity() > 0;
    }
}
