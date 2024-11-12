package com.example.controller.swagger;

import com.example.dto.SpecializationRequestDto;
import com.example.dto.SpecializationResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface SpecializationControllerDocumentation {

    @Operation(summary = "Create a new specialization",
            description = "Creates a new specialization with the specified data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Specialization successfully created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Validation error in request body", content = @Content)
    })
    ResponseEntity<SpecializationResponseDto> createSpecialization(SpecializationRequestDto specializationRequestDto);

    @Operation(summary = "Update an existing specialization",
            description = "Updates specialization data by the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Specialization successfully updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Specialization not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content)
    })
    ResponseEntity<SpecializationResponseDto> updateSpecialization(UUID specializationId, SpecializationRequestDto specializationRequestDto);

    @Operation(summary = "Delete a specialization by ID",
            description = "Deletes the specialization with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Specialization successfully deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Specialization not found", content = @Content)
    })
    ResponseEntity<Void> deleteSpecialization(UUID specializationId);

    @Operation(summary = "Get a specialization by ID",
            description = "Returns information about the specialization with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specialization successfully retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = SpecializationResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "Specialization not found", content = @Content)
    })
    ResponseEntity<SpecializationResponseDto> getSpecializationById(UUID specializationId);

    @Operation(summary = "Get all specializations",
            description = "Returns a list of all specializations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specializations successfully retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    ResponseEntity<List<SpecializationResponseDto>> getAllSpecializations();
}
