# [Draft] Spring Batch example

## Features
- Java 17
- postgresql
- Spring Boot 3.1.3

## Description
Spring Batch is a lightweight, comprehensive batch framework designed to enable the development of robust batch applications vital for the daily operations of enterprise systems.
There are some concepts that you need to understand before you start working with Spring Batch:

- `Job`: A job is an entity that encapsulates an entire batch process. It consists of multiple steps, and each step typically has a single reader, processor, and writer.
- `JobLauncher`: The JobLauncher is the interface for running a job. It provides a simple interface for executing a job with a set of parameters.
- `Step`: A step is an entity that encapsulates a single phase of a batch job. It consists of a reader, a processor, and a writer.

The following diagram is a simplified version of the batch reference architecture that has been used for decades

![reference-model.png](assets%2Freference-model.png)

Those concepts are important also:

- `ItemReader`: The ItemReader is the interface that defines the method that will be called to read the next item. It is important to note that the return value of the read method is the actual item that will be passed to the processor.
- `ItemProcessor`: The ItemProcessor is the interface that defines the method that will be called to process the next item. It is important to note that the return value of the process method is the actual item that will be passed to the writer.
- `ItemWriter`: The ItemWriter is the interface that defines the method that will be called to write a list of items. It is important to note that the write method is called only once per chunk, and the list of items passed to the write method is the actual list of items that were processed by the processor.

## How to start

First clone the project

```
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git
```

and then, start the project locally, running the command:

```
$ cd spring-batch-example
$ make start
```

## How to use

## References
- [Spring Batch Reference Guide](https://docs.spring.io/spring-batch/docs/current/reference/html/index-single.html#spring-batch-intro)
- [Data used for this project](https://www.kaggle.com/datasets/niramay/-coffeemakerclassification?resource=download)
