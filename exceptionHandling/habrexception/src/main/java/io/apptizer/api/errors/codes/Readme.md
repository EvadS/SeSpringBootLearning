# Exception Handling 

## code snippet 

then in your rest service you can throw exception with error code mapping.

```java 
throw new ResourceNotFoundException(ApiError.PRODUCT_NOT_FOUND, "Product Task Not Found");`
```