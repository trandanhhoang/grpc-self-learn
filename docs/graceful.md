- Problem:
  - grpc server don't gracefully shutdown, it mean when request is processing and server got SIGTERM, request will not
  be handle successfully

- How to test:
  - Thead.sleep() in any api handler to make it become a long task.
  - Call this api, then send SIGTERM to server.
    - Without graceful set up: Thread will be killed intermediately.
    - With graceful set up: Thread will be handle until time config 'shutdownGrace' end before be killed.
- Change:
  - Set up in yml file.
    ``` seconds
    grpc:
      shutdownGrace: 30
    ```
  - Apply in Version 3.5.1 and above: (https://github.com/LogNet/grpc-spring-boot-starter/blob/master/ReleaseNotes
  .md#version-351)
    - Need update version of spring-boot, spring-boot-starter, grpc like link below
      - https://github.com/LogNet/grpc-spring-boot-starter/blob/master/ReleaseNotes.md
    - Need update prometheus because spring-boot updated.
      - https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies/2.3.3.RELEASE