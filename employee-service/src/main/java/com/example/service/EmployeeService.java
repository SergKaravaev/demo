package com.example.service;

import com.example.dto.EmployeeDto;
import com.example.dto.EmployeeRequestDto;
import com.example.dto.EmployeeResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto updateEmployee(UUID employeeId, EmployeeRequestDto employeeRequestDto);

    void deleteEmployee(UUID employeeId);

    EmployeeResponseDto getEmployeeById(UUID employeeId);

    List<EmployeeResponseDto> getAllEmployees();

    EmployeeDto getEmployeeByUserId(UUID userId);

    boolean checkEmployeeExistsByUserId(UUID userId);

    void deleteEmployeeByUserId(UUID userId);

    boolean rollbackEmployee(EmployeeDto employeeDto);
}
