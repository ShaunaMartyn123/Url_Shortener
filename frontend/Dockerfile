# Use a Node.js base image to build the React app
FROM node:16-alpine AS build

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the application
RUN npm run build

# Lightweight web server to serve the React app
FROM nginx:alpine

# Copy the built React app to the web server's root directory
COPY --from=build /app/build /usr/share/nginx/html

# Expose the port the web server runs on
EXPOSE 80

# Define the command to run the web server
CMD ["nginx", "-g", "daemon off;"]
