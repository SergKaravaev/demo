package com.example.controller;

import com.example.client.EmployeeServiceClient;
import com.example.controller.swagger.EmployeeControllerDocumentation;
import com.example.dto.EmployeeRequestDto;
import com.example.dto.EmployeeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController implements EmployeeControllerDocumentation {

    private final EmployeeServiceClient employeeServiceClient;

    @Override
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.ok(employeeServiceClient.createEmployee(employeeRequestDto).getBody());
    }

    @Override
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable UUID employeeId,
                                                              @RequestBody EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.ok(employeeServiceClient.updateEmployee(employeeId, employeeRequestDto).getBody());
    }

    @Override
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID employeeId) {
        employeeServiceClient.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable UUID employeeId) {
        return ResponseEntity.ok(employeeServiceClient.getEmployeeById(employeeId).getBody());
    }

    @Override
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeServiceClient.getAllEmployees().getBody());
    }

}
