# Base crud spring mvc application

```bash
 mvn spring-boot:run
 ```

 ##Собрать образ
 ```bash
 docker build --file=Dockerfile \
      --tag=myk8sapp:1.0.0 .
 ```

### чекаем, опционально
```bash
 docker images 
```

### запускаем 
собрать и запустить 
```bash
  docker run -p 8001:8000 --name spring-h2-container myk8sapp
```
простой запуск
```bash
   docker start spring-h2-container
```

### докер хаб 

авторизируемся 
```bash
docker login 
```
переименовываем image
```bash
 docker tag myk8sapp sevad/springh2k8s:latest
```

закидываем
```bash
    docker push sevad/springh2k8s:latest
```

 запустить 
```bash
    docker run -it  -p 18001:8000 \
          --name MY_SPRING_H2 \
          sevad/springh2k8s
```



