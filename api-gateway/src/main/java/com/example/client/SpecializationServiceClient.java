package com.example.client;

import com.example.dto.SpecializationRequestDto;
import com.example.dto.SpecializationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "specialization-service", url = "${specialization.service.url}")
public interface SpecializationServiceClient {

    @PostMapping("/specializations")
    ResponseEntity<SpecializationResponseDto> createSpecialization(@RequestBody SpecializationRequestDto specializationRequestDto);

    @PutMapping("/specializations/{specializationId}")
    ResponseEntity<SpecializationResponseDto> updateSpecialization(@PathVariable UUID specializationId,
                                               @RequestBody SpecializationRequestDto specializationRequestDto);

    @DeleteMapping("/specializations/{specializationId}")
    ResponseEntity<Void> deleteSpecialization(@PathVariable UUID specializationId);

    @GetMapping("/specializations/{specializationId}")
    ResponseEntity<SpecializationResponseDto> getSpecializationById(@PathVariable UUID specializationId);

    @GetMapping("/specializations")
    ResponseEntity<List<SpecializationResponseDto>> getAllSpecializations();

}
