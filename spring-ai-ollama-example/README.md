# Spring AI example

## Description

In this example, we are going to try [Spring AI](https://docs.spring.io/spring-ai/reference/) to create a simple application using Ollama Chat. We are
going to interact with [Large Language Model(LLM)](https://aws.amazon.com/what-is/large-language-model/) and gerenate text from it.

To learn more about some concepts, you can check the [references](#references) section.

## Features
- Java 21
- Spring Boot 3.2.2
- [Spring AI](https://docs.spring.io/spring-ai/reference/) 0.8.0
- [Ollama](https://ollama.com/)

## How to start

First clone the project

```sh
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git
```

and then, start the project locally using `local` profile, running the command:

```sh
$ cd spring-ai-ollama-example
$ make start
```

PS.: It might take a while to start, because it is going to download `llama2` model from the internet.

And then, you can run the `curl` command to ask a question to the model:

```sh
curl --location 'http://localhost:8080/ask' \
--header 'Content-Type: application/json' \
--data '{
    "question": "Tell me a joke?"
}'
```

The response should be something like:

```json
{
  "response": "Why was the math book sad? Because it had too many problems!"
}
```

## Collections

To import all endpoints into [Postman](https://www.postman.com/) or [Insomnia](https://insomnia.rest/) check the
collection into `docs/collections.json` file.

## References
- [Ollama Chat](https://docs.spring.io/spring-ai/reference/api/clients/ollama-chat.html)
