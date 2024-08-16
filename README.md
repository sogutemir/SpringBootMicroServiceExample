# SpringBootMicroServiceExample

## Overview
This project demonstrates a microservices architecture using Spring Boot, Docker, and Docker Compose. It consists of two main services: `user-service` and `account-service`, which communicate with each other using REST APIs.

## Services
### User Service
- **Port:** 8082
- **Description:** Manages user-related operations.
- **Endpoints:**
    - `POST /api/users` - Create a new user.
    - `GET /api/users/{id}` - Retrieve a user by ID.
    - `PUT /api/users/{id}` - Update a user by ID.
    - `DELETE /api/users/{id}` - Delete a user by ID.
    - `GET /api/users/account/{accountId}` - Retrieve a user by account ID.

### Account Service
- **Port:** 8081
- **Description:** Manages account-related operations.
- **Endpoints:**
    - `POST /api/accounts` - Create a new account.
    - `GET /api/accounts/{id}` - Retrieve an account by ID.
    - `GET /api/accounts` - Retrieve all accounts.
    - `PUT /api/accounts/{id}` - Update an account by ID.
    - `DELETE /api/accounts/{id}` - Delete an account by ID.
    - `GET /api/accounts/user/{userId}` - Retrieve an account by user ID.

## Prerequisites
- Docker
- Docker Compose
- Kubernates 
- Java 17+
- Maven

## Running the Application
1. **Build the services:**
   ```sh
   mvn clean install
   

2. **Start the services:**
   ```sh
    docker-compose up --build
    ```
3. **Access the services:** 
   - **Account Service:** http://localhost:8081
   - **User Service:** http://localhost:8082

## Configuration
- **Database:** The services use an in-memory H2 database by default. You can change the database configuration in the `application.properties` file of each service.
- **Docker Compose:** The `docker-compose.yml` file defines the services and their configurations. You can change the configuration as needed.

## Example Requests
- **Create a new account:**
    ```sh
    curl -X POST http://localhost:8081/api/accounts -H "Content-Type: application/json" -d '{"userId": 1, "balance": 1000}'
    ```
- **Create a new user:**
    ```sh
    curl -X POST http://localhost:8082/api/users -H "Content-Type: application/json" -d '{"name": "John Doe", "email": "
    ```
  
## Troubleshooting
- **Port Already in Use:** If you encounter a `Port already in use` error, make sure that the default ports `8081` and `8082` are available on your system.
- **500 Internal Server Error:** Ensure the services can resolve each other's hostnames. Use the service names defined in docker-compose.yml for inter-service communication.
- **Database Connection Issues:** Verify the database configuration in the application.properties files and ensure the PostgreSQL container is running.
  

## Last Sentence
The main purpose of this project is to establish communication between microservices with RestTemplate.



