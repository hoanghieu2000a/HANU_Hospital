package se2.hanu_hospital.room;

import se2.hanu_hospital.patient.entity.Patient;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(4) UNSIGNED ", precision = 4, updatable = false)
    private Long id;

    private int roomNo;

    @OneToMany(mappedBy = "room")
    private Set<Patient> patients = new HashSet<>();

    private int capacity;

    private boolean isFull;

    public Room(Long id, int roomNo, Set<Patient> patients, int capacity) {
        this.id = id;
        this.patients = patients;
        this.capacity = capacity;
        this.roomNo = roomNo;
    }

    public Room(Long id, int roomNo, int capacity) {
        this.id = id;
        this.roomNo = roomNo;
        this.capacity = capacity;
    }

    public Room() {
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        isFull = patients.size() == capacity;

        return isFull;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
