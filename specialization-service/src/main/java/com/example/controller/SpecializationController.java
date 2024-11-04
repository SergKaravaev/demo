package com.example.controller;

import com.example.controller.swagger.SpecializationControllerDocumentation;
import com.example.dto.SpecializationRequestDto;
import com.example.dto.SpecializationResponseDto;
import com.example.service.SpecializationService;
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

    private final SpecializationService specializationService;

    @Override
    @PostMapping
    public ResponseEntity<SpecializationResponseDto> createSpecialization(@RequestBody SpecializationRequestDto specializationRequestDto) {
        return ResponseEntity.ok(specializationService.createSpecialization(specializationRequestDto));
    }

    @Override
    @PutMapping("/{specializationId}")
    public ResponseEntity<SpecializationResponseDto> updateSpecialization(@PathVariable UUID specializationId,
                                                      @RequestBody SpecializationRequestDto specializationRequestDto) {
        return ResponseEntity.ok(specializationService.updateSpecialization(specializationId, specializationRequestDto));
    }

    @Override
    @DeleteMapping("/{specializationId}")
    public ResponseEntity<Void> deleteSpecialization(@PathVariable UUID specializationId) {
        specializationService.deleteSpecialization(specializationId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{specializationId}")
    public ResponseEntity<SpecializationResponseDto> getSpecializationById(@PathVariable UUID specializationId) {
        return ResponseEntity.ok(specializationService.getSpecializationById(specializationId));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<SpecializationResponseDto>> getAllSpecializations() {
        return ResponseEntity.ok(specializationService.getAllSpecializations());
    }

}
