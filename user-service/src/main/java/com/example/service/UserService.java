package com.example.service;

import com.example.dto.UserRequestDto;
import com.example.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(UUID userId, UserRequestDto userRequestDto);

    void deleteUser(UUID userId);

    UserResponseDto getUserById(UUID userId);

    List<UserResponseDto> getAllUsers();

    void checkUserExists(UUID userId);
}
