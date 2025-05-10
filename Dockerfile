FROM eclipse-temurin:21
LABEL maintainer="github.com/estevao081"
WORKDIR /app
COPY target/mapAereo-0.0.1-SNAPSHOT.jar /app/mapAereo.jar
ENTRYPOINT ["java", "-jar", "mapAereo-0.0.1-SNAPSHOT.jar"]