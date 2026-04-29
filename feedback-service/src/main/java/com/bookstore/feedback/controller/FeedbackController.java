package com.bookstore.feedback.controller;

import com.bookstore.feedback.dto.ReviewRequest;
import com.bookstore.feedback.entity.Review;
import com.bookstore.feedback.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<Review> submit(@RequestHeader("userId") Long userId,
                                         @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(feedbackService.submitReview(userId, request));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getProductReviews(id));
    }

    @GetMapping("/product/{id}/rating")
    public ResponseEntity<Double> getRating(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getAverageRating(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> edit(@PathVariable Long id,
                                       @RequestHeader("userId") Long userId,
                                       @Valid @RequestBody ReviewRequest request) {
        return ResponseEntity.ok(feedbackService.editReview(id, userId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        feedbackService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}