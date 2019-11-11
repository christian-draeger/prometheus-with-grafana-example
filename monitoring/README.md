Monitoring
==========

## Prerequisits

In order to follow along, you will need only two things:

* docker
* docker-compose

## A few words regarding the Setup
We are using **Prometheus**, a monitoring system and time series database, 
in combination with its de-facto graphical front-end **Grafana**.

* **Prometheus**
    * is the central piece, it contains the time series database and the logic of scraping stats from exporters (see below) as well as alerts
* **Grafana** 
    * is the ‘face’ of Prometheus. While Prometheus exposes some of its internals like settings and the stats it gathers via basic web front-ends, it delegates the heavy lifting of proper graphical displays and dashboards to Grafana.

## How to use
To spin up the whole monitoring environment run:

    docker-compose up
    
This will start a ready to use Prometheus together with Grafana including: 
* [Prometheus Management UI](http://localhost:9090)
* [Grafana Dashboard](http://localhost:3000)
