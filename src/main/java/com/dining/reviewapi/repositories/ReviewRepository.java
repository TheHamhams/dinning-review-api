package com.dining.reviewapi.repositories;

import com.dining.reviewapi.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
