package com.example.controller;

import com.example.controller.swagger.EmployeeControllerDocumentation;
import com.example.dto.EmployeeRequestDto;
import com.example.dto.EmployeeResponseDto;
import com.example.service.EmployeeService;
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

    private final EmployeeService employeeService;

    @Override
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequestDto));
    }

    @Override
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable UUID employeeId,
                                                              @RequestBody EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employeeRequestDto));
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

    @Override
    @GetMapping("/{userId}/exists")
    public ResponseEntity<Boolean> checkEmployeeExistsByUserId(@PathVariable UUID userId) {
        return ResponseEntity.ok(employeeService.checkEmployeeExistsByUserId(userId));
    }

    @Override
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteEmployeeByUserId(@PathVariable UUID userId) {
        employeeService.deleteEmployeeByUserId(userId);
        return ResponseEntity.noContent().build();
    }

}
