package com.example.demoTwo.controller;

import com.example.demoTwo.dto.UserDto;
import com.example.demoTwo.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/users")
    public ResponseEntity<UserDto> getUserByFullName(@RequestParam String firstName,
                                                     @RequestParam String lastName) {
        return ResponseEntity.ok(userService.getUserByFullName(firstName, lastName));
    }
}
