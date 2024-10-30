package com.example.demoTwo.dto;

import java.util.UUID;

public record EmployeeResponseDto(
        UUID employeeId,
        String firstName,
        String lastName) {
}
