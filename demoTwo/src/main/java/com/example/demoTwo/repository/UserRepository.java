package com.example.demoTwo.repository;

import com.example.demoTwo.bd.UserBd;
import com.example.demoTwo.entity.User;
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
