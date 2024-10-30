package com.example.demoTwo.controller;

import com.example.demoTwo.controller.swagger.EmployeeControllerDocumentation;
import com.example.demoTwo.dto.EmployeeRequestDto;
import com.example.demoTwo.dto.EmployeeResponseDto;
import com.example.demoTwo.service.EmployeeServiceImpl;
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

    private final EmployeeServiceImpl employeeService;

    @Override
    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.createEmployee(employeeRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{employeeId}")
    public ResponseEntity<Void> updateEmployee(@PathVariable UUID employeeId,
                                           @RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.updateEmployee(employeeId, employeeRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable UUID employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

}
