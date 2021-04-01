# Getting Started
Итак, на входе имеем систему с которой реализовано создание и получение списка пользователей 

Создание, имя уникальное 
```bash
curl -X POST \
  http://localhost:8080/user \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: b92627ca-c3a5-4c6f-9e84-e63e52bfd6a4' \
  -H 'cache-control: no-cache' \
  -d '{
	"userName": "test name2",
	"age": 1
}'
```

список 
```bash
curl -X GET \
  http://localhost:8080/user/all \ 
  -H 'cache-control: no-cache'
```

используется 3 профиля local/dev/stage - для каждого отдельная база
---------------

