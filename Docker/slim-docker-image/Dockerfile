FROM openjdk:8-jre-alpine

MAINTAINER Evad-se@mail.ru
RUN mkdir -p /jar
COPY ./target/slim-docker-image.jar /jar/app.jar
ENTRYPOINT ["java","-jar","/jar/app.jar"]