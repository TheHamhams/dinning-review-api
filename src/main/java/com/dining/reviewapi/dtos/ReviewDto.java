package com.dining.reviewapi.dtos;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;

@Data
public class ReviewDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Float rating;
    private Float peanuts;
    private Float egg;
    private Float dairy;
    private String title;
    private String content;
    private Date reviewDate;
    private String status;

    private String username;
    private Integer restaurantId;
}
