package com.bookstore.feedback.service;

import com.bookstore.feedback.dto.ReviewRequest;
import com.bookstore.feedback.entity.Review;
import com.bookstore.feedback.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final ReviewRepository reviewRepository;

    public Review submitReview(Long userId, ReviewRequest request) {
        Review review = Review.builder()
                .productId(request.getProductId())
                .userId(userId)
                .comment(request.getComment())
                .rating(request.getRating())
                .build();
        return reviewRepository.save(review);
    }

    public List<Review> getProductReviews(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public Double getAverageRating(Long productId) {
        return reviewRepository.getAverageRating(productId);
    }

    public Review editReview(Long id, Long userId, ReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        if (!review.getUserId().equals(userId)) throw new RuntimeException("Unauthorized");
        review.setComment(request.getComment());
        review.setRating(request.getRating());
        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}