# Use a lightweight Java runtime
FROM eclipse-temurin:21-jdk

LABEL authors="essemoonkemka"

# Set working directory inside container
WORKDIR /app

# Copy the built jar into the container
COPY target/chatdemo-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]