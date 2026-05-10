package cz.cvut.nss.catalogservice.controller;

import cz.cvut.nss.catalogservice.dto.CreateMenuItemRequest;
import cz.cvut.nss.catalogservice.entity.MenuItem;
import cz.cvut.nss.catalogservice.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService menuItemService;

    @GetMapping("/restaurants/{restaurantId}/menu")
    public List<MenuItem> getMenuByRestaurant(@PathVariable String restaurantId) {
        return menuItemService.getMenuByRestaurant(restaurantId);
    }

    @PostMapping("/restaurants/{restaurantId}/menu")
    public MenuItem createMenuItem(
            @PathVariable String restaurantId,
            @Valid @RequestBody CreateMenuItemRequest request
    ) {
        return menuItemService.createMenuItem(restaurantId, request);
    }

    @PutMapping("/menu-items/{id}")
    public MenuItem updateMenuItem(
            @PathVariable String id,
            @Valid @RequestBody CreateMenuItemRequest request
    ) {
        return menuItemService.updateMenuItem(id, request);
    }

    @DeleteMapping("/menu-items/{id}")
    public void deleteMenuItem(@PathVariable String id) {
        menuItemService.deleteMenuItem(id);
    }

    @GetMapping("/menu-items/search")
    public List<MenuItem> searchMenuItems(@RequestParam String query) {
        return menuItemService.searchMenuItems(query);
    }
}