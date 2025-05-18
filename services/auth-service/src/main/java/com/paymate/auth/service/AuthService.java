package com.paymate.auth.service;

import com.paymate.auth.model.User;

public interface AuthService {
    User login(String email, String password);
    User getUserByEmail(String email);
    User getUserById(int userId);
}
