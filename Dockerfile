# Use a base image with Java 22
FROM openjdk:22-jdk

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and configuration files
COPY .mvn .mvn
COPY mvnw pom.xml ./

# Download Maven dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src ./src

# Build the project
RUN ./mvnw clean package -DskipTests

# Copy the built jar to the final image
COPY target/Url_shortener-0.0.1-SNAPSHOT.jar /app/url-shortener.jar

# Expose the port your application will run on
EXPOSE 8080

# Command to run your application
ENTRYPOINT ["java", "-jar", "url-shortener.jar"]
