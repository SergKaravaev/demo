package com.example.dto;

import java.util.UUID;

public record EmployeeRequestDto(
        UUID userId,
        String specializationTitle) {
}
