#
# Author: Rico Krenn
# Created: 06/22/2026
# Version: 1.0
# Description: Dockerfile for Maven
# Project: 200_Abschlussprojekt
#

FROM maven:3.9-eclipse-temurin-21 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]