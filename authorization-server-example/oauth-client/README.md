# Oauth Client Example

## Description

This project contains a web service that will accept `HTTP GET` requests at http://localhost:8082/products.
It will respond with a JSON representation of a products, as the following listing shows:

## Requirements
All projects below must be running:
- authorization-server
- resource-server

## Features
- Java 21
- Spring Boot 3.2.3

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

## How to use
After starting the application, you can use the following command to test the application:

```bash
$ curl http://localhost:8082/products
```
