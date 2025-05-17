package com.paymate.auth.bootstrap;

import com.paymate.auth.config.JwtConfig;
import com.paymate.auth.grpc.AuthServiceGrpcServer;
import com.paymate.auth.jwt.JwtService;
import com.paymate.auth.repository.AuthRepository;
import com.paymate.auth.service.AuthService;
import com.paymate.auth.service.AuthServiceImpl;

public class AuthServiceBootstrapper {
    public static AuthServiceGrpcServer bootstrap() throws Exception {
        AuthRepository authRepository = new AuthRepository();
        AuthService authService = new AuthServiceImpl(authRepository);
        JwtService jwtService = new JwtService(new JwtConfig());
        return new AuthServiceGrpcServer(authService, jwtService);
    }
}