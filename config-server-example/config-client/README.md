# Basic Spring Web Application

## Description

This is a project to demonstrate how to use a property loading from Spring Cloud Config Server. The property `api.chucknorris` is defined
in [general-configuration-server](https://github.com/valdemarjuniorr/general-configuration-server/) repository.

## Features

- Java 22
- Spring Boot 3.3.0-RC1

## How to start

Run the command:

```shell
$ make start
```

You can start the project with native image with the command:

```shell
$ make native-start
```

With Native Image, applications can run faster, use less memory, and be more secure as shown [here](https://github.com/valdemarjuniorr/spring-boot-graalvm-performance-comparation).

## How to use

After starting the application, you can use the following command to test the application:

```shell
$ curl http://localhost:8080/
```

It will make a `GET` method to the URL configured in `api.chucknorris` property. 
