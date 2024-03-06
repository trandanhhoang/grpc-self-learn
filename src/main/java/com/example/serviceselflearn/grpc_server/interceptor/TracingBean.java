package com.example.serviceselflearn.grpc_server.interceptor;

import io.opentracing.Tracer;
import io.opentracing.contrib.grpc.TracingClientInterceptor;
import io.opentracing.contrib.grpc.TracingServerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TracingBean {
    private final Tracer tracer;

    @Bean
    public TracingClientInterceptor tracingClientInterceptor() {
        TracingClientInterceptor.Builder builder = TracingClientInterceptor
                .newBuilder()
                .withTracer(tracer)
                .withStreaming()
                .withTracedAttributes(TracingClientInterceptor.ClientRequestAttribute.ALL_CALL_OPTIONS,
                        TracingClientInterceptor.ClientRequestAttribute.HEADERS);

        return builder.build();
    }

    @Bean
    public TracingServerInterceptor tracingServerInterceptor() {
        TracingServerInterceptor.Builder builder = TracingServerInterceptor
                .newBuilder()
                .withTracer(tracer)
                .withStreaming()
                .withTracedAttributes(TracingServerInterceptor.ServerRequestAttribute.CALL_ATTRIBUTES,
                        TracingServerInterceptor.ServerRequestAttribute.HEADERS);
        return builder.build();
    }
}
