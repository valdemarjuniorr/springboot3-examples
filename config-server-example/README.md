# Config Server Example

## Description

This is a project to demonstrate how to use the [Spring Cloud Config Server](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/).
The general configuration is centralized in [general-configuration-server](https://github.com/valdemarjuniorr/general-configuration-server/) repository and exposed by Spring Actuator.

In this example there are two ways to get configuration values from the Config Server:
 - [Environment](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/env/Environment.html) class, as we can see in [Controller](./config-client/src/main/java/br/com/valdemarjr/web/controllers/Controller.java)
 - [Value](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Value.html) annotation, as we can see in [RefreshScopeController](./config-client/src/main/java/br/com/valdemarjr/web/controllers/RefreshScopeController.java)
In this case, the [RefreshScope](https://docs.spring.io/spring-cloud-commons/docs/current/api/org/springframework/cloud/context/refresh/RefreshScope.html) annotation is necessary to refresh the configuration values at runtime.

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
        "api.chucknorris": "https://api.chucknorris.io/jokes/random"
      }
    }
  ]
}
```
In case you want to refresh the configuration of all services which are connected to the Config Server, you can use the following command:

```shell
$ curl --request POST 'http://localhost:8888/actuator/busrefresh' \
--header 'Content-Type: application/json'
```

## References
- [Refreshable configuration with Spring Cloud Bus, and the Spring Cloud Config Monitor](https://youtu.be/aC_siBP8rx8?si=bDz97eGOGeLPVnH2)