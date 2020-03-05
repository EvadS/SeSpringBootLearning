FROM openjdk:8-jdk-alpine

COPY . /var/www/java


#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar

WORKDIR /var/www/java

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/app.jar"]

