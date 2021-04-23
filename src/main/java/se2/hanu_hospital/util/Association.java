package se2.hanu_hospital.base;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Association<E, T extends JpaRepository<?, ?>> {

}
