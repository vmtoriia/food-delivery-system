package cz.cvut.nss.catalogservice.service;

import cz.cvut.nss.catalogservice.dto.CreateRestaurantRequest;
import cz.cvut.nss.catalogservice.entity.Restaurant;
import cz.cvut.nss.catalogservice.repository.RestaurantRepository;
import org.springframework.cache.annotation.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Cacheable(value = "restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant createRestaurant(CreateRestaurantRequest request) {
        Restaurant restaurant = Restaurant.builder()
                .name(request.name())
                .category(request.category())
                .rating(request.rating())
                .build();

        return restaurantRepository.save(restaurant);
    }

    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    public void deleteRestaurant(String id) {
        restaurantRepository.deleteById(id);
    }

    public List<Restaurant> searchRestaurants(String query) {
        return restaurantRepository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(query, query);
    }
}