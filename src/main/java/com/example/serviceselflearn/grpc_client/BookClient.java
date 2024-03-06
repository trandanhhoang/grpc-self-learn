package com.example.serviceselflearn.grpc_client;

import book.v1.protobuf.BookServiceGrpc;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.opentracing.contrib.grpc.TracingClientInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BookClient {
    // For Client call to server.
    private final TracingClientInterceptor tracingClientInterceptor;

    @Bean
    public Channel initChannel() {
        return tracingClientInterceptor.intercept(
                ManagedChannelBuilder.forAddress("localhost", 9095).usePlaintext().build()
        );
    }


    @Bean
    public BookServiceGrpc.BookServiceBlockingStub initBookServiceBlockingStub(Channel channel) {
        return BookServiceGrpc.newBlockingStub(channel);
    }

}

