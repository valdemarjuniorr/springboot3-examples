# Redis Example

## Description

This project is an example of how to use Redis as a cache using [Lettuce](https://lettuce.io/) in a Spring Boot application.
We instantiate a Redis server and Redis Sentinel using Docker and using the [Spring Cache Abstraction](https://docs.spring.io/spring-framework/reference/integration/cache.html) to cache the results of a method.

## Features

- Java 22
- Spring Boot 3.2.4
- Spring Data Redis (Lettuce)
- Spring Cache

## How to start

Run the command:

```bash
$ make start
```

You can start the project with native image with the command:

```bash
$ make native-start
```

With Native Image, applications can run faster, use less memory, and be more secure as shown [here](https://github.com/valdemarjuniorr/spring-boot-graalvm-performance-comparation).

P.S: `serialization-config.json` is necessary to make the project works with Native Image.

## References

[Caching with Spring Boot 3, Lettuce, and Redis Sentinel](https://medium.com/javarevisited/caching-with-spring-boot-3-lettuce-and-redis-sentinel-5f6fab7e58f8)
