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
  
  
  команда сборки, использующую liquibase-maven-plugin для сравнения.
 ```bash 
  mvn clean install liquibase:diff -DskipTests=true
```

https://habr.com/ru/post/460907/