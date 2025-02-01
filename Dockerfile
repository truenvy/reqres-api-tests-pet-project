
# Use an official Maven image as the base image
FROM maven:3.9-eclipse-temurin-21 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Run Maven to download dependencies and run the tests
RUN mvn clean test

# Generate the Allure report
RUN mvn allure:report

# Use a lightweight image for viewing the Allure report (optional)
FROM nginx:alpine AS runner

# Copy the generated Allure report into the NGINX container
COPY --from=builder /app/target/site/allure-maven-plugin /usr/share/nginx/html

# Expose the port for viewing the Allure report
EXPOSE 80

# Start NGINX to serve the report
CMD ["nginx", "-g", "daemon off;"]