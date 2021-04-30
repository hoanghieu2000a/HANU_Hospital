package com.example.api.core.bill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BillRepositoryTest {

    @Autowired
    private BillRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void it_should_insert_bill() {
        // Arrange
        LocalDate now = LocalDate.now();

        Bill bill = new Bill();
        bill.setPatient(null);
        bill.setTotalPrice(150f);
        bill.setCreateDate(now);

        repository.save(bill);

        // Act
        Bill result = repository.findById(bill.getId())
                .orElseThrow();

        // Assert
        assertEquals(150f, result.getTotalPrice());
        assertEquals(now, result.getCreateDate());
    }
}