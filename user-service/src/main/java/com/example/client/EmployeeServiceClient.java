package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "employee-service", url = "${employee.service.url}")
public interface EmployeeServiceClient {

    @GetMapping("/employees/{userId}/exists")
    ResponseEntity<Boolean> checkEmployeeExistsByUserId(@PathVariable UUID userId);

    @DeleteMapping("/employees/users/{userId}")
    ResponseEntity<Void> deleteEmployeeByUserId(@PathVariable UUID userId);
}
