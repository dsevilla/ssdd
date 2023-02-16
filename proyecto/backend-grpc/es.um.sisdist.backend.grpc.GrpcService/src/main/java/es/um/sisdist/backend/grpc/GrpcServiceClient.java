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

package es.um.sisdist.backend.grpc;

/*
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
*/

/**
 * A simple client that requests a greeting from the {@link CollageServer}.
 */
public class GrpcServiceClient 
{
	/*
  private static final Logger logger = Logger.getLogger(GrpcServiceClient.class.getName());

  private final ManagedChannel channel;
  private final GrpcServiceGrpc.GrpcServiceBlockingStub blockingStub;
  private final GrpcServiceGrpc.GrpcServiceStub asyncStub;
  
  // Construct client connecting to HelloWorld server at {@code host:port}. 
  public GrpcServiceClient(String host, int port) 
  {
    channel = ManagedChannelBuilder.forAddress(host, port)
        // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
        // needing certificates.
        .usePlaintext()
        .build();
    blockingStub = GrpcServiceGrpc.newBlockingStub(channel);
    asyncStub = GrpcServiceGrpc.newStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }
  
  // Send images. 
  public void sendImagesAndGetCollage()
  {
	  // Imágenes para enviar
	  ImageData image1 = ImageData.newBuilder().setId("imagen1")
			  .setData(ByteString.copyFrom("Imagen 1 data".getBytes())).build();	  
	  ImageData image2 = ImageData.newBuilder().setId("imagen2")
			  .setData(ByteString.copyFrom("Imagen 2 data".getBytes())).build();
	  
	  try {
		  blockingStub.storeImage(image1);		  
		  blockingStub.storeImage(image2);
	  } catch (StatusRuntimeException e) {
		  logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
		  return;
	  }

	  // Stream
	  try {
		  final CountDownLatch finishLatch = new CountDownLatch(1);
		  
		  StreamObserver<Empty> soEmpty = new StreamObserver<Empty>() {

			  @Override
			  public void onNext(Empty value) {
			  }

			  @Override
			  public void onError(Throwable t) {
				  finishLatch.countDown();
			  }

			  @Override
			  public void onCompleted() {
				  finishLatch.countDown();
			  }
		  };
		  
		  StreamObserver<ImageData> so = asyncStub.storeImages(soEmpty);
		  so.onNext(image1);
		  so.onNext(image2);
		  so.onCompleted();
		  
		  // Esperar la respuesta
		  if (finishLatch.await(1, TimeUnit.SECONDS))
			  logger.info("Received response.");
		  else
			  logger.info("Not received response!");
		  
	  } catch (StatusRuntimeException e) {
		  logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
		  return;
	  } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }

  // * Collage client
  public static void main(String[] args) throws Exception {
    GrpcServiceClient client = 
      new GrpcServiceClient(args.length == 0 ? "localhost" : args[0],
                        50051);
    try {
      // Access a service running on the local machine on port 50051 
      client.sendImagesAndGetCollage();
    } finally {
      client.shutdown();
    }
  }
  */
}
