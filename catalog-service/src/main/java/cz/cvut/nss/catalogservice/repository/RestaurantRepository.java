package cz.cvut.nss.catalogservice.repository;

import cz.cvut.nss.catalogservice.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {

        List<Restaurant> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            String name,
            String category
    );
}