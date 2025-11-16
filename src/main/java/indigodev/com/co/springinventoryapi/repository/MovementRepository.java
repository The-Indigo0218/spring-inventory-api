package indigodev.com.co.springinventoryapi.repository;

import indigodev.com.co.springinventoryapi.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
}
