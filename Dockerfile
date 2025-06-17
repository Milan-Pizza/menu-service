FROM eclipse-temurin:17-jre-alpine
LABEL authors="mrinal"
WORKDIR /app
COPY target/menu-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]