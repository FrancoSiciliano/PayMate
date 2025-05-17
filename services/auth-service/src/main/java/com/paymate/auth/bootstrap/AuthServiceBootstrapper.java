package com.paymate.auth.bootstrap;

import com.paymate.auth.grpc.AuthServiceGrpcServer;
import com.paymate.auth.repository.AuthRepository;
import com.paymate.auth.service.AuthService;
import com.paymate.auth.service.AuthServiceImpl;

public class AuthServiceBootstrapper {
    public static AuthServiceGrpcServer bootstrap() {
        AuthRepository authRepository = new AuthRepository();
        AuthService authService = new AuthServiceImpl(authRepository);
        return new AuthServiceGrpcServer(authService);
    }
}