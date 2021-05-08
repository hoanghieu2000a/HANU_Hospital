package se2.hanu_hospital.bill;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
    Bill getDepartmentById(long id);
}
