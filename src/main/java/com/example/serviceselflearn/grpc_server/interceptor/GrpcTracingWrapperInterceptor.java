package com.example.serviceselflearn.grpc_server.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.opentracing.contrib.grpc.TracingServerInterceptor;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcGlobalInterceptor;

@GRpcGlobalInterceptor
@RequiredArgsConstructor
//@Order(4)
public class GrpcTracingWrapperInterceptor implements ServerInterceptor {

  private final TracingServerInterceptor tracingServerInterceptor;

  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
    return tracingServerInterceptor.interceptCall(call, headers, next);
  }

}
