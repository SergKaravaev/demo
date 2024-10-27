package com.example.demoTwo.service;

import com.example.demoTwo.dto.UserRequestDto;
import com.example.demoTwo.entity.User;
import com.example.demoTwo.mapper.UserMapper;
import com.example.demoTwo.repository.UserRepository;
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
