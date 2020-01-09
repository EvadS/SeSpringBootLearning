# Getting Started

примеир не закончен но рабочий 
## TODO: 
1. зделать нормальные апи в соотвествии с best practice 
2. maven 
    профили 
    sonar cube 
    
3. база в файл



http://localhost:8080/v3/api-docs/    
### Reference Documentation

##Используемые технологии 
* h2 (memory), скрипт инициализации
* JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository 
* Валидация 
    * Hibernate Validator
   
* HATEOAS (Hypermedia as the Engine of Application State) - это правило архитектуры REST приложения. 
* swagger
    http://localhost:8080/my/swagger-ui/index.html


### H2
 H2 console at http://localhost:8080/h2-console.
 
  H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
  
## REST API 
* get all http://localhost:8080/students  
``` curl  http://localhost:8080/students ```

* get specific student  ``` curl http://localhost:8080/students/10002 ```
* DELETE ``` curl -i http://localhost:8080/students/10002 ```
* create a new student 
```
curl -s -i  -X POST \
  http://localhost:8080/students \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
    "name": "Tom",
    "passportNumber": "Z1234567"
  }'
 ```
 
 * UPDATE (   http://localhost:8080/students/{id})
 
 ```
 curl -s -i -X PUT \
   http://localhost:8080/students/2 \
   -H 'Content-Type: application/json' \
   -H 'cache-control: no-cache' \
   -d '{
     "name": "Tom",
     "passportNumber": "Z1234567"
   }'
  ```
  
  ##Правила валидации 
 * DecimalMax
 * DecimalMin
 * Digits
 * Email
 * Future
 * FutureOrPresent
 * Max
 * Min
 * Negative
 * NegativeOrZero
 * NotBlank
 * NotEmpty
 * NotNull
 * Null
 * Past
 * PastOrPresent
 * Pattern
 * Positive
 * PositiveOrZero
 
 ### smaples 
 https://javadeveloperzone.com/spring-boot/spring-boot-hateoas-rest-service-example/
 
 
 ## profiles 
 
 
 run on development profile
 ```
 java -Dspring.profiles.active=development -jar target/app.jar
 ```
 check profile 
 
 ```
 curl http://localhost:8080/profile/checkProfile
 ```
 as a result:
  ``` java 
  Spring Boot is running under development Profile
 ```
 