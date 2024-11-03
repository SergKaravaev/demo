package com.example.service;

import com.example.dto.EmployeeRequestDto;
import com.example.dto.EmployeeResponseDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);

    EmployeeResponseDto updateEmployee(UUID employeeId, EmployeeRequestDto employeeRequestDto);

    void deleteEmployee(UUID employeeId);

    EmployeeResponseDto getEmployeeById(UUID employeeId);

    List<EmployeeResponseDto> getAllEmployees();

    boolean checkEmployeeExistsByUserId(UUID userId);

    void deleteEmployeeByUserId(UUID userId);
}
