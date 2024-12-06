# Use OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Install Maven Wrapper and run the build using the wrapper
RUN chmod +x mvnw
RUN ./mvnw clean install  # Use the Maven Wrapper to build the project

# Run the generated JAR file (adjust with the actual filename from your target directory)
CMD ["java", "-jar", "target/Job-Application-0.0.1-SNAPSHOT.jar"]
