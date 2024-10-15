# Custom Spring Boot Starter

## Description

This is a simple custom Spring Boot Starter example that provides an autoconfiguration service to call Chuck Norris API by default.
This feature is useful to create a custom starter that can be used in multiple projects with default configurations, for example, in your organization.

## Features

- Java 23
- Spring Boot 3.3.4

## How to start

It's necessary to generate the maven local dependency to use this starter. To do that, run the command

```shell
mvn clean install -U
```

If everything goes well, it will be possible to see a new dependency in the local repository in the folder below:

```shell
~/.m2/repository/br/com/valdemarjr/custom-spring-boot-starter
```

## How to use

Now that the dependency is installed, it's possible to use it in a Spring Boot project. To do that, add the dependency in the `pom.xml` file:

```xml

<dependency>
    <groupId>br.com.valdemarjr</groupId>
    <artifactId>custom-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

## References

- [How to create your own custom Spring Boot Starter](https://www.youtube.com/watch?v=9m1bC57oWrc)
- [Starters](https://docs.spring.io/spring-boot/reference/using/build-systems.html#using.build-systems.starters)
- [Creating Your Own Starter](https://docs.spring.io/spring-boot/reference/features/developing-auto-configuration.html#features.developing-auto-configuration.custom-starter)
