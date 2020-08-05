[![Actions Status](https://github.com/christian-draeger/prometheus-with-grafana-example/workflows/Continious%20Integration/badge.svg)](https://github.com/christian-draeger/prometheus-with-grafana-example/actions?query=workflow%3A"Continious+Integration")

Spring-Boot Monitoring Setup with Prometheus and Grafana
========================================================

## Prerequisits

* JDK >=8 installed
* docker installed
* docker-compose installed

#### Recommended

* [gdub](https://github.com/dougborg/gdub) installed
    * installation via brew: `brew install gdub`
    * installation via mac-ports: `sudo port install gdub`


## TL;DR - Start All Services (Middleware & Monitoring)
run from project root:

```bash
./gradlew start

# or using gdub
gw start
```

## Development History and Functional Requirements
### Initial Project Setup

#### Evaluation Phase
##### Server-Framework
We decided to use Spring-Boot over other Frameworks because it has a wide distribution, is a mature technology and has a very extensive community and documentation. Since it is de facto an industry standard at the moment, this decision should ensure that the further development and maintenance of the application should not be a problem for a wide range of developers.
All Frameworks that have been evaluated and would have been a good fit for the project are:
* Spring-Boot
* Ktor
* Quarkus
* Micronaut

#### Tools
Focus of the first development phase was to make it possible to outsource functionality, especially the authentication from the iOS app, as well as enabling a centralised business metric monitoring.
To have the possibility to create date agnostic / time-based reports in the future we decided to use a time series database.  
A time series database (TSDB) is a software system that is optimized for storing and serving time series through associated pairs of time(s) and value(s).

Evaluated TSDBs are:
* [Prometheus](https://en.wikipedia.org/wiki/Prometheus_(software))
* [InfluxDB](https://en.wikipedia.org/wiki/InfluxDB)
* [Graphite](https://en.wikipedia.org/wiki/Graphite_(software))

We decided to use Prometheus because of its excellent spring-boot integration as well as the HTTP pull model it is using to provide metrics from a monitored application.

Visualization of Metrics:
To arrange the metrics on dashboards and provide nice looking and intuitive Monitoring we decided to use [Grafana](https://grafana.com/) because it has a wide range of plugins to visualize all kinds of data, is pretty flexible in terms of datasources, has an active and big community as well as working out of the box with Prometheus. 

#### SCM
We use [git](https://git-scm.com/) as our version control system of choice. The origin server is github (private repository).

#### Creation of initial Project setup
The initial Project setup is a Gradle multi module project consisting of
* the [application](./application/README.md) module, that is representing the middleware
* the [monitoring](./monitoring/README.md) module, where all grafana and prometheus specifics live 
* the [systemTests](./systemTests/README.md) module, where the End-to-End tests are located to verify that the middleware is working properly with the monitoring

All needed tools and the middleware itself can be started conveniently with one command via docker-compose:
```bash
./gradlew start
```
and could thereby be easily deployed to any kind of docker container orchestration like Kubernetes.


