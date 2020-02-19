# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/maven-plugin/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

mvn clean package

docker-compose build

Run Application With docker-compose


Now we will apply some commands to run our application. I think we have already downloaded project from the above link. We will go to the project root directory. To run the application we will use following commands:

 docker-compose up — This will execute Dockerfile commands and will run services defined in the docker-compose file.
 docker-compose down — This will stop and remove all containers that were running by docker-compose file.
 docker-compose up --build — If we do an update on the Dockerfile, the war/jar file, or the docker-compose file, then we have to execute this command to get updated data on the Docker machine.
So, I think after running docker-compose up, It runs the application with all services in the Docker machine. Ignore some initial exception logs for database communication. To check whether it runs or not, we can check http://localhost:10222/book. We can see the list of books here. 