package org.zerhusen.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.security.model.Motorcycle;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {

}
