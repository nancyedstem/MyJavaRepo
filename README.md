# Overview

## Database Setup

This setup is following the postgres setup using docker.

Run the command docker pull postgres
Start the docker container for postgres
docker run --name odw-postgres-local -p5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres


# Technology Stack
- **Postgres 13.5**

- **Spring Boot 2.7.4**

- **Java 11**

- **Junit5**

- **Swagger**

- **Postman**

# Running the application
### Add an application-local.properties to match the postgres setup

# Verification

- Run the backend using the command mvn spring-boot:run -Dspring-boot.run.profiles=local in your local machine.

# Swagger url

http://localhost:8080/swagger-ui/

# Postman url

 ## Postman doc collection is also added
 ###### Grave API should be run before ownerAPI
 http://localhost:8080/api/grave/save
 http://localhost:8080/api/owner/save