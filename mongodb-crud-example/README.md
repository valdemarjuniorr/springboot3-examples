# MongoDB CRUD Example

## Features

- Java 19
- docker-compose
- Springboot 3.0.3
- MongoBD

## Description

A simple CRUD REST implementation with MongoDB where it is possible to create users and hobbies.

## How to start

First clone the project

```
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git 
```

and then, start the project locally, running the command:

```
$ cd mongodb-crud-example
$ make start
```

## How to use

To create a new user run the `curl`:

```
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Valdemar Jr",
    "age": 41,
    "hobbies": [
        {
            "name": "Dancing",
            "frequency": 2
        },
        {
            "name": "Cycling",
            "frequency": 3
        }
    ]
}'
```
and then the `curl` to get all users:
```
curl --location 'http://localhost:8080/users'
```

## Collections
To import all endpoints into [Postman](https://www.postman.com/) or [Insomnia](https://insomnia.rest/) check the collection into `docs/collections.json` file.