package com.paymate.auth;

import com.paymate.auth.grpc.AuthServiceGrpcServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class AuthServer {
  public static void main(String[] args) {

    Server server = ServerBuilder
            .forPort(50051)
            .addService(new AuthServiceGrpcServer())
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
