package com.example.service.impl;

import com.example.dto.SpecializationRequestDto;
import com.example.dto.SpecializationResponseDto;
import com.example.entity.Specialization;
import com.example.exception.NotFoundException;
import com.example.mapper.SpecializationMapper;
import com.example.repository.SpecializationRepository;
import com.example.service.SpecializationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecializationServiceImpl implements SpecializationService {

    private static final String SPECIALIZATION_NOT_FOUND = "Specialization not found";

    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper;

    @Override
    @Transactional
    public SpecializationResponseDto createSpecialization(SpecializationRequestDto specializationRequestDto) {
        Specialization specialization = specializationMapper.toEntity(specializationRequestDto);
        specializationRepository.save(specialization);
        return specializationMapper.toDto(specialization);
    }

    @Override
    @Transactional
    public SpecializationResponseDto updateSpecialization(UUID specializationId, SpecializationRequestDto specializationRequestDto) {
        Specialization specialization = specializationRepository.findById(specializationId)
                .orElseThrow(() -> new NotFoundException(SPECIALIZATION_NOT_FOUND));
        specialization.setTitle(specializationRequestDto.title());
        specializationRepository.save(specialization);
        return specializationMapper.toDto(specialization);
    }

    @Override
    @Transactional
    public void deleteSpecialization(UUID specializationId) {
        checkSpecializationExists(specializationId);
        specializationRepository.deleteById(specializationId);
    }

    @Override
    public SpecializationResponseDto getSpecializationById(UUID specializationId) {
        return specializationRepository.findById(specializationId)
                .map(specializationMapper::toDto)
                .orElseThrow(() -> new NotFoundException(SPECIALIZATION_NOT_FOUND));
    }

    @Override
    public List<SpecializationResponseDto> getAllSpecializations() {
        return specializationRepository.findAll()
                .stream()
                .map(specializationMapper::toDto)
                .toList();
    }

    @Override
    public void checkSpecializationExists(UUID specializationId) {
        if (!specializationRepository.existsById(specializationId)) {
            throw new NotFoundException(SPECIALIZATION_NOT_FOUND);
        }
    }

}
