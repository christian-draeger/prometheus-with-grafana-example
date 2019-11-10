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
* **Alertmanager** 
    * manages the routing of alerts which Prometheus raises to various different channels like email, pagers, slack - and so on. So while Prometheus collects stats and raises alerts it is completely agnostic of where these alerts should be displayed. This is where the alertmanager picks up.
* **Exporters** 
    * are http endpoints which expose ‘prometheus metrics’ for scraping by the Prometheus server. What this means is that this is a pull set-up. Note that it is also possible to set up a push-gateway which is essentially an intermediary push target which Prometheus can then scrape. This is useful for scenarios where pull is not appropriate or feasible (for example short lived processes).
    
## How to use
To spin up the whole monitoring environment run:

    docker-compose up
    
This will start a ready to use Prometheus together with Grafana including: 
* [Prometheus Management UI](http://localhost:9090)
* [Grafana Dashboard](http://localhost:9091)
