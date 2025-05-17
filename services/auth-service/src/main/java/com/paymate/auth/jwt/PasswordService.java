package com.paymate.auth.jwt;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.security.SecureRandom;

public class PasswordService {
    public String hashPassword(String password) {
        return BCrypt.with(new SecureRandom()).hashToString(6, password.toCharArray());
    }

    public boolean verifyPassword(String password, String hash) {
        return BCrypt.verifyer().verify(password.toCharArray(), hash.toCharArray()).verified;
    }
}
