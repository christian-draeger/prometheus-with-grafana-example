Middleware
==========

Run application on  local machine:
```bash
./gradlew bootRun
```

Create Docker Image / Deployable:
```bash
# docker file will be placed in ./build/docker
./gradlew dockerBuildImage
```

Create Fat-Jar (standalone jar including embedded tomcat server):
```bash
# jar file will be placed in ./build/libs
./gradlew bootJar
```

####Sanity and Health checks
We use [actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html) to get health-checks and application relevant metrics.
