package com.example.demoTwo.dto;

import java.util.UUID;

public record EmployeeRequestDto(
        UUID userId,
        String specializationTitle) {
}
