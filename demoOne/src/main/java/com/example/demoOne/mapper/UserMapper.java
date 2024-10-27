package com.example.demoOne.mapper;

import com.example.demoOne.dto.UserRequestDto;
import com.example.demoOne.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserRequestDto toDto(User user) {
        UserRequestDto userRequestDto = new UserRequestDto(user.getFirstName(), user.getLastName());
        return userRequestDto;
    }
}
