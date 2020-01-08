# Getting Started

### Reference Documentation

##Используемые технологии 
* h2 (memory), скрипт инициализации
* JpaRepository extends PagingAndSortingRepository which in turn extends CrudRepository 
* Валидация 
    * Hibernate Validator



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
 
 