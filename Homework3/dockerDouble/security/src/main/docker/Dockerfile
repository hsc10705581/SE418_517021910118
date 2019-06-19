FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD security-0.0.1-SNAPSHOT.jar sec.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/sec.jar"]