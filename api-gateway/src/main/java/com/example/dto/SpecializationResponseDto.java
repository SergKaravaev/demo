package com.example.dto;

import java.util.UUID;

public record SpecializationResponseDto(
        UUID specializationId,
        String title) {
}
