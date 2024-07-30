# URL Shortener App

This is a URL Shortener application that allows users to shorten long URLs. It has a frontend built with React, a backend developed using Spring Boot, and a PostgreSQL database. Both the frontend and backend are containerized using Docker.

## Features
- Shorten long URLs
- Redirect shortened URLs to the original URL
- View all shortened URLs
- Delete shortened URLs

## Technologies Used
- **Frontend**: React
- **Backend**: Spring Boot
- **Database**: PostgreSQL
- **Containerization**: Docker

## Prerequisites
Ensure you have the following installed on your machine:
- [Docker](https://www.docker.com/get-started)
- [Node.js](https://nodejs.org/) (for frontend development)
- [Java JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (for backend development)
- [PostgreSQL](https://www.postgresql.org/download/)

## Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/ShaunaMartyn123/Url_Shortener.git
    cd Url_Shortener
    ```

2. **Setup the frontend:**

    ```bash
    cd frontend
    npm install
    ```

3. **Setup the backend:**

    ```bash
    cd Url_shortener
    mvnw clean install
    ```

## Running the Application

### With Docker

#### Building and Running the Backend Container

1. Create a JAR file:

    ```bash
    mvn clean package
    ```

2. Build a Docker container called `url_shortener`:

    ```bash
    docker build -t url_shortener .
    ```

3. Run the Docker image called `url_shortener`:

    ```bash
    docker run -d -p 8080:8080 url_shortener
    ```

4. Check what port it is running on:

    ```bash
    docker ps
    ```

#### Building and Running the Frontend Container

1. Navigate to the frontend directory:

    ```bash
    cd ../frontend
    ```

2. Build a Docker container called `react-app`:

    ```bash
    docker build -t react-app .
    ```

3. Run the Docker container:

    ```bash
    docker run -d -p 3000:3000 react-app
    ```

4. Check what port it is running on:

    ```bash
    docker ps
    ```

### Without Docker

#### Run the Backend

1. Navigate to the backend directory and start the Spring Boot application:

    ```bash
    cd ../backend
    ./mvnw spring-boot:run
    ```

#### Run the Frontend

1. Navigate to the frontend directory and start the React application:

    ```bash
    cd ../frontend
    npm start
    ```

## Usage

1. **Access the application:**

    - Open your web browser and go to `http://localhost:3000` for the frontend displaying all URLs with delete buttons.
    - Open your web browser and go to `http://localhost:8080/api/urls` to access the backend URL displaying all URLs in JSON format.

2. **Shorten a URL:**

    Enter a long URL in the input field and click the "Shorten" button. The shortened URL will be displayed.

3. **View all shortened URLs:**

    The list of all shortened URLs can be viewed on the main page.

4. **Delete a shortened URL:**

    Click the "Delete" button next to a shortened URL to remove it from the list.


