#REST API Exception Handling in Spring Boot

An exception is an undesirable or unforeseen occasion, which happens during the execution of a program i.e at run time
, that breaks the program.

use **@ControllerAdvice** and **@ExceptionHandler** to handle all exceptions at one place so if an exception is thrown
 it will go through our handled methods.
 
## @ResponseStatus 
 задать статус ответа в польщовтельском exception
 
### @ControllerAdvice
 @ControllerAdvice is a specialization of the @Component annotation which allows to handle exceptions across the whole
 application in one global handling component. It can be viewed as an interceptor of exceptions thrown by methods 
 annotated with @RequestMapping and similar.
 
 Имеет больший приоритет чем  @ResponseStatus
 
 ## @ExceptionHandler
 Annotation for handling exceptions in specific handler classes and/or handler methods.
