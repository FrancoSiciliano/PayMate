package com.paymate.auth.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JwtConfig {
    private final Properties properties = new Properties();

    public JwtConfig() {

        try (FileInputStream input = new FileInputStream("services/auth-service/src/main/resources/application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JWT config", e);
        }

    }

    public String getIssuer() {
        return properties.getProperty("jwt.issuer");
    }

    public String getAudience() {
        return properties.getProperty("jwt.audience");
    }

    public int getExpiration() {
        return Integer.parseInt(properties.getProperty("jwt.expiration"));
    }

    public String getPrivateKeyPath() {
        return properties.getProperty("jwt.private-key-path");
    }

    public String getPublicKeyPath() {
        return properties.getProperty("jwt.public-key-path");
    }
}
