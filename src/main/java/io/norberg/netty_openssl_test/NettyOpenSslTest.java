package io.norberg.netty_openssl_test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;

public class NettyOpenSslTest {

  public static void main(final String... args)
      throws ExecutionException, InterruptedException, IOException {

    final Vertx vertx = Vertx.vertx();

    final HttpClient client = vertx.createHttpClient(
        new HttpClientOptions().setSsl(true));

    final CompletableFuture<Void> done = new CompletableFuture<>();


    final HttpClientRequest request = client.getAbs("https://www.google.com:443/");
    request.handler(event -> {
      System.out.println(event.statusCode() + " " + event.statusMessage());
      event.bodyHandler(buf -> {
        final String body = buf.toString("UTF-8");
        System.out.println(body);
        done.complete(null);
      });
    });

    request.end();

    done.get();

    client.close();
    vertx.close();
  }
}
