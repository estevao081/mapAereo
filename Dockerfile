FROM eclipse-temurin:21-jdk-jammy AS build

ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

WORKDIR /app

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN chmod +x mvnw && \
    echo "Java version:" && \
    java -version && \
    echo "JAVA_HOME: $JAVA_HOME" && \
    echo "Path: $PATH"

RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

CMD [ "java", "-jar", "app.jar" ]