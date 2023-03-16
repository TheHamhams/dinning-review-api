package com.dining.reviewapi.controllers;

import com.dining.reviewapi.models.Restaurant;
import com.dining.reviewapi.repositories.RestaurantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

//    GET
    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants =  this.restaurantRepository.findAll();

        return ResponseEntity.ok(restaurants);
    }

//    POST
    @PostMapping("/create")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant request) {
        Restaurant restaurant = this.restaurantRepository.save(request);

        return ResponseEntity.ok(restaurant);
    }

//    PUT
    @PutMapping("/update")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant restaurant) {
        Optional<Restaurant> restaurantOptional = this.restaurantRepository.findById(restaurant.getId());
        if (restaurantOptional.isEmpty()) {
            return null;
        }

        Restaurant updatedRestaurant = restaurantOptional.get();

        if (restaurant.getName() != null) {
            updatedRestaurant.setName(restaurant.getName());
        }

        if (restaurant.getCity() != null) {
            updatedRestaurant.setCity(restaurant.getCity());
        }

        if (restaurant.getState() != null) {
            updatedRestaurant.setState(restaurant.getState());
        }

        if (restaurant.getZipcode() != null) {
            updatedRestaurant.setZipcode(restaurant.getZipcode());
        }

        if (restaurant.getType() != null) {
            updatedRestaurant.setType(restaurant.getType());
        }

        return ResponseEntity.ok(this.restaurantRepository.save(updatedRestaurant));
    }

//    DELETE

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("id") Integer id) {
        Optional<Restaurant> restaurantOptional = this.restaurantRepository.findById(id);
        if (restaurantOptional.isEmpty()) {
            return null;
        }

        Restaurant restaurant = restaurantOptional.get();
        this.restaurantRepository.delete(restaurant);

        return ResponseEntity.ok("Restaurant Deleted");
    }
}
