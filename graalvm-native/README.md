# Springboot 3 native

## Prequirements

- [mvnd](https://github.com/apache/maven-mvnd)
- [GraalVM](https://www.graalvm.org/downloads/)
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
[INFO] Total time:  11.603 s
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
[INFO] Total time:  8.393 s (Wall Clock)
[INFO] Finished at: 2023-03-27T11:08:29-03:00
[INFO] ------------------------------------------------------------------------
```



