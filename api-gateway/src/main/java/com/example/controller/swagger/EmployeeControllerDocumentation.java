package com.example.controller.swagger;

import com.example.dto.EmployeeRequestDto;
import com.example.dto.EmployeeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface EmployeeControllerDocumentation {

    @Operation(summary = "Create a new employee",
            description = "Creates a new employee with the specified data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee successfully created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Validation error in request body", content = @Content)
    })
    ResponseEntity<EmployeeResponseDto> createEmployee(EmployeeRequestDto employeeRequestDto);

    @Operation(summary = "Update an existing employee",
            description = "Updates employee data by the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee successfully updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content)
    })
    ResponseEntity<EmployeeResponseDto> updateEmployee(UUID employeeId, EmployeeRequestDto employeeRequestDto);

    @Operation(summary = "Delete a employee by ID",
            description = "Deletes the employee with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Employee successfully deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    ResponseEntity<Void> deleteEmployee(UUID employeeId);

    @Operation(summary = "Get a employee by ID",
            description = "Returns information about the employee with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee successfully retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
    })
    ResponseEntity<EmployeeResponseDto> getEmployeeById(UUID employeeId);

    @Operation(summary = "Get all employees",
            description = "Returns a list of all employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employees successfully retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    ResponseEntity<List<EmployeeResponseDto>> getAllEmployees();

}
