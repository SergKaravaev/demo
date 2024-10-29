package com.example.demoTwo.service;

import com.example.demoTwo.dto.UserDto;
import com.example.demoTwo.exception.NotFoundException;
import com.example.demoTwo.mapper.UserMapper;
import com.example.demoTwo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    private static final String USER_NOT_FOUND = "User not found";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getUserByFullName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

}
