package com.bookstore.user.dto;

import lombok.*;

@Data @AllArgsConstructor
public class AuthResponse {
    private String token;
    private String email;
    private String role;
}