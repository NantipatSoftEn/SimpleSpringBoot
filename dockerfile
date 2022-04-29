#
# Build stage
#
# FROM maven:3.6.0-jdk-11-slim AS build
# COPY src /usr/src/app
# COPY pom.xml /usr/src/app
# RUN mvn -f /usr/src/app/pom.xml clean package


FROM openjdk:15-jdk-alpine
COPY target/app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

