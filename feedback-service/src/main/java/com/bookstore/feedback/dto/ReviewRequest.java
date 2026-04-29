package com.bookstore.feedback.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ReviewRequest {
    @NotNull
    private Long productId;

    @NotBlank
    private String comment;

    @Min(1) @Max(5)
    private int rating;
}