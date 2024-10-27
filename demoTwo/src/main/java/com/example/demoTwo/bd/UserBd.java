package com.example.demoTwo.bd;

import com.example.demoTwo.entity.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class UserBd {
    private Map<Long, User> users = new HashMap<>();

    public UserBd() {
        User ivanIvanov = new User(1L, "Ivan", "Ivanov");
        User nikitaPetrov = new User(2L, "Nikita", "Petrov");
        users.put(ivanIvanov.getUserId(), ivanIvanov);
        users.put(nikitaPetrov.getUserId(), nikitaPetrov);
    }
}
