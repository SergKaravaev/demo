package com.example.demoOne.service;

import com.example.demoOne.dto.UserDto;

import java.util.List;

public interface UserService {

    void createUser(UserDto userDto);

    void updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();
}
