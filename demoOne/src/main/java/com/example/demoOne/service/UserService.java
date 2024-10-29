package com.example.demoOne.service;

import com.example.demoOne.dto.UserRequestDto;
import com.example.demoOne.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void createUser(UserRequestDto userRequestDto);

    void updateUser(UUID userId, UserRequestDto userRequestDto);

    void deleteUser(UUID userId);

    UserResponseDto getUserById(UUID userId);

    List<UserResponseDto> getAllUsers();
}
