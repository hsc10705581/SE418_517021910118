FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD adjmatrixgraph-0.0.1-SNAPSHOT.jar adj.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/adj.jar"]