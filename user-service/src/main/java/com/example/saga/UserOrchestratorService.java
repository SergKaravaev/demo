package com.example.saga;

import com.example.client.EmployeeServiceClient;
import com.example.dto.EmployeeDto;
import com.example.entity.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserOrchestratorService {

    private final UserRepository userRepository;
    private final EmployeeServiceClient employeeService;

    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        EmployeeDto employee = employeeService.getEmployeeByUserId(userId).getBody();

        try {
            employeeService.deleteEmployeeByUserId(userId);
            userRepository.deleteById(userId);
        } catch (Exception e) {
            compensateDelete(user, employee);
            throw new RuntimeException("Error deleting user");
        }
    }

    private void compensateDelete(User user, EmployeeDto employee) {
        try {
            userRepository.save(user);
            boolean rollbackSuccess = employeeService.rollbackEmployee(employee).getBody();

            if (!rollbackSuccess) {
                throw new RuntimeException("Failed to rollback");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error compensation of delete user and employee");
        }
    }

}
