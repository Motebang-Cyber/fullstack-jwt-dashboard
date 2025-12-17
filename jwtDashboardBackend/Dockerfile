# Use a lightweight Java image
FROM eclipse-temurin:21-jdk-jammy
# Set working directory inside container
WORKDIR /app
# Copy the jar file built by Maven/Gradle
COPY target/jwtDashboardBackend-0.0.1-SNAPSHOT.jar app.jar
# Expose port 8080 (Spring Boot default)
EXPOSE 8080
# Command to run the app
ENTRYPOINT ["java","-jar","app.jar"]