# soft-drinks-industry-levy-all-performance-tests

Performance test suite for the `soft-drinks-industry-levy`, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood.

## Pre-requisites

### Services

Start `soft-drinks-industry-levy` services as follows:

```bash
sm2 --start SDIL_ALL
```

### Logging

The default log level for all HTTP requests is set to `WARN`. Configure [logback.xml](src/test/resources/logback.xml) to update this if required.

### WARNING :warning:

Do **NOT** run a full performance test against staging from your local machine. Please [implement a new performance test job](https://confluence.tools.tax.service.gov.uk/display/DTRG/Practical+guide+to+performance+testing+a+digital+service#Practicalguidetoperformancetestingadigitalservice-SettingupabuildonJenkinstorunagainsttheStagingenvironment) and execute your job from the dashboard in [Performance Jenkins](https://performance.tools.staging.tax.service.gov.uk).

## Tests

Run smoke test (locally) as follows:

```bash
sbt -Dperftest.runSmokeTest=true -Dconfig.resource=application.local.conf -DrunLocal=true -Dperftest.labels=registrations Gatling/test
sbt -Dperftest.runSmokeTest=true -Dconfig.resource=application.local.conf -DrunLocal=true -Dperftest.labels=returns Gatling/test
sbt -Dperftest.runSmokeTest=true -Dconfig.resource=application.local.conf -DrunLocal=true -Dperftest.labels=variations Gatling/test
sbt -Dperftest.runSmokeTest=true -Dconfig.resource=application.local.conf -DrunLocal=true -Dperftest.labels=all Gatling/test
```

Run full performance test (locally) as follows:

```bash
sbt -Dperftest.runSmokeTest=false -Dconfig.resource=application.local.conf -DrunLocal=true -Dperftest.labels=all Gatling/test
```

Run tests (Staging) from Jenkins here:

[soft-drinks-industry-levy-all-performance-tests](https://performance.tools.staging.tax.service.gov.uk/job/soft-drinks-industry-levy-all-performance-tests/)

Run tests using labels

```bash
> sbt -Dperftest.labels={label} test
```

labels are: all, returns, variations

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

* application.conf: Default configuration for Jenkins/staging runs
    * Uses production load (10%)
    * Prevents overwhelming shared staging environment during CI performance tests
* application.local.conf: Configuration for local development runs
    * Uses production load with 2x buffer (20%)
    * Provides confidence that application can handle peak load scenarios
    * Automatically used when running tests locally

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
