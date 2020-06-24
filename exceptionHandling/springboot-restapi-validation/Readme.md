Spring Boot REST API Validation + Globally Error Handling Tutorial with Example

https://hellokoding.com/spring-boot-rest-api-validation-tutorial-with-example/


Type command mvn clean spring-boot:run at your project root directory to run the application

Test with cURL

```http request
curl -i -X PUT -H "Content-Type:application/json" -d "{\"id\": 1, \"name\" : \"Hello Koding\", \"description\": \"Practical Coding Courses, Tutorials and Examples\", \"price\":1}" http://localhost:8080/api/v1/products/1

```

Expected output
{"status":"400 BAD_REQUEST","message":"Product ID 1 is not existing","body": null}