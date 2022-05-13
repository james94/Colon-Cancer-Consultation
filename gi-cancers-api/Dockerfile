FROM openjdk:17-jdk-alpine

# SJSU CMPE252 Spring 2022

ENV SPRING_DATASOURCE_URL=jdbc:mysql://gicancers-db:3306/gicancers
ENV SPRING_DATASOURCE_USERNAME=gicancers_docker
ENV SPRING_DATASOURCE_PASSWORD=gicancers_docker

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
