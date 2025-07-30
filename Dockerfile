FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21
WORKDIR /app
COPY --from=builder /app/target/mapAereo-0.0.1-SNAPSHOT.jar mapAereo.jar
ENTRYPOINT ["java", "-jar", "mapAereo.jar"]