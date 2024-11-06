package com.example.dto;

import java.util.UUID;

public record EmployeeResponseDto(
        UUID employeeId,
        UserDto userDto,
        SpecializationDto specializationDto) {
}
