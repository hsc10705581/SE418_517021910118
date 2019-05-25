FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD rest-consumer-0.0.1-SNAPSHOT.jar consumer.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/consumer.jar"]