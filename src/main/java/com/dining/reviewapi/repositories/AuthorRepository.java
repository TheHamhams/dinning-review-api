package com.dining.reviewapi.repositories;

import com.dining.reviewapi.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByUsername(String username);
}
