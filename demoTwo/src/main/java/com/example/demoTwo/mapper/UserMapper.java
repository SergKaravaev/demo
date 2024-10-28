package com.example.demoTwo.mapper;

import com.example.demoTwo.dto.UserRequestDto;
import com.example.demoTwo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserRequestDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserRequestDto userRequestDto = new UserRequestDto(user.getFirstName(), user.getLastName());
        return userRequestDto;
    }
}
