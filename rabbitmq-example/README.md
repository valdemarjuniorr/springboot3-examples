# RabbitMQ fanout example

## Features

- Java 21
- docker-compose
- Springboot 3.2.2
- RabbitMQ 3.11.9

## Description

This example uses [fanout pattern](https://en.wikipedia.org/wiki/Fan-out_(software)) which it means a message will
send(produce) to a topic(exchange) and received by two different interessed in the subject(consumers).

![diagram.png](assets%2Fdiagram.png)

## How to start

First clone the project:

```
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git
```

and then, start the project locally, running the command:

```
make start
```

It will start the RabbitMQ in container and the application.

## How to use

To send a message run `curl` below:

```
curl --location 'http://localhost:8080/publish' \
--header 'Content-Type: application/json' \
--data '{
    "content": "message sent by post request"
}'
```

In application's log you will see that the message was published in `notify.new.user` exchange and consumed
by `NotifyCompanyConsumer` and `NotifyUserConsumer` consumers, logging respectively:
- `A company received a new user message. Message -> message sent by post request`.
- `New user received a new message. Message -> message sent by post request`.
