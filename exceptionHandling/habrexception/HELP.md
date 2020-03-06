# Getting Started

# use 
 * <b>loombook</b> (Если вы хотите повторить данный пример у себя в IntelliJ Idea, то вам необходимо поставить галочку в пункте enable annotation processing, либо написать все руками.)
 
### Reference Documentation
* Аннотация <b>ExceptionHandler</b>. Используется для обработки собственных и каких-то специфичных исключений. 

* Аннотация <b>ControllerAdvice</b>. Данная аннотация дает «совет» группе констроллеров по определенным событиям. В нашем случае — это обработка ошибок. По умолчанию применяется ко всем контроллерам, но в параметрах можно указать отпределенную группу. 

* Класс <b>ResponseEntityExceptionHandler</b>. Данный класс занимается обработкой ошибок. У него куча методов, название которых построенно по принципу handle + название исключения. Если мы хотим обработать какое-то базовое исключение, то наследуемся от этого класса и переопределяем нужный метод.

### Структура 
Здесь 3 пакета каждый из которых демонстрирует как обрабатывать исключения 

Замечания 
* `io.apptizer.api.errors.codes` - больше как заготовка. интерес представляет enum с фиксированным списком возможных ошибок 
* `se.aop.exceptionhandling` обработка на основе аспектов. 

## HabrexceptionApplication
самый простой и базовый вариант
Корректный вызов
``` http request
http://localhost:8080/user/1
```

incorrect 
```http request
 http://localhost:8080/user/4
```
будет сгенерено ThereIsNoSuchUserException (status : 404 )
## ExceptionLoggerPointCut
This quick lesson in AOP will explore you can use Spring's implementation of aspect-oriented programming to handle exceptions.

Running the Application
To run the application, run the following command:
```
gradle bootRun 
```
