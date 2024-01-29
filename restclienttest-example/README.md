# Rest Client Test Annotation

## Features

- Annotation for Rest Client Test
- Java 21
- Spring Boot 3.2.2

## Description

This project is a simple example of how to use the `@RestClientTest` annotation
to test a Rest Client or Rest Template calls. As mentioned in
the [Spring Boot documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing.spring-boot-applications.autoconfigured-rest-client),
the `@RestClientTest` annotation can be used in combination with `@AutoConfigureMockRestServiceServer` and `AutoConfigureWebClient` to test `RestTemplate` and `RestClient` beans.

The application is integrated with [chucknorris.io](https://api.chucknorris.io/jokes/random) API, which returns a random joke about Chuck Norris.

## How to start

First clone the project

```sh
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git
```

and then, start the project locally using `local` profile, running the command:

```sh
$ cd restclienttest-example
$ make start
```

## References

- [Spring Boot Rest Client - How to test HTTP calls using @RestClientTest](https://youtu.be/jhhi03AIin4?si=e6bXI92LFk9xeDjK)
