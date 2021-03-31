
#Установка и использование PostgreSQL на Ubuntu 18.04

Open the terminal using Ctrl + Alt + t and login as the root user on your system.

## Шаг 1 — Установка 

 обновить локальный индекс пакетов.
```bash
   sudo apt update
```
 Установить пакет Postgres
 -contrib  содержит дополнительные утилиты и функциональные возможности:
```bash
  sudo apt install postgresql postgresql-contrib
```
----------------------------------------------------------------------------

## удаляем  PostgreSQL
 ### List All Postgres related packages
```bash
dpkg -l | grep postgres
```

### Remove all above from prev list 
```
sudo apt-get --purge remove
```

### Remove the following folders

```bash
sudo rm -rf /var/lib/postgresql/
sudo rm -rf /var/log/postgresql/
sudo rm -rf /etc/postgresql/
```

### Remove the postgres user:
```bash
sudo deluser postgres
```
----------------------------------------------------------------------------


## Configure PostgreSQL
 
В ходе установки была создана учетную запись пользователя **postgres**, которая связана с используемой по умолчанию ролью
 Postgres. Чтобы использовать Postgres, вы можете войти в эту учетную запись.

### Change the postgres user’s Linux password
```bash
   sudo passwd postgres
```

###  to set a password for the postgres database user.
```bash
  su - postgres
  psql -d template1 -c "ALTER USER postgres WITH PASSWORD 'newpassword';"
```

### Create a Database
```
   createdb mytestdb
```

закрыть командную строку
```bash
 \q
```
---------------------------------------------------------------------------

## У 
  
#### Переключение на учетную запись postgres
``` bash
   sudo -i -u postgres
```

#### получить доступ к командной строке 
```bash
   psql
```
#### postgresql service  
``` bash
   sudo service postgresql restart
   sudo service postgresql status
```
-----------------------------------------------------------------------------


### Create data base to pg Admin 
$ sudo su - postgres
$ psql
postgres=# 


postgres=# CREATE USER tecmint WITH PASSWORD 'securep@wd';
postgres=# CREATE DATABASE tecmintdb;
postgres=# GRANT ALL PRIVILEGES ON DATABASE tecmintdb to tecmint;
postgres=# \q

Configuring PostgreSQL Client Authentication
 sudo vim /etc/postgresql/12/main/pg_hba.conf

$ sudo systemctl restart postgresql

--- Install pgAdmin

https://www.tecmint.com/install-postgresql-and-pgadmin-in-ubuntu/



