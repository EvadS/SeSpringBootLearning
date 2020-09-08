# Пользовательская аутентификация в Spring Security

MVC вариант когда используем Authentication authentication для получения данных о пользователе

здесь добавлена форма для логина 
http://localhost:8000/login
admin/admin

вначале лоинимся и попадаем в CustomAuthencationProvider
где создается объект Authentication

его нужно передавать в каждом запросе где нужен авторищзированный пользователь 

Настройка Spring Security
Настройка Spring Security
void configure(HttpSecurity http) для авторизации
void configure(AuthenticationManagerBuilder auth) для аутентификации