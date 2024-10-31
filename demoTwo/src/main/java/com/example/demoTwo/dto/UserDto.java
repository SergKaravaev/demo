package com.example.demoTwo.dto;

import java.util.UUID;

public record UserDto(
        UUID userId,
        String firstName,
        String lastName
) {
}
