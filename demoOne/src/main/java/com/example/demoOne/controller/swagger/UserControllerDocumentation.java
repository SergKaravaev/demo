package com.example.demoOne.controller.swagger;

import com.example.demoOne.dto.UserRequestDto;
import com.example.demoOne.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserControllerDocumentation {

    @Operation(summary = "Create a new user",
            description = "Creates a new user with the specified data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User successfully created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Validation error in request body", content = @Content)
    })
    ResponseEntity<Void> createUser(UserRequestDto userRequestDto);

    @Operation(summary = "Update an existing user",
            description = "Updates user data by the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User successfully updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content)
    })
    ResponseEntity<Void> updateUser(UUID userId, UserRequestDto userRequestDto);

    @Operation(summary = "Delete a user by ID",
            description = "Deletes the user with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User successfully deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    ResponseEntity<Void> deleteUser(UUID userId);

    @Operation(summary = "Get a user by ID",
            description = "Returns information about the user with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    ResponseEntity<UserResponseDto> getUserById(UUID userId);

    @Operation(summary = "Get all users",
            description = "Returns a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users successfully retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    ResponseEntity<List<UserResponseDto>> getAllUsers();
}
