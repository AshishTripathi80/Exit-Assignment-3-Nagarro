package com.productservice.dto;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class ReviewDto {

    private Long id;

    private Long  rating;

    @Lob
    private String description;

    private Long productId;
}
