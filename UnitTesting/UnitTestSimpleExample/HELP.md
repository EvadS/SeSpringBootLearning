# Getting Started

## Hello wold junit project

### Reference Documentation
Spring Boot Test предоставляется двумя модулями -
* **spring-boot-test** содержит основные элементы
* **spring-boot-test-autoconfigure** поддерживает автоматическую настройку для тестов

```http request
curl  localhost:8080/employee
```

### step 1 
 @SpringBootTest это альтернатива @ContextConfiguration
 работает путем создания ApplicationContext, используемого в ваших тестах через SpringApplication.