package com.paymate.auth;

import com.paymate.auth.grpc.AuthServiceGrpcServer;
import com.paymate.auth.repository.AuthRepository;
import com.paymate.auth.service.AuthService;
import com.paymate.auth.service.AuthServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class AuthServer {

  private static AuthService authService;
  private static AuthRepository authRepository;
  private static AuthServiceGrpcServer authServiceGrpcServer;

  public static void main(String[] args) {

    authServiceBootstrap();

    Server server = ServerBuilder
            .forPort(50051)
            .addService(authServiceGrpcServer)
            .build();

    System.out.println("AuthService started on port 50051...");

    try {
      server.start();
      server.awaitTermination();
    } catch (Exception e)
    {
      System.out.println("Server terminated.");
    }

  }

  private static void authServiceBootstrap()
  {
    authRepository = new AuthRepository();
    authService = new AuthServiceImpl(authRepository);
    authServiceGrpcServer = new AuthServiceGrpcServer(authService);
  }
}
