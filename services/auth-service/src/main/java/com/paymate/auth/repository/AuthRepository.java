package com.paymate.auth.repository;

import com.paymate.auth.model.User;

import java.util.ArrayList;
import java.util.List;


public class AuthRepository {

    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "jose.siciliano@mail.com", "1234"));
        users.add(new User(2, "lucia.siciliano@mail.com", "1234"));
        users.add(new User(3, "fernanda.saldivar@mail.com", "1234"));
        users.add(new User(4, "franco.siciliano@mail.com", "1234"));
    }

    public User getUserByEmail(String email) {
        for (User user : users)
        {
            if (user.getEmail().equals(email))
            {
                return user;
            }
        }
        return  null;
    }
}
