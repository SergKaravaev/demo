package com.example.demoOne.service;

import com.example.demoOne.dto.UserDto;
import com.example.demoOne.entity.User;
import com.example.demoOne.exception.NotFoundException;
import com.example.demoOne.mapper.UserMapper;
import com.example.demoOne.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND = "User not found";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Long userId, UserDto userDto) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(USER_NOT_FOUND);
        }
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(USER_NOT_FOUND);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

}
