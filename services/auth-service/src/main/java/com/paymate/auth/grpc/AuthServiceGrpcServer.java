package com.paymate.auth.grpc;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.paymate.auth.jwt.JwtService;
import com.paymate.auth.model.User;
import com.paymate.auth.repository.AuthRepository;
import com.paymate.auth.service.AuthService;
import com.paymate.grpc.auth.*;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
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

        try
        {
            DecodedJWT decodedJWT =  jwtService.verify(req.getToken());
            String userId = decodedJWT.getSubject();

            User user = authService.getUserById(Integer.parseInt(userId));

            if (user != null) {
                TokenResponse response = TokenResponse.newBuilder()
                        .setValid(true)
                        .setUserId(userId)
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
            else {
                throw new StatusRuntimeException(Status.UNAUTHENTICATED.withDescription("Invalid user."));
            }

        } catch (JWTVerificationException e) {
            throw new StatusRuntimeException(Status.UNAUTHENTICATED.withDescription("Invalid or expired token."));
        }

    }
}
