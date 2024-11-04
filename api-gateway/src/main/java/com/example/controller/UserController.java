package com.example.controller;

import com.example.client.UserServiceClient;
import com.example.controller.swagger.UserControllerDocumentation;
import com.example.dto.UserRequestDto;
import com.example.dto.UserResponseDto;
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
@RequestMapping("/users")
public class UserController implements UserControllerDocumentation {

    private final UserServiceClient userServiceClient;

    @Override
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userServiceClient.createUser(userRequestDto).getBody());
    }

    @Override
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID userId,
                                                      @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userServiceClient.updateUser(userId, userRequestDto).getBody());
    }

    @Override
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userServiceClient.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userServiceClient.getUserById(userId).getBody());
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userServiceClient.getAllUsers().getBody());
    }

}
