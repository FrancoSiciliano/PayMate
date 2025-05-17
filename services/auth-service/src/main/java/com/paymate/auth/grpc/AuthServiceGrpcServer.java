package com.paymate.auth.grpc;

import com.paymate.grpc.auth.*;
import io.grpc.stub.StreamObserver;

public class AuthServiceGrpcServer extends AuthServiceGrpc.AuthServiceImplBase {
    @Override
    public void login(LoginRequest req, StreamObserver<LoginResponse> responseObserver) {
        LoginResponse response = LoginResponse.newBuilder()
                .setToken("aaaa-token")
                .setUserId("2")
                .build();
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
