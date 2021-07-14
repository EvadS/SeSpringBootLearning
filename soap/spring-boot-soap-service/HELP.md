# Getting Started

There is  jaxb2-maven-plugin to generate the domain classes efficiently.

### Contract-Last  
  we start with the WSDL contract, from which we generate the Java classes.
  
###  Contract-First. 
  Spring-WS only supports the contract-first development style.    
  contract-first approach requires us to create the domain (methods and parameters) for our service first.
  
  
### jaxb2-maven-plugin

the classes will be automatically generated 
```bash
    mvn compile
```

run 
```
    mvn spring-boot:run
```

test
```http request
curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws 
```