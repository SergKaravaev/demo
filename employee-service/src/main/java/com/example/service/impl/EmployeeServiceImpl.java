package com.example.service.impl;

import com.example.client.UserServiceClient;
import com.example.dto.EmployeeRequestDto;
import com.example.dto.EmployeeResponseDto;
import com.example.dto.SpecializationDto;
import com.example.dto.UserDto;
import com.example.entity.Employee;
import com.example.exception.NotFoundException;
import com.example.mapper.EmployeeMapper;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
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
        userServiceClient.checkUserExists(employeeRequestDto.userId());
        Employee employee = employeeMapper.toEntity(employeeRequestDto);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    @Transactional
    public EmployeeResponseDto updateEmployee(UUID employeeId, EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException(EMPLOYEE_NOT_FOUND));
        userServiceClient.checkUserExists(employeeRequestDto.userId());
        employee.setUserId(employeeRequestDto.userId());
        employee.setSpecializationId(employeeRequestDto.specializationId());
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
        ResponseEntity<UserDto> userResponse = userServiceClient.getUserById(employee.getUserId());
        UserDto userDto = userResponse.getBody();
        return employeeMapper.toDto(employee, userDto, new SpecializationDto(UUID.randomUUID(), "title"));
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employee -> {
                    ResponseEntity<UserDto> userResponse = userServiceClient.getUserById(employee.getUserId());
                    UserDto userDto = userResponse.getBody();
                    return employeeMapper.toDto(employee, userDto, new SpecializationDto(UUID.randomUUID(), "title"));
                }).toList();
    }

    @Override
    public boolean checkEmployeeExistsByUserId(UUID userId) {
        return employeeRepository.existsByUserId(userId);
    }

    @Override
    @Transactional
    public void deleteEmployeeByUserId(UUID userId) {
        employeeRepository.deleteEmployeeByUserId(userId);
    }
}
