# Open tracing
- docker-compose up -d
- add pom
- add application.yml
- add folder interceptor
- add tag
  ```
  Span currentSpan = tracer.activeSpan();
      currentSpan.setTag("userId", "1023517767");
  ```
- add new child span 
  ```txt
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
  ```
- add grpc_client
  - add TracingClientInterceptor n


# Metrics
- docker-compose up -d in project metrics_self_learn.
- add pom prometheus and actuator, and AOP to using timed
- https://micrometer.io/docs/concepts#_the_timed_annotation
  - ```
    @Configuration
    @EnableAspectJAutoProxy
    public class MonitorAutoTimingConfig {

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
    return new TimedAspect(registry);
    }
    }
    ```