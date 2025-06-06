package com.example.newsmanager.domain.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank String username,
    @NotBlank UserRole role,
    @NotBlank @Email String email,
    @NotBlank @Size(min = 6) String password
) {}