package com.paymate.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.paymate.auth.config.JwtConfig;
import com.paymate.auth.util.KeyUtils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

public class JwtService {
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final JwtConfig config;

    public JwtService(JwtConfig config) throws Exception {
        this.config = config;
        RSAPublicKey publicKey = KeyUtils.loadPublicKey(config.getPublicKeyPath());
        RSAPrivateKey privateKey = KeyUtils.loadPrivateKey(config.getPrivateKeyPath());
        this.algorithm = Algorithm.RSA256(publicKey, privateKey);
        this.verifier = JWT.require(algorithm)
                .withIssuer(config.getIssuer())
                .withAudience(config.getAudience())
                .build();
    }

    public String generateToken(String userId, String email) {
        return JWT.create()
                .withIssuer(config.getIssuer())
                .withAudience(config.getAudience())
                .withSubject(userId)
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + config.getExpiration() * 1000L))
                .sign(algorithm);
    }

    public DecodedJWT verify(String token) {
        return verifier.verify(token);
    }
}
