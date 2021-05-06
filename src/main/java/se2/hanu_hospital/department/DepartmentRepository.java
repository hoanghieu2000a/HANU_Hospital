package se2.hanu_hospital.department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByNameContaining(String name);

    Department getDepartmentById(Long id);
}
