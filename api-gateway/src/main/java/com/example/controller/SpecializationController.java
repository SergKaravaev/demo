package com.example.controller;

import com.example.client.SpecializationServiceClient;
import com.example.controller.swagger.SpecializationControllerDocumentation;
import com.example.dto.SpecializationRequestDto;
import com.example.dto.SpecializationResponseDto;
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
@RequestMapping("/specializations")
public class SpecializationController implements SpecializationControllerDocumentation {

    private final SpecializationServiceClient specializationServiceClient;

    @Override
    @PostMapping
    public ResponseEntity<SpecializationResponseDto> createSpecialization(@RequestBody SpecializationRequestDto specializationRequestDto) {
        return ResponseEntity.ok(specializationServiceClient.createSpecialization(specializationRequestDto).getBody());
    }

    @Override
    @PutMapping("/{specializationId}")
    public ResponseEntity<SpecializationResponseDto> updateSpecialization(@PathVariable UUID specializationId,
                                                      @RequestBody SpecializationRequestDto specializationRequestDto) {
        return ResponseEntity.ok(specializationServiceClient.updateSpecialization(specializationId, specializationRequestDto).getBody());
    }

    @Override
    @DeleteMapping("/{specializationId}")
    public ResponseEntity<Void> deleteSpecialization(@PathVariable UUID specializationId) {
        specializationServiceClient.deleteSpecialization(specializationId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{specializationId}")
    public ResponseEntity<SpecializationResponseDto> getSpecializationById(@PathVariable UUID specializationId) {
        return ResponseEntity.ok(specializationServiceClient.getSpecializationById(specializationId)).getBody();
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SpecializationResponseDto>> getAllSpecializations() {
        return ResponseEntity.ok(specializationServiceClient.getAllSpecializations()).getBody();
    }

}
