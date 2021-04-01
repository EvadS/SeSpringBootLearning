# Liquibase. 

Независимая от базы данных библиотека для отслеживания, управления и применения изменений схемы базы данных.

## Before start 
Итак, на входе имеем систему с которой реализовано создание и получение списка пользователей 

Создание пользователей, имя уникальное 
```bash
curl -X POST \
  http://localhost:8080/user \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: b92627ca-c3a5-4c6f-9e84-e63e52bfd6a4' \
  -H 'cache-control: no-cache' \
  -d '{
	"userName": "test name2",
	"age": 1
}'
```

список 
```bash
curl -X GET \
  http://localhost:8080/user/all \ 
  -H 'cache-control: no-cache'
```

используется 3 профиля local/dev/stage - для каждого отдельная база
---------------

## Настройка для spring-boot

### Dependency
```xml
<dependency>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-core</artifactId>
</dependency>
```

### отключить создание схемы БД.
```properties
spring.jpa.hibernate.ddl-auto=none
```

### Главный файл жрнала изменений
Необходимо создать главный changelog. По умолчанию в spring-boot Liquibase ищет его в папке 
resources/db/changelog/db.changelog-master.yml


### Скелет для главного changelog
Создаем db.changelog-master.xml

Вставляем начальное содержимое в файл:
```xml
    <databaseChangeLog
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
            xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd">
    
        // сюда пишутся changeSets
    
    </databaseChangeLog>
```

### Указать главный change log
изменяем путь в application.properties
```properties
liquibase.change-log=classpath:db/changelog/db.changelog-master.xml
```


Generate the changeLog With a Maven Plugin
### подключаем мавен плагин 
```xml
 <plugin>
        <groupId>org.liquibase</groupId>
        <artifactId>liquibase-maven-plugin</artifactId>
        <version>3.4.1</version>
        <configuration>                  
            <propertyFile>src/main/resources/liquibase.properties</propertyFile>
        </configuration>                
    </plugin> 
```
###  настраиваем liquibase properties  
минимальный конфиг берем из application.properties
```
url=jdbc=
username=
password=
driver=com.mysql.cj.jdbc.Driver
outputChangeLogFile=src/main/resources/liquibase-outputChangeLog.xml
```
## Generate a ChangeLog From an Existing Database
```
   mvn liquibase:generateChangeLog
```
получить change set с данными
```bash
mvn liquibase:generateChangeLog -Dliquibase.diffTypes=data 
```
в результате текущее состояние базы будут в файле
liquibase-outputChangeLog.xml

