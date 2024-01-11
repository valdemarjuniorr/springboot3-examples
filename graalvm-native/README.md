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
-d groupId=br.com.valdemarjr -d artifactId=spring-boot-3-native -d packageName=br.com.valdemarjr.native
-d applicationName=spring-boot-3-native -d name='Springboot 3 native example' -d description='Springboot 3 native example'
-d type=maven-project
```

Or generate
from [start.spring.io](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.0.5&packaging=jar&jvmVersion=17&groupId=br.com.valdemarjr&artifactId=spring-boot-3-native&name=spring-boot-3-native&description=Spring%20boot%203%20native%20example&packageName=br.com.valdemarjr.native&dependencies=actuator,web,devtools,native)
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

### Docker image
Running the command `docker image ls -a` it will show the docker image's size:
```
REPOSITORY                   TAG              IMAGE ID       CREATED        SIZE
graalvm-native               0.0.1-SNAPSHOT   77fd4ad52589   43 years ago   281MB
```
And in the `/target` folder generate a `graalvm-native-0.0.1-SNAPSHOT.jar` file size about 209MB.

## GraalVM AOT
Let's run the Native Build command:

`
$ mvn -Pnative native:compile
`
It took `02:36 min`:
```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  02:36 min
[INFO] Finished at: 2023-05-07T12:40:27-03:00
[INFO] ------------------------------------------------------------------------
```

Now let's start the application:

`
$ ./target/graalvm-native
`
And it took 0.101 seconds:

```
INFO 14807 --- [main] c.e.demo.SpringBoot3NativeApplication    : Started SpringBoot3NativeApplication in 0.101 seconds (process running for 0.106)
```

Now let's generate our application using buildpacks, running `mvn -Pnative spring-boot:native-image`. It took:
```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  04:51 min
[INFO] Finished at: 2023-05-14T12:33:14-03:00
[INFO] ------------------------------------------------------------------------
```
but, What I am going to do with this image? You are going to send to a pipeline. As mentioned in the presentation:

> The paved path to production starts at your laptop.

After the build finishes, we are able to start your application inside the container:

```
$ docker run -p 8080:8080 graalvm-native:0.0.1-SNAPSHOT
```
And the started up in `0.083 seconds`:

```
INFO 1 --- [main] c.e.demo.SpringBoot3NativeApplication    : Started SpringBoot3NativeApplication in 0.083 seconds (process running for 0.09)
```
and let's check the container image size this time throw `docker image ls -a` command:

```
REPOSITORY                   TAG              IMAGE ID       CREATED        SIZE
graalvm-native               0.0.1-SNAPSHOT   81bd596784bd   43 years ago   97.4MB
```

What is the size difference which as removed? That is the VM, the runtime layer.
The big change about the size is sending this image around the globe, removing this size make such a big difference.

## References
- [Introducing Spring Boot 3.0](https://www.youtube.com/watch?v=H6HwoWZtngs)

## Troubleshooting
- [exec: "gcc": executable file not found in %PATH% when trying go build](https://stackoverflow.com/questions/43580131/exec-gcc-executable-file-not-found-in-path-when-trying-go-build)
- [java.lang.RuntimeException: There was an error linking the native image: Linker command exited with 1](https://simply-how.com/fix-graalvm-native-image-compilation-issues)
