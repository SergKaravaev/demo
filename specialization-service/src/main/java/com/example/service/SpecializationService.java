package com.example.service;

import com.example.dto.SpecializationRequestDto;
import com.example.dto.SpecializationResponseDto;

import java.util.List;
import java.util.UUID;

public interface SpecializationService {

    SpecializationResponseDto createSpecialization(SpecializationRequestDto specializationRequestDto);

    SpecializationResponseDto updateSpecialization(UUID specializationId, SpecializationRequestDto specializationRequestDto);

    void deleteSpecialization(UUID specializationId);

    SpecializationResponseDto getSpecializationById(UUID specializationId);

    List<SpecializationResponseDto> getAllSpecializations();

    void checkSpecializationExists(UUID specializationId);
}
