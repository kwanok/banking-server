# Use a base image with Java 8 installed
FROM openjdk:17-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy the executable JAR file and the application.properties file to the working directory
COPY ../build/libs/banking-1.0.0-SNAPSHOT.jar .
COPY ../src/main/resources/application.yaml .

# Expose port 8080 to the Docker host
EXPOSE 8080

# Run the JAR file when the container starts
CMD ["java", "-jar", "banking-1.0.0-SNAPSHOT.jar"]