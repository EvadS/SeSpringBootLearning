# Spring Boot, H2, JPA, Hibernate Rest API Tutorial.

Automating the Docker image creation and publishing using dockerfile-maven-plugin

## Requirements
1. Java - 1.8.x
2. Maven - 3.x.x
3. Docker

## Dockerize
 build the docker image using docker-file-maven plugin -
 first packages the application in the form of a jar file, and then builds the docker image

```bash
   mvn package dockerfile:build
```

### login to docker hub 
```bash
  docker login 
```

or wrapper 
```bash
   ./mvnw package dockerfile:build
```
Finally, you can push the docker image to the docker registry using dockerfile:push command -
```bash
   mvn dockerfile:push
```
