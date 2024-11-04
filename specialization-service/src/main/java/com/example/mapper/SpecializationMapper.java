package com.example.mapper;

import com.example.dto.SpecializationRequestDto;
import com.example.dto.SpecializationResponseDto;
import com.example.entity.Specialization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpecializationMapper {

    @Mapping(target = "specializationId", source = "specializationId")
    @Mapping(target = "title", source = "title")
    SpecializationResponseDto toDto(Specialization specialization);

    @Mapping(target = "specializationId", ignore = true)
    @Mapping(target = "title", source = "title")
    Specialization toEntity(SpecializationRequestDto specializationRequestDto);

}
