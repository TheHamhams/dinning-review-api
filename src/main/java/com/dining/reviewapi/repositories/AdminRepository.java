package com.dining.reviewapi.repositories;

import com.dining.reviewapi.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
