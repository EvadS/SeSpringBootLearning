# back
# устанавливаем самую лёгкую версию JVM
FROM openjdk:8-jdk-alpine

# указываем ярлык. Например, разработчика образа и проч. Необязательный пункт.
LABEL maintainer="evad-se@gmail.com"

# указываем, где в нашем приложении лежит джарник
ARG JAR_FILE=target/app.jar


ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]