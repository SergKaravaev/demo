package com.example.demoTwo.mapper;

import com.example.demoTwo.dto.UserRequestDto;
import com.example.demoTwo.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRequestDto toDto(User user);
}
