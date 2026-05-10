package cz.cvut.nss.catalogservice.service;

import cz.cvut.nss.catalogservice.dto.CreateMenuItemRequest;
import cz.cvut.nss.catalogservice.entity.MenuItem;
import cz.cvut.nss.catalogservice.entity.Restaurant;
import cz.cvut.nss.catalogservice.repository.MenuItemRepository;
import cz.cvut.nss.catalogservice.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public List<MenuItem> getMenuByRestaurant(String restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public MenuItem createMenuItem(String restaurantId, CreateMenuItemRequest request) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuItem menuItem = MenuItem.builder()
                .name(request.name())
                .category(request.category())
                .price(request.price())
                .available(request.available())
                .restaurant(restaurant)
                .build();

        return menuItemRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(String id, CreateMenuItemRequest request) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        menuItem.setName(request.name());
        menuItem.setCategory(request.category());
        menuItem.setPrice(request.price());
        menuItem.setAvailable(request.available());

        return menuItemRepository.save(menuItem);
    }

    public void deleteMenuItem(String id) {
        menuItemRepository.deleteById(id);
    }

    public List<MenuItem> searchMenuItems(String query) {
        return menuItemRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(query, query);
    }
}