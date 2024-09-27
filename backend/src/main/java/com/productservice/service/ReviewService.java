package com.productservice.service;

import com.productservice.dto.ReviewDto;
import com.productservice.model.Review;

import java.util.List;

public interface ReviewService {

    Review postReview(ReviewDto reviewDto);
    List<ReviewDto> getReviewsByProductId(Long productId);
}
