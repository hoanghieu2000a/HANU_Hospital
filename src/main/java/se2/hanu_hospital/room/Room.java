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

    private int name;

    @OneToMany(mappedBy = "room")
    private Set<Patient> patients = new HashSet<>();

    private int capacity;

    public Room(Long id, Set<Patient> patients, int capacity, int name) {
        this.id = id;
        this.patients = patients;
        this.capacity = capacity;
        this.name = name;
    }

    public Room() {
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
