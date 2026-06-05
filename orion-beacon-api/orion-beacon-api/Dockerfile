FROM maven:3.9.9-eclipse-temurin-21-alpine AS build

WORKDIR /build

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S oriongroup && adduser -S orionuser -G oriongroup

WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

EXPOSE 8080

USER orionuser

ENTRYPOINT ["java", "-jar", "app.jar"]