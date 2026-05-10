package cz.cvut.nss.catalogservice.repository;

import cz.cvut.nss.catalogservice.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, String> {

    List<MenuItem> findByRestaurantId(String restaurantId);

    List<MenuItem> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            String name,
            String category
    );
}