FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/app.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java -jar /app.jar --debug