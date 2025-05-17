package com.paymate.auth;

import com.paymate.auth.bootstrap.AuthServiceBootstrapper;
import com.paymate.auth.grpc.AuthServiceGrpcServer;
import com.paymate.auth.repository.AuthRepository;
import com.paymate.auth.service.AuthService;
import com.paymate.auth.service.AuthServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class AuthServer {
  public static void main(String[] args) {

    Server server = ServerBuilder
            .forPort(50051)
            .addService(AuthServiceBootstrapper.bootstrap())
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
}
