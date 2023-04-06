#!/bin/bash
awslocal sns create-topic --name notification-topic

awslocal sqs create-queue --queue-name notification-queue

awslocal sns subscribe --topic-arn "arn:aws:sns:us-east-1:000000000000:notification-topic" --protocol sqs --notification-endpoint "http://localhost:4566/000000000000/notification-queue"