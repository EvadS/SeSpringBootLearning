#HATEOAS

Enable annotation processor (on idea ) !
HATEOAS stands for **_“Hypermedia as the engine of application state”_**
##  правило архитектуры REST приложения.

Примеры
Ниже представлен код объекта Customer:

`
class Customer {
    String name;
}`

Простое JSON представление выглядит так:

```java
{ 
    "name" : "Alice"
}
```

Здесь информация о клиенте, но она ничего не содержит из числа её релевантных ссылок.

HATEOAS ответ будет выглядеть следующим образом:

```javascript
{
    "name": "Alice",
    "links": [ {
        "rel": "self",
        "href": "http://localhost:8080/customer/1"
    } ]
}
```

* **rel** означает отношение. В данном случае, гиперссылка на себя. Более сложные системы могут включать другие отношения. К примеру, отношение "rel":"customer" связывает запрос с другим клиентом
* **href** - это полная URL, которая однозначно указывает на ресурс

-- -------------