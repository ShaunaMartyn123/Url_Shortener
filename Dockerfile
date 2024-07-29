FROM openjdk:17-jdk-alpine

# Define a build argument for the JAR file location
ARG JAR_FILE=target/Url_shortener-0.0.1-SNAPSHOT.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} /Url_shortener.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/Url_shortener.jar"]
