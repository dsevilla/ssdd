/*
 * Copyright 2015, Google Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *
 *    * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

 package es.um.sisdist.calc;

 import io.grpc.Server;
 import io.grpc.ServerBuilder;
 import io.grpc.stub.StreamObserver;
 
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
 
 /**
  * Server that manages startup/shutdown of a {@code Greeter} server.
  */
 public class CalcServer {
   private static final Logger logger = Logger.getLogger(CalcServer.class.getName());
 
   /* The port on which the server should run */
   private int port = 50051;
   private Server server;
 
   private void start() throws IOException {
     server = ServerBuilder.forPort(port)
         .addService(new CalcImpl())
         .build()
         .start();
     logger.info("Server started, listening on " + port);
     Runtime.getRuntime().addShutdownHook(new Thread() {
       @Override
       public void run() {
         // Use stderr here since the logger may have been reset by its JVM shutdown hook.
         System.err.println("*** shutting down gRPC server since JVM is shutting down");
         CalcServer.this.stop();
         System.err.println("*** server shut down");
       }
     });
   }
 
   private void stop() {
     if (server != null) {
       server.shutdown();
     }
   }
 
   /**
    * Await termination on the main thread since the grpc library uses daemon threads.
    */
   private void blockUntilShutdown() throws InterruptedException {
     if (server != null) {
       server.awaitTermination();
     }
   }
 
   /**
    * Main launches the server from the command line.
    */
   public static void main(String[] args) throws IOException, InterruptedException {
     final CalcServer server = new CalcServer();
     server.start();
     server.blockUntilShutdown();
   }
 
   private class CalcImpl extends CalcGrpc.CalcImplBase {
 
     @Override
     public void sumame(CalcRequest req, StreamObserver<CalcResponse> responseObserver) {
      List<Integer> array_c = new ArrayList<Integer>();
      for (int i = 0; i < req.getACount(); i++)
        array_c.add(req.getA(i) + req.getB());

      CalcResponse reply = CalcResponse.newBuilder().addAllC(array_c).build();
       responseObserver.onNext(reply);
       responseObserver.onCompleted();
     }

     @Override
     public void restame(CalcRequest req, StreamObserver<CalcResponse> responseObserver) {
      List<Integer> array_c = new ArrayList<Integer>();
      for (int i = 0; i < req.getACount(); i++)
        array_c.add(req.getA(i) - req.getB());
        
        CalcResponse reply = CalcResponse.newBuilder().addAllC(array_c).build();
       responseObserver.onNext(reply);
       responseObserver.onCompleted();
     }
   }
 }
 