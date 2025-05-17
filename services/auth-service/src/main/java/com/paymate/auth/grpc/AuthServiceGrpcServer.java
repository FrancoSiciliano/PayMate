package com.paymate.auth.grpc;

import com.auth0.jwt.algorithms.Algorithm;
import com.paymate.auth.jwt.JwtService;
import com.paymate.auth.model.User;
import com.paymate.auth.service.AuthService;
import com.paymate.grpc.auth.*;
import io.grpc.stub.StreamObserver;

public class AuthServiceGrpcServer extends AuthServiceGrpc.AuthServiceImplBase {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthServiceGrpcServer(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @Override
    public void login(LoginRequest req, StreamObserver<LoginResponse> responseObserver) {

        User user = authService.login(req.getEmail(), req.getPassword());

        if (user != null)
        {
            String userId = String.valueOf(user.getUserId());

             LoginResponse response = LoginResponse.newBuilder()
                     .setToken(jwtService.generateToken(userId, user.getEmail()))
                     .setUserId(userId)
                     .build();

             responseObserver.onNext(response);
             responseObserver.onCompleted();
        }
        else
        {
            responseObserver.onError(new Exception("email or password are wrong"));
        }



    }

    @Override
    public void validateToken(TokenRequest req, StreamObserver<TokenResponse> responseObserver) {
        TokenResponse response = TokenResponse.newBuilder()
                .setValid(true)
                .setUserId("2")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
