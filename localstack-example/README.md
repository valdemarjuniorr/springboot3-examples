# Localstack example

To check the aws authentication order [here](https://docs.awspring.io/spring-cloud-aws/docs/3.0.0-RC1/reference/html/index.html#credentials.)

## Permition
[IAM Permition](https://docs.awspring.io/spring-cloud-aws/docs/3.0.0-RC1/reference/html/index.html#iam-permissions-4) necessary to send a notification to a topic.

Check if localstack has started by [localstack/health](http://localhost:4566/health)

Amazon SQS has a maximum message size of 256kb per message, so bigger messages will fail to be sent.