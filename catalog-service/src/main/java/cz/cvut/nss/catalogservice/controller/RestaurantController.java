package cz.cvut.nss.catalogservice.controller;

import cz.cvut.nss.catalogservice.dto.CreateRestaurantRequest;
import cz.cvut.nss.catalogservice.entity.Restaurant;
import cz.cvut.nss.catalogservice.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PostMapping
    public Restaurant createRestaurant(@Valid @RequestBody CreateRestaurantRequest request) {
        return restaurantService.createRestaurant(request);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable String id) {
        return restaurantService.getRestaurantById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurant(id);
    }

    @GetMapping("/search")
    public List<Restaurant> searchRestaurants(@RequestParam String query) {
        return restaurantService.searchRestaurants(query);
    }
}