package com.example.client;

import com.example.dto.EmployeeRequestDto;
import com.example.dto.EmployeeResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "employee-service", url = "${employee.service.url}")
public interface EmployeeServiceClient {

    @PostMapping("/employees")
    ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto);

    @PutMapping("/employees/{employeeId}")
    ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable UUID employeeId,
                                                       @RequestBody EmployeeRequestDto employeeRequestDto);

    @DeleteMapping("/employees/{employeeId}")
    ResponseEntity<Void> deleteEmployee(@PathVariable UUID employeeId);

    @GetMapping("/employees/{employeeId}")
    ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable UUID employeeId);

    @GetMapping("/employees")
    ResponseEntity<List<EmployeeResponseDto>> getAllEmployees();

}
