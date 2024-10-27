package com.example.demoOne.mapper;

import com.example.demoOne.dto.UserRequestDto;
import com.example.demoOne.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRequestDto toDto(User user);
}
