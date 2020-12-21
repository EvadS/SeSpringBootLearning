# Base crud spring mvc application

```bash
 mvn spring-boot:run
 ```

 ##Собрать образ
 ```bash
 docker build --file=Dockerfile \
      --tag=spring-h2-server:1.0.0 .
 ```

### запускаем 
```bash
    docker run -p 8001:8000 --name base-spring-h2-server spring-h2-server:1.0.0
```

### закинуть image на докер хаб 
имя профиля - имя репозитория
создаем образ
docker commit  base-spring-h2-server sevad/base-spring-h2-server:v1.0.0
авторизируемся 
```bash
docker login 
```
закидываем
```bash
    docker push sevad/base-spring-h2-server:v1.0.0
```

