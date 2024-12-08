# Use OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven Wrapper files first for dependency resolution
COPY mvnw .
COPY .mvn .mvn

# Copy the rest of the project files
COPY pom.xml .
COPY src ./src

# Ensure the Maven Wrapper has execute permissions
RUN chmod +x mvnw

# Run the Maven build
RUN ./mvnw dependency:resolve
RUN ./mvnw clean install

# Expose the port your application will run on (adjust if needed)
EXPOSE 8080

# Run the generated JAR file
CMD ["java", "-jar", "target/Job-Application-0.0.1-SNAPSHOT.jar"]
