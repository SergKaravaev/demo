package com.example.client;

import com.example.dto.UserRequestDto;
import com.example.dto.UserResponseDto;
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

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserServiceClient {

    @PostMapping("/users")
    ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto);

    @PutMapping("/users/{userId}")
    ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID userId,
                                               @RequestBody UserRequestDto userRequestDto);

    @DeleteMapping("/users/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable UUID userId);

    @GetMapping("/users/{userId}")
    ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID userId);

    @GetMapping("/users")
    ResponseEntity<List<UserResponseDto>> getAllUsers();

}
