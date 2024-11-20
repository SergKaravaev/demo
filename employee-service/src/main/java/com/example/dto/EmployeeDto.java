package com.example.dto;

import java.util.UUID;

public record EmployeeDto(
        UUID employeeId,
        UUID userId,
        String specializationTitle) {
}
