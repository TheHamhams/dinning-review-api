package com.dining.reviewapi.repositories;

import com.dining.reviewapi.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByAdminName(String adminName);
}
