# Getting Started
Liquibase maven sample

## part 1 

Добавляем в файл application.properties настройку
```json
spring.jpa.hibernate.ddl-auto=none
```

 http://localhost:8080/user/all
 
 ## part 2
  liquibase-maven-plugin
  
 ### liquibase-maven-plugin.properties
 Поле **referenceUrl**. Как видите она ссылается не на БД, а на пакет приложения в котором находятся классы сущностей
  
## После изменений базы 
### 1. команда сборки, использующую liquibase-maven-plugin для сравнения.
 ```bash 
  mvn clean install liquibase:diff -DskipTests=true
```

### 2. подключаем сгенеренный скрипт к основному инструкцией include 
```yaml
  - include:
      file: db.changelog-20190723-100748666.yaml
      relativeToChangelogFile: true
```

### 3.  в этом файле нужно указать  logicalFilePath: db/changelog/db.changelog-20190723-100748666.yaml по аналогии с тем, как это сделано 
в db.changelog-master.yaml.

### 4. Запуск
После этого перезапустите приложение или выполните команду:
```
    mvn liquibase:update
```


 этот скрипт вносит именно то изменение, которое и было сделано в коде.
Далее мы просто скопировали changeSet из этого файла в db.changelog-master.yaml

https://habr.com/ru/post/460907/