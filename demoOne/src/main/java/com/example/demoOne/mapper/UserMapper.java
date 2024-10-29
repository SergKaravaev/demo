package com.example.demoOne.mapper;

import com.example.demoOne.dto.UserRequestDto;
import com.example.demoOne.dto.UserResponseDto;
import com.example.demoOne.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    UserResponseDto toDto(User user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    User toEntity(UserRequestDto userRequestDto);

}
