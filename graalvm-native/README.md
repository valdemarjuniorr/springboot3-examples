# Springboot 3 native

## Requirements

- [httpie](https://httpie.io/docs/cli)
- [mvnd](https://github.com/apache/maven-mvnd)
- [GraalVM](https://www.graalvm.org/downloads/)
- [Buildpack](https://buildpacks.io/)
- Java 19

To download the project running:

```
$ curl https://start.spring.io/starter.tgz -o spring-boot-3-native.zip -d dependencies=web,actuator,devtools,native 
-d groupId=br.com.livelo -d artifactId=spring-boot-3-native -d packageName=br.com.livelo.native
-d applicationName=spring-boot-3-native -d name='Springboot 3 native example' -d description='Springboot 3 native example'
-d type=maven-project
```

Or generate
from [start.spring.io](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.0.5&packaging=jar&jvmVersion=17&groupId=br.com.livelo&artifactId=spring-boot-3-native&name=spring-boot-3-native&description=Spring%20boot%203%20native%20example&packageName=br.com.livelo.native&dependencies=actuator,web,devtools,native)
template.

## Commands

After download the project, run a command as below:

```
$ mvn clean package
```

In my case, it ran in:

```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.303 s
[INFO] Finished at: 2023-03-27T11:08:15-03:00
[INFO] ------------------------------------------------------------------------
```

After that, try the command as below:

```
$ mvnd clean package 
```

And it ran faster:

```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.384 s (Wall Clock)
[INFO] Finished at: 2023-03-27T11:08:29-03:00
[INFO] ------------------------------------------------------------------------
```

even faster when it runs `mvnd clean package -DskipTests`:

```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.408 s (Wall Clock)
[INFO] Finished at: 2023-05-07T10:37:55-03:00
[INFO] ------------------------------------------------------------------------
```

## Start up

Let's see how long does it take to startup. Run the command below:
`
mvn spring-boot:run
`
And the result was `1.737 seconds` as showed below:

```
INFO 8488 --- [  restartedMain] c.e.demo.SpringBoot3NativeApplication    : Started SpringBoot3NativeApplication in 1.737 seconds (process running for 2.055)
```

And check if the application is up and running:
`
$ http :8080/actuator/health
`

## Memory consumption

If we run the command `ps -aux | grep 8488`, as `8488` is the application's processor ID(PID):

```
PID     %CPU    %MEM
8488    0.2     2.0
```

## Buildpack

[Buildpack](https://buildpacks.io/) transform your application source code into images that can run on any
cloud. [Buildpack](https://buildpacks.io/) give a consistent way to create an image of your application. To do that run the command below:

`
$ mvn spring-boot:build-image
`
It will build
