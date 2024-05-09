# Config Server Example

## Description

This is a project to demonstrate how to use the [Spring Cloud Config Server](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/).
The general configuration is centralized in [general-configuration-server](https://github.com/valdemarjuniorr/general-configuration-server/) repository and exposed by Spring Actuator.

## Features
- Java 22
- Spring Boot 3.3.0-RC1
- Spring Cloud 2023.0.1

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
After starting the application, you can see the configuration by accessing the URL:

```shell
$ curl http://localhost:8888/actuator/default
```

It will show the configuration properties in JSON format as below:

```json
{
  "name": "actuator",
  "profiles": [
    "default"
  ],
  "label": null,
  "version": "59460e0bda08bff3bd2da2099c01e70bca3af8d3",
  "state": null,
  "propertySources": [
    {
      "name": "https://github.com/valdemarjuniorr/general-configuration-server/application.yml",
      "source": {
        "api.chucknorris": "https://api.chucknorris.io/"
      }
    }
  ]
}
```
The property `api.chucknorris` is the configuration centralized in [general-configuration-server](https://github.com/valdemarjuniorr/general-configuration-server/) repository.