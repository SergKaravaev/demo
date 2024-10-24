package com.example.demoOne.service;

import com.example.demoOne.dto.UserRequestDto;
import com.example.demoOne.entity.User;
import com.example.demoOne.mapper.UserMapper;
import com.example.demoOne.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserRequestDto getUserByFullName(String firstName, String lastName) {
        User user = userRepository.getUserByFullName(firstName, lastName);
        UserRequestDto userRequestDto = userMapper.toDto(user);
        return userRequestDto;
    }
}
