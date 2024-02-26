# Spring CLI example

## Description

This is a simple example of a Spring Boot application that uses the Spring CLI
to create projects from existing templates. To install the Spring CLI you can
run the command below:

```bash
git clone https://github.com/spring-projects/spring-cli $HOME/temp/spring-cli

cd $HOME/temp/spring-cli

./gradlew clean build -x test
```

and then, to make it available in your system, create an alias like this:

```bash
alias spring='java -jar $HOME/temp/spring-cli/build/libs/spring-cli-0.8.1.jar'
```

## Project Structure

First of you need to create a project catalog configuration file
named `project-catalog.yml`. This file contains the list of available templates
and their respective repositories. Here is an example of a `project-catalog.yml`
file:

```yaml
---
project-repositories:
  - name: "web"
    description: "RESTful service example"
    url: "https://github.com/valdemarjuniorr/project-catalog-examples/tree/main/web"
    tags:
      - "java-21"
      - "boot-3.2.x"
      - "rest"
      - "web"
  - name: "test-containers"
    description: "A TestContainers example"
    url: "https://github.com/valdemarjuniorr/project-catalog-examples/tree/main/test-container"
    tags:
      - "java-21"
      - "boot-3.2.x"
      - "test-containers"
      - "docker"
```
Now it is necessary to add the `project-catalog.yml` file to the Spring CLI project-catalog list to make it available to create new projects.
To do this, run the following command:

```bash
$ spring project-catalog add examples https://github.com/valdemarjuniorr/project-catalog-examples/
```

To check if the project catalog `examples` was added, run the following command:

```bash
$ spring project-catalog list
```
You will see the following output:

```
┌────────┬───────────┬───────────────────────────────────────────────────────────┬────┐
│Name    │Description│URL                                                        │Tags│
├────────┼───────────┼───────────────────────────────────────────────────────────┼────┤
│examples│           │https://github.com/valdemarjuniorr/project-catalog-examples│[]  │
└────────┴───────────┴───────────────────────────────────────────────────────────┴────┘
```
When you run `spring project list` you will see the list of available projects in the catalog `examples`:

```
┌───────────────┬────────────────────────┬────────────────────────────────────────────────────────────────────────────────────┬────────┬──────────────────────────────────────────────┐
│Name           │Description             │URL                                                                                 │Catalog │Tags                                          │
├───────────────┼────────────────────────┼────────────────────────────────────────────────────────────────────────────────────┼────────┼──────────────────────────────────────────────┤
│web            │RESTful service example │https://github.com/valdemarjuniorr/project-catalog-examples/tree/main/web           │examples│[java-21, boot-3.2.x, rest, web]              │
├───────────────┼────────────────────────┼────────────────────────────────────────────────────────────────────────────────────┼────────┼──────────────────────────────────────────────┤
│test-containers│A TestContainers example│https://github.com/valdemarjuniorr/project-catalog-examples/tree/main/test-container│examples│[java-21, boot-3.2.x, test-containers, docker]│
└───────────────┴────────────────────────┴────────────────────────────────────────────────────────────────────────────────────┴────────┴──────────────────────────────────────────────┘
```

### Create a new project
After adding those project templates, you will be able to create a new project. To do this, run the following command, for example to use `web` template:

```bash
spring boot new <PROJECT_NAME> web
```

### Adding a template to a new project
If you have an existing project, and you want to add a new template to it, you can run the following command:

```bash
spring boot add test-containers
```
It will add the `test-containers` template to the existing project.

### Conventions

https://docs.spring.io/spring-cli/reference/boot-add-guide.html#_conventions

## References
- [Project Catalog Example](https://github.com/valdemarjuniorr/project-catalog-examples)
