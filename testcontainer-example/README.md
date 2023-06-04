# Testcontainers Example

## Features

- Springboot 3.1
- Java 17
- Postgres
- docker-compose
- Mockito 5

## Description

In this example, I am using one of the new features of Springboot 3 which is
[docker-compose integration](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.1-Release-Notes#testcontainers), [Mockito 5](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.1-Release-Notes#mockito-5)
and [Testcontainers](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.1-Release-Notes#testcontainers).

### Docker-compose integration

When your app is starting up, the Docker Compose integration will look for a configuration file in the current working directory. The following files are supported:

- `docker-compose.yml` (case for this example)
- `compose.yaml`
- `compose.yml`
- `docker-compose.yaml`

### Testcontainers at development time

In this [Springboot 3.1 version](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.1-Release-Notes) was add a container support for development time.
A new maven goal `spring-boot:test-run` can be used to launch an application through a test main method when using [Testcontainers at development time](https://docs.spring.io/spring-boot/docs/3.1.0/reference/html/features.html#features.testing.testcontainers.at-development-time).

## How to start

First clone the project

```
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git
```

and then, start the project locally, running the command:

```
$ cd testcontainers-example
$ make start
```

## How to use

## Collections

## References

- [Spring Tips: go fast with Spring Boot 3.1](https://www.youtube.com/watch?v=ykEK2xuJrN8)
