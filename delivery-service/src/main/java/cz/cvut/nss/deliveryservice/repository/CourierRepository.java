package cz.cvut.nss.deliveryservice.repository;

import cz.cvut.nss.deliveryservice.entity.Courier;
import cz.cvut.nss.deliveryservice.entity.CourierStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierRepository extends JpaRepository<Courier, String> {

    // Implementation of the Strategy pattern: finding the first available courier
    Optional<Courier> findFirstByStatus(CourierStatus status);
}