package com.example.demoOne.repository;

import com.example.demoOne.bd.UserBd;
import com.example.demoOne.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserBd userBd;

    public User getUserByFullName(String firstName, String lastName) {
        for (User user : userBd.getUsers().values()) {
            if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
                return user;
            }
        }
        return null;
    }
}
