# Observability Example

- Java 21
- Springboot 3.2.2
- Prometheus

## How to start

First clone the project:

```
$ git clone git@github.com:valdemarjuniorr/springboot3-examples.git
```

and then, start the project locally, running the command:

```
$ cd observability-example
$ make start
```

## How to use

After execute `make start` command, then run:

```
curl -l 'http://localhost:8080/manual'
```

if it runs correctly, it will create a metric called `manual` and it can be checked on prometheus metrics
towards [/actuator/metrics](http://localhost:8080/actuator/metrics) url
or directly towards [/actuator/metrics/manual](http://localhost:8080/actuator/metrics/manual) url. The response is:

```json
{
  "name": "manual",
  "baseUnit": "seconds",
  "measurements": [
    {
      "statistic": "COUNT",
      "value": 1.0
    },
    {
      "statistic": "TOTAL_TIME",
      "value": 0.0138677
    },
    {
      "statistic": "MAX",
      "value": 0.0
    }
  ],
  "availableTags": [
    {
      "tag": "error",
      "values": [
        "none"
      ]
    }
  ]
}
```

In the application's log has some entries like:

```
: onStart called
: START - name='manual', contextualName='null', error='null', lowCardinalityKeyValues=[], highCardinalityKeyValues=[], map=[], parentObservation={name=http.server.requests(null), error=null, context=name='http.server.requests', contextualName='null', error='null', lowCardinalityKeyValues=[exception='none', method='GET', outcome='SUCCESS', status='200', uri='UNKNOWN'], highCardinalityKeyValues=[http.url='/manual'], map=[class io.micrometer.core.instrument.Timer$Sample='io.micrometer.core.instrument.Timer$Sample@4b01a181', class io.micrometer.core.instrument.LongTaskTimer$Sample='SampleImpl{duration(seconds)=0.0014358, duration(nanos)=1435800.0, startTimeNanos=2855503504200}'], parentObservation=null}
: onScopeOpened called
:  OPEN - name='manual', contextualName='null', error='null', lowCardinalityKeyValues=[], highCardinalityKeyValues=[], map=[class io.micrometer.core.instrument.Timer$Sample='io.micrometer.core.instrument.Timer$Sample@61edd426', class io.micrometer.core.instrument.LongTaskTimer$Sample='SampleImpl{duration(seconds)=1.803E-4, duration(nanos)=180300.0, startTimeNanos=2855505256500}'], parentObservation={name=http.server.requests(null), error=null, context=name='http.server.requests', contextualName='null', error='null', lowCardinalityKeyValues=[exception='none', method='GET', outcome='SUCCESS', status='200', uri='UNKNOWN'], highCardinalityKeyValues=[http.url='/manual'], map=[class io.micrometer.core.instrument.Timer$Sample='io.micrometer.core.instrument.Timer$Sample@4b01a181', class io.micrometer.core.instrument.LongTaskTimer$Sample='SampleImpl{duration(seconds)=0.0020807, duration(nanos)=2080700.0, startTimeNanos=2855503504200}'], parentObservation=null}
: get manual called
: CLOSE - name='manual', contextualName='null', error='null', lowCardinalityKeyValues=[], highCardinalityKeyValues=[], map=[class io.micrometer.core.instrument.Timer$Sample='io.micrometer.core.instrument.Timer$Sample@61edd426', class io.micrometer.core.instrument.LongTaskTimer$Sample='SampleImpl{duration(seconds)=5.845E-4, duration(nanos)=584500.0, startTimeNanos=2855505256500}'], parentObservation={name=http.server.requests(null), error=null, context=name='http.server.requests', contextualName='null', error='null', lowCardinalityKeyValues=[exception='none', method='GET', outcome='SUCCESS', status='200', uri='UNKNOWN'], highCardinalityKeyValues=[http.url='/manual'], map=[class io.micrometer.core.instrument.Timer$Sample='io.micrometer.core.instrument.Timer$Sample@4b01a181', class io.micrometer.core.instrument.LongTaskTimer$Sample='SampleImpl{duration(seconds)=0.0023855, duration(nanos)=2385500.0, startTimeNanos=2855503504200}'], parentObservation=null}
: onScopeClosed called
:  STOP - name='manual', contextualName='null', error='null', lowCardinalityKeyValues=[], highCardinalityKeyValues=[], map=[class io.micrometer.core.instrument.Timer$Sample='io.micrometer.core.instrument.Timer$Sample@61edd426', class io.micrometer.core.instrument.LongTaskTimer$Sample='SampleImpl{duration(seconds)=-1.0E-9, duration(nanos)=-1.0, startTimeNanos=2855505256500}'], parentObservation={name=http.server.requests(null), error=null, context=name='http.server.requests', contextualName='null', error='null', lowCardinalityKeyValues=[exception='none', method='GET', outcome='SUCCESS', status='200', uri='UNKNOWN'], highCardinalityKeyValues=[http.url='/manual'], map=[class io.micrometer.core.instrument.Timer$Sample='io.micrometer.core.instrument.Timer$Sample@4b01a181', class io.micrometer.core.instrument.LongTaskTimer$Sample='SampleImpl{duration(seconds)=0.0026843, duration(nanos)=2684300.0, startTimeNanos=2855503504200}'], parentObservation=null}
: onStop called
```
Those logs represent observability metric state flow as shown in the diagram:

![observability_flow.png](assets%2Fobservability_flow.png)

Those states flow are configured in `SimpleLoggingHandler.java` class.

### Observe code statements

There are two ways to observe code statements manually as in `ManualObservabilityController.java` controller class example:

```java
@GetMapping
public void getManual() {
    var observation = Observation.createNotStarted("manual", observationRegistry);
    observation.start();
    try (var ignored = observation.openScope()) {
        log.info("get manual called");
    } catch (Exception ex) {
        log.error("error to call method", ex);
        observation.error(ex);
    } finally {
        observation.stop();
    }
}
```

Or the shorter way:

```java
@GetMapping("/simpler")
public void getManualSimpler() {
    var observation = Observation.createNotStarted("manual_simpler", observationRegistry);
    observation.observe(() -> log.info("get manual simpler called"));
}
```

### Observe a class or a method

There is a simpler way to observe a method or a whole class, as it can be seen in `ObservabilityService.java` class, using `@Observed` annotation:

```java
@Observed(name = "observabilityService")
public void get() {
    log.info("get service");
}
```
