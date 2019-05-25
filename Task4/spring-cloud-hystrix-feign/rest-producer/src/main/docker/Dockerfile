FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD rest-producer-0.0.1-SNAPSHOT.jar producer.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/producer.jar"]