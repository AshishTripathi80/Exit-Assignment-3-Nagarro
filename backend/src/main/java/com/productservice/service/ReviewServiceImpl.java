package com.productservice.service;

import com.productservice.dto.ReviewDto;
import com.productservice.enums.Constants;
import com.productservice.exception.ProductNotFoundException;
import com.productservice.model.Product;
import com.productservice.model.Review;
import com.productservice.repo.ProductRepository;
import com.productservice.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private  ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public Review postReview(ReviewDto reviewDto){
        Product product=productRepository.findById(reviewDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(Constants.ERROR_PRODUCT_NOT_FOUND.getMessage() + reviewDto.getProductId()));

        Review review= new Review();
        review.setRating(reviewDto.getRating());
        review.setDescription(reviewDto.getDescription());
        review.setProduct(product);

        return reviewRepository.save(review);
    }

    public List<ReviewDto> getReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);

        // Mapping Review to ReviewDto
        return reviews.stream().map(review -> {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setId(review.getId());
            reviewDto.setRating(review.getRating());
            reviewDto.setDescription(review.getDescription());
            reviewDto.setProductId(review.getProduct().getId());
            return reviewDto;
        }).collect(Collectors.toList());
    }
}
