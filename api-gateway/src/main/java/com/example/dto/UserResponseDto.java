package com.example.dto;

import java.util.UUID;

public record UserResponseDto(
        UUID userId,
        String firstName,
        String lastName) {
}
