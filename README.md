Spring-Boot Monitoring Setup with Prometheus and Grafana
============================

## Prerequisits

* JDK >=8 installed
* docker installed

#### Recommended

* [gdub](https://github.com/dougborg/gdub) installed
    * installation via brew: `brew install gdub`
    * installation via mac-ports: `sudo port install gdub`


## Start All Services (Middleware & Monitoring)
run from project root:

```bash
./gradlew build && docker-compose up

# or using gdub
gw build && docker-compose up
```
