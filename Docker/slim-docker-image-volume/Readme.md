
# Check 
``` http request
http://localhost:8802/hello?name=Rostyslav
```

## base dockerfile 
FROM openjdk:13.0.2-slim-buster
MAINTAINER Evad-se@mail.ru
RUN mkdir -p /jar
COPY ./target/slim-docker-image.jar /jar/app.jar
ENTRYPOINT ["java","-jar","/jar/app.jar"]

##Собрать образ 
```bash
docker build --file=Dockerfile \
     --tag=my-server:1.0.0 .
```

## check
```bash
docker images 
```

### смотрим 
 openjdk:13.0.2-slim-buster. Его размер составляет 409 MB 
 openjdk       13.0.2-slim-buster   5927024fce47        5 days ago           409MB
 my-server     latest               91a1847da390        About a minute ago   429MB

### запускаем 
```bash
    docker run -p 8801:8801 --name my-server-docker  my-server:1.0.0
 ```

Теперь проанализируем, сколько занимает установленный туда JDK 13.0.2:
```bash
docker exec -it my-server-docker /bin/bash
du -hs /usr/java/openjdk-13
```
---------------------------
Result
root@32dd55685788:/# du -hs /usr/java/openjdk-13
316M    /usr/java/openjdk-13

 JDK занимает **316** из 409 MB размера всего образа.
 
-----
### 
Анализируем 
```bash
jdeps -cp "java -jar target/slim-docker-image.jar --thin.classpath" target/slim-docker-image.jar
```
есть модули not found, что не дает понимания, каких модулей в итоге не хватает.

Docker-образ с кастомным JRE
Берем за основу Dockerfile, который используется в openjdk, и модифицируем его таким образом, чтобы вместо
 установленного JDK использовался кастомный JRE

-----
меняем на более легковесную 
FROM openjdk:8-jre-alpine

### собираем имедж
```base
docker build --file=Dockerfile      --tag=my-server:1.0.3 .
```
### стартуем 
```base
docker run -d -p 8802:8801 --name my-server-docker-2  my-server:1.0.3
```
### заходим в контейнер 
```base
docker exec -it my-server-docker-2 /bin/sh
```

### находим расположение джава 
```base
echo $JAVA_HOME
```
### смотрим размер
```base
du -hs /usr/lib/jvm/java-1.8-openjdk/jre
```
### тек размер 
```base
current size : 58
```
## смотрим слои
docker history  my-server:1.0.3

----------------------------------
## Работа с томами из командной строки
### Создание тома

```bash
docker volume create --name my_volume
```
## Выяснение информации о томах
```bash
docker volume ls
```

## Исследовать конкретный том можно так:
```bash
docker volume inspect my_volume
```
## Удаление тома
````bash
docker volume rm my_volume
````
 удалить все тома, которые не используются контейнерами,
```bash
docker volume prune
```

создать том во время создания контейнера
docker container run --mount source=my_volume, target=/container/path/for/volume my_image


----------------
maven plugin 
```base 
mvn package
```

links 
Containerizing your Spring boot application with Docker
```http request
https://dimitr.im/docker-spring-boot
```

base Docker 
```dockerfile
FROM openjdk:13-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```