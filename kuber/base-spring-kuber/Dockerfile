#FROM gradle:jdk10

#FROM adoptopenjdk:8-jdk-hotspot
FROM  gradle:6.7-jdk8

#копирование проекта в /app
COPY --chown=gradle:gradle . /app
WORKDIR /app

#сборка проекта с помощью Gradle
RUN gradle build

EXPOSE 9000
WORKDIR /app

#запуск приложения, используя результат предыдущего шага
CMD java -jar build/libs/base-spring-kuber-0.0.1-SNAPSHOT.jar
