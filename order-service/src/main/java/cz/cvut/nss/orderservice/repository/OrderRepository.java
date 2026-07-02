package cz.cvut.nss.orderservice.repository;

import cz.cvut.nss.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}