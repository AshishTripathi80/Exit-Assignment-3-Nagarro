package com.productservice.controller;

import com.productservice.dto.ReviewDto;
import com.productservice.model.Review;
import com.productservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews/")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // POST endpoint to post a review
    @PostMapping
    public ResponseEntity<Review> postReview(@ModelAttribute ReviewDto reviewDto) {
        Review postedReview = reviewService.postReview(reviewDto);
        return ResponseEntity.ok(postedReview);
    }

    @GetMapping("{productId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByProductId(@PathVariable Long productId) {
        List<ReviewDto> reviews = reviewService.getReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }
}
