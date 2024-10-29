package com.example.demoTwo.mapper;


import com.example.demoTwo.dto.UserDto;
import com.example.demoTwo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    UserDto toDto(User user);

}
