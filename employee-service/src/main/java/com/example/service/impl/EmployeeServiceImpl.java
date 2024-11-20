package com.example.service.impl;

import com.example.client.UserServiceClient;
import com.example.dto.EmployeeDto;
import com.example.dto.EmployeeRequestDto;
import com.example.dto.EmployeeResponseDto;
import com.example.dto.UserDto;
import com.example.entity.Employee;
import com.example.exception.NotFoundException;
import com.example.mapper.EmployeeMapper;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEE_NOT_FOUND = "Employee not found";

    private final UserServiceClient userServiceClient;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
        checkUserExists(employeeRequestDto.userId());
        Employee employee = employeeMapper.toEntity(employeeRequestDto);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    @Transactional
    public EmployeeResponseDto updateEmployee(UUID employeeId, EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException(EMPLOYEE_NOT_FOUND));
        checkUserExists(employeeRequestDto.userId());
        employee.setUserId(employeeRequestDto.userId());
        employee.setSpecializationTitle(employeeRequestDto.specializationTitle());
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(UUID employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new NotFoundException(EMPLOYEE_NOT_FOUND);
        }
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public EmployeeResponseDto getEmployeeById(UUID employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException(EMPLOYEE_NOT_FOUND));
        UserDto userDto = getUserById(employee.getUserId());
        return employeeMapper.toDto(employee, userDto);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employee -> {
                    UserDto userDto = getUserById(employee.getUserId());
                    return employeeMapper.toDto(employee, userDto);
                }).toList();
    }

    @Override
    public boolean checkEmployeeExistsByUserId(UUID userId) {
        return employeeRepository.existsByUserId(userId);
    }

    @Override
    public EmployeeDto getEmployeeByUserId(UUID userId) {
        Employee employee = employeeRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(EMPLOYEE_NOT_FOUND));
        return employeeMapper.toEmployeeDto(employee);
    }

    @Override
    @Transactional
    public void deleteEmployeeByUserId(UUID userId) {
        employeeRepository.deleteEmployeeByUserId(userId);
    }

    @Override
    public void rollbackEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        employeeRepository.save(employee);
    }

    private void checkUserExists(UUID userId) {
        userServiceClient.checkUserExists(userId);
    }

    private UserDto getUserById(UUID userId) {
        return userServiceClient.getUserById(userId).getBody();
    }
}
