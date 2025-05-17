package com.paymate.auth.service;

import com.paymate.auth.model.User;
import com.paymate.auth.repository.AuthRepository;
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public User login(String email, String password) {

        User user = authRepository.getUserByEmail(email);

        if (user != null)
        {
            return user.getPassword().equals(password) ? user : null;
        }

        return null;
    }
}
