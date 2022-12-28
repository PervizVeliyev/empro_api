FROM openjdk:19
MAINTAINER Parviz
COPY target/RestTemplate-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]