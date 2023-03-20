package com.dining.reviewapi.repositories;

import com.dining.reviewapi.models.Author;
import com.dining.reviewapi.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAuthor(Author author);

    List<Review> findByRestaurantId(Integer id);
}
