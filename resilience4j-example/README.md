# Resilience4j Example

## Features

- Java 19
- Springboot 3.0.4

## Description

This example is using [resilience4j](https://resilience4j.readme.io/) framework
with [Circuit breaker](https://resilience4j.readme.io/docs/circuitbreaker), [Retry](https://resilience4j.readme.io/docs/retry)
and [Rate limit](https://resilience4j.readme.io/docs/ratelimiter) examples.

## How to start

First clone the project:

```
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git 
```

and then, start the project locally, running the command:

```
$ cd resilience4j-example
$ make start
```

## How to use

After the Services start access [Actuator/health](http://localhost:8080/actuator/health) to see Circuit breaker metrics,
as below:

```json
{
  "status": "UP",
  "components": {
    "circuitBreakers": {
      "status": "UP",
      "details": {
        "serviceA": {
          "status": "UP",
          "details": {
            "failureRate": "0.0%",
            "failureRateThreshold": "50.0%",
            "slowCallRate": "0.0%",
            "slowCallRateThreshold": "100.0%",
            "bufferedCalls": 0,
            "slowCalls": 0,
            "slowFailedCalls": 0,
            "failedCalls": 0,
            "notPermittedCalls": 0,
            "state": "CLOSED"
          }
        }
      }
    }
  }
}
```

All the details properties are previouly configured at `application.yml` file. Check
the [documentation](https://resilience4j.readme.io/docs/getting-started-3#configuration) for more information.

## Circuit Breaker

To see the circuit breaker working it's necessary to stop `Service B` and run `curl -l 'http://localhost:8080/a'`. The
response is `This is a fallaback method from Service A`. It means `CircuitBreakerController.callBackMethod` method was
called.

After 5 retries, configured at `resilience4j.circuitbreaker.instances.serviceA.minimumNumberOfCalls` property, as
possible to see in application's log:

```
error CircuitBreaker 'serviceA' is OPEN and does not permit further calls
```

Which means the circuit is `OPEN` and not allowing for more calls. Check this blog post to know more
about [Circuit Break pattern](https://martinfowler.com/bliki/CircuitBreaker.html).

## Retry

To see the retry working its necessary to stop `Service B` and run `curl -l 'http://localhost:8080/a/retry'`. The
response is `fallback retry method called`. It means `RetryController.fallbackRetry` method was called, but it took 15
seconds to response. It´s because the method is configured to retry 3 times every 5 seconds. Those configuration are
defined on `application.yml` file as below:

```yaml
resilience4j:
  retry:
  instances:
  serviceA:
  maxAttempts: 3
  wait-duration: 5s
```

## Rate limit

To see rate limiter working both `ServiceA` and `ServiceB` applications must be UP, then
run `curl -l 'http://localhost:8080/a/rate-limit'`. It returns `Service B is called from Service A`, but you run it a
couple of times it will return `Rate limit achieved. Try again in 3 seconds`. Those configurations are set on `application.yml` as below:
```yaml
resilience4j:
  ratelimiter:
    instances:
      serviceA:
        registerHealthIndicator: false
        limitForPeriod: 10
        limitRefreshPeriod: 10s
        timeoutDuration: 3s
```
For more information about those configurations check the [documentation for rate limit](https://resilience4j.readme.io/docs/ratelimiter#create-and-configure-a-ratelimiter).