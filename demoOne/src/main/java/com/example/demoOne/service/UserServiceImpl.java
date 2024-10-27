package com.example.demoOne.service;

import com.example.demoOne.dto.UserRequestDto;
import com.example.demoOne.exception.NotFoundException;
import com.example.demoOne.mapper.UserMapper;
import com.example.demoOne.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private static final String USER_NOT_FOUND = "User not found";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserRequestDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

}
