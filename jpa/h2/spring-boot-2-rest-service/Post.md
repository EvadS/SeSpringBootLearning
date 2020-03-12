## Post
база обнуляется при перезапуске 
### цель 
поле автор  заполняется только при создании
```
    @Column(name = "AUTHOR", nullable = false, length = 75, updatable= false )
    private String autor;
```    
  ```http request GET 
localhost:8080/post/ 

```
Create 
```http request POST 
localhost:8080/post/create
{
	"title" : "TITLE",
	"score": 1000,
	"autor" : "AAAAAAAAAA"	
}
```
update 
```http request
localhost:8080/post/create
{
	"title" : "TITLE",
	"score": 1000,
	"autor" : "autor"	
}
```
