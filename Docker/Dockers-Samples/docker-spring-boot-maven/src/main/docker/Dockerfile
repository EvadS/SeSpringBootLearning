FROM java:8
VOLUME /tmp
ADD target/app.jar appl.jar
RUN bash -c 'touch /appl.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/appl.jar"]