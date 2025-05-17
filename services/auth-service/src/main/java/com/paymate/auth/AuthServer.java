package com.paymate.auth;

import com.paymate.auth.bootstrap.AuthServiceBootstrapper;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class AuthServer {
  public static void main(String[] args) throws Exception {

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
