# Localstack example

## Features

- Java 21
- Springboot 3.2.1
- localstack
- docker-compose

## Description

A simple example using [localstack](https://localstack.cloud/) with [SNS](https://aws.amazon.com/pt/sns/)
and [SQS](https://aws.amazon.com/pt/sqs/) services enabled. There is a topic `notification-topic` and a
queue `notification-queue` configured which are used to send and receive messages.

The `notification-queue` is subscribed in the `notification-topic` as you can see in the `./scripts/init-aws.sh` file
and show as below:

![topic-queue.png](assets%2Ftopic-queue.png)

P.s: The `./scripts/init-aws.sh` script is executed when the `docker-compose` starts, and it has to have executed
permission:

```
$ chmod +x ./scripts/init-aws.sh
```

## How to start

First clone the project

```
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git
```

and then, start the project locally, running the command:

```
$ cd localstack-example
$ make start
```

## How to use

There are two endpoints to send and receive messages from the queue. To send a message to a topic `notification-topic`,
run the curl command:

```
curl --location 'http://localhost:8080/notify/topic' \
--header 'Content-Type: application/json' \
--data '{
    "from": "Valdemar Jr",
    "to": "Notification server",
    "content": "Content message"
}'
```
The notification will be sent to the topic and the queue will receive the message. The message structure received towards SNS is:
```json
{
  "Type": "Notification",
  "MessageId": "4c028028-72f2-4bb4-94b9-eb1dcffbd894",
  "TopicArn": "arn:aws:sns:us-east-1:000000000000:notification-topic",
  "Message": "{\"from\":\"Valdemar Jr\",\"to\":\"Notification server\",\"content\":\"Content message\"}",
  "Timestamp": "2023-04-18T00:21:52.986Z",
  "SignatureVersion": "1",
  "Signature": "EXAMPLEpH+..",
  "SigningCertURL": "https://sns.us-east-1.amazonaws.com/SimpleNotificationService-0000000000000000000000.pem",
  "UnsubscribeURL": "http://localhost:4566/?Action=Unsubscribe&SubscriptionArn=arn:aws:sns:us-east-1:000000000000:notification-topic:64c5a370-435b-4cd3-be4d-39b630f5f83b",
  "Subject": "notification",
  "MessageAttributes": {
    "NOTIFICATION_SUBJECT_HEADER": {
      "Type": "String",
      "Value": "notification"
    },
    "id": {
      "Type": "String",
      "Value": "e67d186f-651e-0054-8148-e535934baea8"
    },
    "contentType": {
      "Type": "String",
      "Value": "application/json"
    },
    "timestamp": {
      "Type": "Number.java.lang.Long",
      "Value": "1681777312978"
    }
  }
}
```

## Collections

To import all endpoints into [Postman](https://www.postman.com/) or [Insomnia](https://insomnia.rest/) check the
collection into `docs/collections.json` file.

## Some references

- To check the aws authentication
    order [here](https://docs.awspring.io/spring-cloud-aws/docs/3.1.0/reference/html/index.html#credentials).
- [IAM Permission](https://docs.awspring.io/spring-cloud-aws/docs/3.1.0/reference/html/index.html#iam-permissions-4)
  necessary to send a notification to a topic.
