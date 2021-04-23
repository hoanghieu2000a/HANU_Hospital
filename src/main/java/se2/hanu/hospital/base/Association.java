package se2.hanu.hospital.base;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Association<E, T extends JpaRepository<?, ?>> {

}
