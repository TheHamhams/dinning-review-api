package com.dining.reviewapi.controllers;

import com.dining.reviewapi.dtos.ReviewDto;
import com.dining.reviewapi.exceptions.AuthorUsernameNotFoundException;
import com.dining.reviewapi.exceptions.RestaurantIdNotFoundException;
import com.dining.reviewapi.exceptions.ReviewIdNotFoundException;
import com.dining.reviewapi.models.Author;
import com.dining.reviewapi.models.Restaurant;
import com.dining.reviewapi.models.Review;
import com.dining.reviewapi.repositories.AuthorRepository;
import com.dining.reviewapi.repositories.RestaurantRepository;
import com.dining.reviewapi.repositories.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final AuthorRepository authorRepository;
    private final RestaurantRepository restaurantRepository;

    public ReviewController(ReviewRepository reviewRepository, AuthorRepository authorRepository,
        RestaurantRepository restaurantRepository) {

        this.reviewRepository = reviewRepository;
        this.authorRepository = authorRepository;
        this.restaurantRepository = restaurantRepository;
    }

//    GET
    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(this.reviewRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("id") Integer id) {
        Review review = this.reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewIdNotFoundException("Review not found"));

        return ResponseEntity.ok(review);
    }

    @GetMapping("/author/{username}")
    public ResponseEntity<List<Review>> getReviewByAuthor(@PathVariable("username") String username) {
        Author author = this.authorRepository.findByUsername(username)
                .orElseThrow(() -> new AuthorUsernameNotFoundException("Username not found"));

        List<Review> reviews = this.reviewRepository.findByAuthor(author);

        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Review>> getReviewsByRestaurant(@PathVariable("id") Integer id) {
        Restaurant restaurant = this.restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantIdNotFoundException("Restaurant not found"));

        List<Review> reviews = this.reviewRepository.findByRestaurantId(id);

        return ResponseEntity.ok(reviews);
    }

//    POST
    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto request) {
        Author author = this.authorRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthorUsernameNotFoundException("Username not found"));

        Restaurant restaurant = this.restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RestaurantIdNotFoundException("Restaurant not found"));

        Review review = new Review();
        review.setRating(request.getRating());
        review.setPeanuts(request.getPeanuts());
        review.setEgg(request.getEgg());
        review.setDairy(request.getDairy());
        review.setTitle(request.getTitle());
        review.setContent(request.getContent());
        review.setReviewDate(new Date(System.currentTimeMillis()));
        review.setStatus("pending");
        review.setRestaurant(restaurant);
        review.setAuthor(author);

        Review response = this.reviewRepository.save(review);

        return ResponseEntity.ok(response);
    }

//    DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") Integer id) {
        Review review = this.reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewIdNotFoundException("Review not found"));

        this.reviewRepository.delete(review);

        return ResponseEntity.ok("Review deleted");
    }

}
