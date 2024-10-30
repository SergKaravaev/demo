package com.example.demoTwo.service;



import com.example.demoTwo.dto.EmployeeRequestDto;
import com.example.demoTwo.dto.EmployeeResponseDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    void createEmployee(EmployeeRequestDto employeeRequestDto);

    void updateEmployee(UUID employeeId, EmployeeRequestDto employeeRequestDto);

    void deleteEmployee(UUID employeeId);

    EmployeeResponseDto getEmployeeById(UUID employeeId);

    List<EmployeeResponseDto> getAllEmployees();
}
