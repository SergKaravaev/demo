package com.example.demoTwo.service.impl;

import com.example.demoTwo.dto.EmployeeRequestDto;
import com.example.demoTwo.dto.EmployeeResponseDto;
import com.example.demoTwo.entity.Employee;
import com.example.demoTwo.exception.NotFoundException;
import com.example.demoTwo.mapper.EmployeeMapper;
import com.example.demoTwo.repository.EmployeeRepository;
import com.example.demoTwo.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final String EMPLOYEE_NOT_FOUND = "Employee not found";

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public void createEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeMapper.toEntity(employeeRequestDto);
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(UUID employeeId, EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException(EMPLOYEE_NOT_FOUND));
        employee.setUserId(employeeRequestDto.userId());
        employee.setSpecializationTitle(employeeRequestDto.specializationTitle());
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(UUID employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new NotFoundException(EMPLOYEE_NOT_FOUND);
        }
        employeeRepository.deleteById(employeeId);
    }

//    @Override
//    public EmployeeResponseDto getEmployeeById(UUID employeeId) {
//        return employeeRepository.findById(employeeId)
//                .map(employeeMapper::toDto)
//                .orElseThrow(() -> new NotFoundException(EMPLOYEE_NOT_FOUND));
//    }
//
//    @Override
//    public List<EmployeeResponseDto> getAllEmployees() {
//        return employeeRepository.findAll()
//                .stream()
//                .map(employeeMapper::toDto)
//                .collect(Collectors.toList());
//    }

}
