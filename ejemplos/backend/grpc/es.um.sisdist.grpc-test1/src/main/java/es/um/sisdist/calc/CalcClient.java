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

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.um.sisdist.calc.CalcClient;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
public class CalcClient 
{
  private static final Logger logger = Logger.getLogger(CalcClient.class.getName());

  private final ManagedChannel channel;
  private final CalcGrpc.CalcBlockingStub blockingStub;

  /** Construct client connecting to HelloWorld server at {@code host:port}. */
  public CalcClient(String host, int port) 
  {
    channel = ManagedChannelBuilder.forAddress(host, port)
        // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
        // needing certificates.
        .usePlaintext()
        .build();
    blockingStub = CalcGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  /** Say hello to server. */
  public void suma() {
    logger.info("Creando numeros");

    List<Integer> array_a = new ArrayList<Integer>();

    for (int i = 0; i < 5; i++) array_a.add(i);

    CalcRequest request = CalcRequest.newBuilder().addAllA(array_a).setB(2).build();

    CalcResponse response;
    try {
      response = blockingStub.sumame(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }

    for (int i = 0; i < response.getCCount();i++)
      logger.info(i + "Suma: " + response.getC(i));
  }

    /** Say hello to server. */
    public void resta() {
      logger.info("Creando numeros");
      List<Integer> array_a = new ArrayList<Integer>();

      for (int i = 0; i < 5; i++) array_a.add(i);
  
      CalcRequest request = CalcRequest.newBuilder().addAllA(array_a).setB(2).build();
      
      CalcResponse response;
      try {
        response = blockingStub.restame(request);
      } catch (StatusRuntimeException e) {
        logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        return;
      }

      for (int i = 0; i < response.getCCount();i++)
        logger.info(i + " Resta: " + response.getC(i));
    }

  /**
   * Greet client. If provided, the first element of {@code args} is the name to use in the
   * greeting.
   */
  public static void main(String[] args) throws Exception {
    CalcClient client = new CalcClient("localhost", 50051);
    try {
      /* Access a service running on the local machine on port 50051 */
      client.suma();
      client.resta();
    } finally {
      client.shutdown();
    }
  }
}
