# Use an OpenJDK image
FROM openjdk:11-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Install Maven and build the project
RUN apt-get update && apt-get install -y maven
RUN mvn clean install  # For Maven projects

# Run the generated JAR file (adjust with the actual filename from your target directory)
CMD ["java", "-jar", "target/Job-Application-0.0.1-SNAPSHOT.jar"]
