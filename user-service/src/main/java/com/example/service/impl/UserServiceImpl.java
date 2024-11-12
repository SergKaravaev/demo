package com.example.service.impl;

import com.example.client.EmployeeServiceClient;
import com.example.dto.EmployeeDto;
import com.example.dto.UserRequestDto;
import com.example.dto.UserResponseDto;
import com.example.entity.User;
import com.example.exception.NotFoundException;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND = "User not found";

    private final EmployeeServiceClient employeeServiceClient;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(UUID userId, UserRequestDto userRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user.setFirstName(userRequestDto.firstName());
        user.setLastName(userRequestDto.lastName());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(UUID userId) {
        EmployeeDto employeeDto = getEmployeeByUserId(userId);
        UserResponseDto userResponseDto = getUserById(userId);
        checkUserExists(userId);
        try {
            deleteEmployee(userId);
            userRepository.deleteById(userId);
        } catch (Exception exception) {
            rollbackEmployee(employeeDto);
            rollbackUser(userResponseDto);
        }
    }


    @Override
    public UserResponseDto getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public void checkUserExists(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(USER_NOT_FOUND);
        }
    }

    private void deleteEmployee(UUID userId) {
        if (Boolean.TRUE.equals(employeeServiceClient.checkEmployeeExistsByUserId(userId).getBody())) {
            employeeServiceClient.deleteEmployeeByUserId(userId);
        }
    }

    private EmployeeDto getEmployeeByUserId(UUID userId) {
        return employeeServiceClient.getEmployeeByUserId(userId).getBody();
    }

    private void rollbackEmployee(EmployeeDto employeeDto) {
        employeeServiceClient.rollbackEmployee(employeeDto);
    }

    private void rollbackUser(UserResponseDto userResponseDto) {
        User user = userMapper.toEntity(userResponseDto);
        userRepository.save(user);
    }
}
