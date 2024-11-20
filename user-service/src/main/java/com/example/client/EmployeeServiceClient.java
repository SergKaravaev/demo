package com.example.client;

import com.example.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "employee-service", url = "${employee.service.url}")
public interface EmployeeServiceClient {

    @DeleteMapping("/employees/users/{userId}")
    ResponseEntity<Void> deleteEmployeeByUserId(@PathVariable UUID userId);

    @GetMapping("/employees/get-employee/{userId}")
    ResponseEntity<EmployeeDto> getEmployeeByUserId(@PathVariable UUID userId);

    @PostMapping("/employees/rollback")
    ResponseEntity<Boolean> rollbackEmployee(@RequestBody EmployeeDto employeeDto);
}
