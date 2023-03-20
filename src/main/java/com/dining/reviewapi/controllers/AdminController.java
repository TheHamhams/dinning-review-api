package com.dining.reviewapi.controllers;

import com.dining.reviewapi.exceptions.*;
import com.dining.reviewapi.models.Admin;
import com.dining.reviewapi.models.Review;
import com.dining.reviewapi.repositories.AdminRepository;
import com.dining.reviewapi.repositories.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminRepository adminRepository;
    private final ReviewRepository reviewRepository;

    public AdminController(AdminRepository adminRepository, ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        this.adminRepository = adminRepository;
    }

//    GET
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Review>> getAllStatus(@PathVariable("status") String status) {
        List<Review> reviews = this.reviewRepository.findByStatus(status);

        return ResponseEntity.ok(reviews);
    }

    @GetMapping()
    public ResponseEntity<List<Admin>> getAllAdmin() {
        return ResponseEntity.ok(this.adminRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") Integer id) {
        Admin admin = this.adminRepository.findById(id)
                .orElseThrow(() -> new AdminIdNotFoundException("Admin not found"));

        return ResponseEntity.ok(admin);
    }

    @GetMapping("/username/{adminName}")
    public ResponseEntity<Admin> getAdminByUsername(@PathVariable("adminName") String adminName) {
        Admin admin = this.adminRepository.findByAdminName(adminName)
                .orElseThrow(() -> new AdminNameNotFoundException("Admin not found"));

        return ResponseEntity.ok(admin);
    }

//    POST
    @PostMapping("status/{adminId}/{reviewId}/{approval}")
    public ResponseEntity<String> adminReview(@PathVariable("reviewId") Integer reviewId,
                                             @PathVariable("adminId") Integer adminId, @PathVariable("approval") String approval) {
        Review review = this.reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewIdNotFoundException("Review not found"));
        Admin admin = this.adminRepository.findById(adminId)
                .orElseThrow(() -> new AdminIdNotFoundException("Admin not found"));

        review.setStatus(approval);
        this.reviewRepository.save(review);
        if (approval == "approved") {
            admin.getApprovedReviews().add(review);
            this.adminRepository.save(admin);

            return ResponseEntity.ok("Review has been approved!");
        } else if (approval == "rejected") {
            admin.getDeniedReviews().add(review);
            this.adminRepository.save(admin);

            return ResponseEntity.ok("Review has been rejected!");
        } else {
            throw new InvalidApprovalStatusException("Invalid approval status");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin request) {
        Optional<Admin> checkUsername = this.adminRepository.findByAdminName(request.getAdminName());
        if (checkUsername.isPresent()) {
            throw new UsernameAlreadyExistsException("Admin name already exists");
        }

        Admin admin = this.adminRepository.save(request);

        return ResponseEntity.ok(admin);
    }
}
