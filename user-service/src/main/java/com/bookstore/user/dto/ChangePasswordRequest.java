package com.bookstore.user.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ChangePasswordRequest {

    @NotBlank
    private String currentPassword;

    @Size(min = 8)
    @Pattern(regexp = ".*[A-Z].*", message = "New password must contain an uppercase letter")
    private String newPassword;
}