package com.dining.reviewapi.repositories;

import com.dining.reviewapi.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
