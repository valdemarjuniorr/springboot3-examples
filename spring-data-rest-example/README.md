# Springboot Data Rest example

This example is about [Spring Data Rest](https://docs.spring.io/spring-data/rest/docs/current/reference/html/) which it
is an application that accesses relational JPA data througha [hypermedia-based RESTful](https://spring.io/guides/gs/rest-hateoas) front end.
Spring Data REST takes the features of [Spring HATEOAS](https://spring.io/projects/spring-hateoas) and [Spring Data JPA](https://spring.io/projects/spring-data-jpa) and automatically combines them together.

## Features

- Java 21
- Springboot 3.2.2

## Description

In this example we have a relational in memory database as below:

![data-base.png](assets%2Fdata-base.png)

There are a `ProductRepository` which all operation will be available at `/products` endpoint. The path is derived from
the uncapitalized, pluralized, simple class name of the domain class being managed. It also exposes an item resource for
each of the items managed by the repository under the URI template `/products/{id}`.

Resource discovery starts at the top level of the application. By issuing a request to the root URL under which the
Spring Data REST application is deployed, the client can extract, from the returned JSON object, a set of links that
represent the next level of resources that are available to the client.

For example, to discover what resources are available at the root of the application, issue an HTTP GET to the root URL,
as follows:

```json
{
  "_embedded": {
    "products": [
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/products"
    },
    "profile": {
      "href": "http://localhost:8080/api/profile/products"
    }
  },
  "page": {
    "size": 20,
    "totalElements": 0,
    "totalPages": 0,
    "number": 0
  }
}
```

## How to start

First clone the project

```
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git
```

and then, start the project locally, running the command:

```
$ cd spring-data-rest-example
$ make start
```

## HAL Explorer
Because of the `spring-data-rest-hal-explorer` dependency is enabling the HAL Explorer in [localhost:8080](http://localhost:8080/api) to help with requests and responses, as below:

![hal-explorer.png](assets%2Fhal-explorer.png)

**P.s.**: I don't make relational operation works. For example, create a `Product` related to `Department`.

Check the [blog post](https://www.baeldung.com/spring-rest-hal) about the HAL Explorer.

## Collections

To import all endpoints into [Postman](https://www.postman.com/) or [Insomnia](https://insomnia.rest/) check the
collection into `docs/collections.json` file.

## References
For more details about the profile link, see [Application-Level Profile Semantics (ALPS)](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#metadata.alps).
