package io.norberg.netty_openssl_test;

import com.mastfrog.netty.http.client.HttpClient;
import com.mastfrog.netty.http.client.ResponseFuture;
import com.mastfrog.netty.http.client.ResponseHandler;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;

public class NettyOpenSslTest {

  public static void main(final String... args)
      throws ExecutionException, InterruptedException, IOException {

    final HttpClient client = HttpClient.builder().followRedirects().build();

    final ResponseFuture h = client.get().setURL("https://www.google.com/").execute(
        new ResponseHandler<String>(String.class) {
          protected void receive(HttpResponseStatus status, HttpHeaders headers, String response) {
            System.out.println(response);
          }
        });
  }
}
