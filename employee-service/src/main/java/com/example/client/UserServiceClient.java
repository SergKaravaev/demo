package com.example.client;

import com.example.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserServiceClient {

    @GetMapping("/users/{userId}/exists")
    ResponseEntity<Void> checkUserExists(@PathVariable("userId") UUID userId);

    @GetMapping("/users/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable("userId") UUID userId);
}
