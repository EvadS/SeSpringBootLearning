# Information from Http request 

## How to get information from http Request 
## Base variant 

```http request
 http://localhost:8080/agent
```

## Customized variant  (with UserAgentUtils)
 ```http request
 http://localhost:8080/user-agent
```
we try to find **"mobile-user-agent"** in Header 
*  found - use value 
* not found - use value from request 
Postman 
http://localhost:8080/user-agent
with headers 
client: my-user-agent
without 
userAgent: PostmanRuntime/7.6.0

* Firefox 
userAgent: FIREFOX:78.0

* Chrome 
userAgent: CHROME:84.0.4147.89





