package com.example.demoOne.mapper;

import com.example.demoOne.dto.UserDto;
import com.example.demoOne.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    UserDto toDto(User user);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    User toEntity(UserDto userDto);

}
