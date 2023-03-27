# Websocket example

- Java 19
- Springboot 3.0.5

## How to start

First clone the project:

```
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git 
```

and then, start the project locally, running the command:

```
$ cd websocket-example
$ make start
```

## How to use

After execute `make start` command, then open [http://localhost:8080](http://localhost:8080). It will open the page as
below:

![index.png](assets%2Findex.png)

The Connect button will connect to the backend towards [STOMP message protocol](https://stomp.github.io/). Then fill the
fields and hit send. It will send a message throw STOMP message protocol to the backend and send back to the frontend.

Pretty simple! Now, try to open more tabs at the same url, click in Connect button in all tabs, but fill the fields and
click send. All tabs will receive the message.  