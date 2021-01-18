# Hola Docker with Spring Boot

**Updated**: Now the code uses Spring Boot 2 and Java 10.

This project shows you how to dockerize a Spring Boot application using a single `Dockerfile` or combining it with `docker-compose`.

The guide to build the code and understand the different alternatives is located at [https://thepracticaldeveloper.com/2017/12/11/dockerize-spring-boot/](https://thepracticaldeveloper.com/2017/12/11/dockerize-spring-boot/).

## Running the app with Docker

Make sure you generate first the `.jar` file by running:

`mvn clean package`

Then, you just need to execute:

`docker-compose up`

Building the image
```bash
docker build -t hola-manual-build .
```

docker images

docker run -p 8000:8080 hola-manual-build

localhost:8000


docker-compose up

все композы 
 docker-compose ps
 
 ### Accessing the source code
 $ docker-compose exec --index=1 hola /bin/bash
 
 ## Debugging the application in a Docker container
 
 docker run -e "JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n" -p 8080:8080 -p 5005:5005 -t hola-manual-build
 
 ## Using Spring Profiles
 ```bash
     docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t hola-manual-build
```