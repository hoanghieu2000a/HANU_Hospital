package com.example.api.core.patient;

import com.example.api.core.bill.Bill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String gender;

    private LocalDate dob;

    private String phoneNumber;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    @OneToOne(mappedBy = "patient")
    private Bill bill;
}
