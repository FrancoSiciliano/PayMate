package com.paymate.auth.grpc;

import com.paymate.auth.model.User;
import com.paymate.auth.service.AuthService;
import com.paymate.grpc.auth.*;
import io.grpc.stub.StreamObserver;

public class AuthServiceGrpcServer extends AuthServiceGrpc.AuthServiceImplBase {

    private final AuthService authService;

    public AuthServiceGrpcServer(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void login(LoginRequest req, StreamObserver<LoginResponse> responseObserver) {

        LoginResponse response;
        User user = authService.login(req.getEmail(), req.getPassword());

        if (user != null)
        {
             response = LoginResponse.newBuilder()
                     .setToken("VALID-TOKEN")
                     .setUserId(String.valueOf(user.getUserId()))
                     .build();

        }
        else
        {
            response = LoginResponse.newBuilder()
                    .build();
        }


        responseObserver.onNext(response);
        responseObserver.onCompleted();
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
