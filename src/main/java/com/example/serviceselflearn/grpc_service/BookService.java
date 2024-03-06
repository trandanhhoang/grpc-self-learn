package com.example.serviceselflearn.grpc_service;

import book.v1.protobuf.Book;
import book.v1.protobuf.BookList;
import book.v1.protobuf.BookRequest;
import book.v1.protobuf.BookServiceGrpc;
import io.grpc.stub.StreamObserver;
import io.micrometer.core.annotation.Timed;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

import static com.example.serviceselflearn.MonitorUtils.GRPC_SERVER_METRIC;

// gRPC Server started, listening on port 9095.
@GRpcService
@RequiredArgsConstructor
public class BookService extends BookServiceGrpc.BookServiceImplBase {

    private final Tracer tracer;

    private final BookServiceGrpc.BookServiceBlockingStub bookService;

    @Override
    @Timed(GRPC_SERVER_METRIC)
    public void getBooks(BookRequest request, StreamObserver<BookList> responseObserver) {
        Span currentSpan = tracer.activeSpan();
        currentSpan.setTag("userId", "1023517767");
        Span parentSpan = tracer.scopeManager().activeSpan();
        // Start a new child span
        Span childSpan = tracer.buildSpan("ChildSpanOperation")
                .asChildOf(parentSpan)
                .start();
        try (Scope ignored = tracer.activateSpan(childSpan)) {
            childSpan.log("YUHU");
            childSpan.setTag("child userId", "123456789");
            BookList bookList = BookList.newBuilder()
                    .addBooks(Book.newBuilder().setId("1").setName("Book 1").build())
                    .addBooks(Book.newBuilder().setId("2").setName("Book 2").build())
                    .build();

            bookService.getBooks2(request);

            responseObserver.onNext(bookList);
            responseObserver.onCompleted();
        } finally {
            childSpan.finish();
        }
    }

    @Override
    public void getBooks2(BookRequest request, StreamObserver<BookList> responseObserver) {
        Span currentSpan = tracer.activeSpan();
        currentSpan.setTag("userId", "1023517767");
        Span parentSpan = tracer.scopeManager().activeSpan();
        // Start a new child span
        Span childSpan = tracer.buildSpan("ChildSpanOperation")
                .asChildOf(parentSpan)
                .start();
//        SpanContext parentContext = tracer.extract(Format.Builtin.TEXT_MAP, new HeadersMapExtractAdapter(consumerRecord.headers()));
//        Span span = tracer.buildSpan(this.getClass().getSimpleName()).asChildOf(parentContext).start();
        try (Scope ignored = tracer.activateSpan(childSpan)) {
            childSpan.log("YUHU");
            childSpan.setTag("child userId", "123456789");
            BookList bookList = BookList.newBuilder()
                    .addBooks(Book.newBuilder().setId("1").setName("Book 1").build())
                    .addBooks(Book.newBuilder().setId("2").setName("Book 2").build())
                    .build();

            responseObserver.onNext(bookList);
            responseObserver.onCompleted();
        } finally {
            childSpan.finish();
        }
    }
}
