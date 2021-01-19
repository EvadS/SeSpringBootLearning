# Getting Started
Постое приложение на Spring Boot в Docker-контейнере в поде K8s, который управляется через K8s deployment
 и доступен через сервис

## Docker 

### build image 
```bash
docker build -t sevad/hello-gradle-spring .
```

Check docker image 
```bash
docker run -p 9000:9000 sevad/hello-gradle-spring
```

### push to docker hub 
```bash
  docker push sevad/hello-gradle-spring
```
--------------
Файл манифеста K8s тоже простой. Он состоит из развертывания (Deployment) и сервиса (Service):

base-spring-kuber-deployment.yaml

Deployment определяет две реплики пода, в которых будет выполняться контейнер, созданный из образа, указанного в атрибуте image.

У сервиса (Service) тип ClusterIP (тип сервиса по умолчанию). Он предоставляет внутри кластера возможность подключаться
 к нам другим приложениям.
 
 Создаем ресурсы в кластере:
 ```bash
   kubectl create -f base-spring-kuber-deployment.yaml
 ```


 Внутри кластера
 
 $ kubectl get pods
 
 kubectl get svc
 
 зайти на под 
 kubectl exec -it hello-world-5bb87c95-6h4kh bash