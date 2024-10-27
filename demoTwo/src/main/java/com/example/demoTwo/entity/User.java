package com.example.demoTwo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
}
