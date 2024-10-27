package com.example.demoOne.repository;

import com.example.demoOne.bd.UserBd;
import com.example.demoOne.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserBd userBd;

    public User getUserById(Long userId) {
        User user = userBd.getUsers().get(userId);
        return user;
    }
}
