#! /bin/bash

./wait-for-it.sh postgres:6033 -t 15
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -Djava.security.egd=file:/dev/./urandom  -jar   target/app.jar

