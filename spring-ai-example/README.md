# Spring AI example

## Description

In this example, we are going to try [Spring AI](https://docs.spring.io/spring-ai/reference/) to create a simple application that will
use [Retrieval Augmented Generation (RAG)](https://blogs.nvidia.com/blog/what-is-retrieval-augmented-generation/) which we're going to interact with [Large Language Model(LLM)](https://aws.amazon.com/what-is/large-language-model/) and train it to answer questions.

To learn more about some concepts, you can check the [references](#references) section.

## Features
- Java 21
- Spring Boot 3.2.2
- [Spring AI](https://docs.spring.io/spring-ai/reference/) 0.8.0
- [PostgreSQL pgVector](https://github.com/pgvector/pgvector)

## How to start

First clone the project

```sh
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git
```

and then, start the project locally using `local` profile, running the command:

```sh
$ cd spring-ai-example
$ make start
```

## How to use

### Account setup
First, create an [OpenAI account](https://platform.openai.com/signup) or [sign in](https://platform.openai.com/login). Next, navigate to the [API key page](https://platform.openai.com/account/api-keys) and `Create new secret key`, optionally naming the key.
Make sure to save this somewhere safe and do not share it with anyone.

### Environment variables
Now, you need to set the environment variables `OPENAI_API_KEY` with the API KEY created in the previous step. For example:

```sh
$ export OPENAI_API_KEY=<OPENAI_API_KEY>
```

After that, you can run the application using the command:

```sh
$ make start
```

And then, you can run the `curl` command to ask a question to the model:

```sh
curl --location 'http://localhost:8080/ask' \
--header 'Content-Type: application/json' \
--data '{
    "question": "what should I know about the transition to consumer direct care network washington?"
}'
```

The response should be something like:

```json
{
  "response": "The transition to Consumer Direct Care Network in Washington involves a shift in how home care aides (caregivers) are paid and managed. Consumer Direct Care Network is a fiscal intermediary that handles payroll and benefits for caregivers providing services to Medicaid in-home care clients. This transition aims to streamline the payment process and improve overall efficiency in managing home care services for both caregivers and clients."
}
```

## Collections

To import all endpoints into [Postman](https://www.postman.com/) or [Insomnia](https://insomnia.rest/) check the
collection into `docs/collections.json` file.

## References
- [Chat Completion API](https://docs.spring.io/spring-ai/reference/api/chatclient.html)
- [Github project reference](https://github.com/spring-tips/llm-rag-with-spring-ai/tree/main)
- [Spring Tips: Spring AI](https://youtu.be/aNKDoiOUo9M?si=YhPLR82r8_Sv4Rfq)
