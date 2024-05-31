# Spring Boot Application with MySQL using Docker Compose

This project demonstrates how to run a Spring Boot application with MySQL using Docker Compose.

## Prerequisites

- Docker
- Docker Compose

## Getting Started

1. Clone the repository:

   ```sh
   git clone https://github.com/hakimamarullah/tasks-management-samir
   ```

2. Navigate to the project directory:

   ```sh
   cd tasks-management-samir
   ```

3. Start MySQL container:

   ```sh
   docker-compose up --build -d
   ```

4. Run application (**skip this step if the springboot container is already running from previous step**):

   ```sh
   ./mvnw spring-boot:run
   ```
5. Access the Spring Boot application at [http://localhost:8080](http://localhost:8080).

## Testing the Application

1. Open your web browser and navigate to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui.html) to access the Swagger UI.

2. Use the Swagger UI to test the various endpoints exposed by the Spring Boot application.

## Stopping the Application

1. [non-detached] To stop the application and remove the containers, press `Ctrl + C` in the terminal where `docker-compose` is running.
2. If you use `-d` option then simple run this command to stop all containers `docker-compose down`


## Authentication
I implement a robust JWT-based authentication mechanism in my Spring Boot application. This setup allows users to log in and receive a JWT token, which they must include in the Authorization header of their subsequent requests. The JWT token is validated on each request to ensure that only authenticated users can access protected resources. This approach ensures stateless, scalable, and secure authentication for my API.

Here's how I implemented it in the SecurityConfig class:

SecurityConfig
In the SecurityConfig class, I configure Spring Security to manage the authentication and authorization aspects of the application. The configuration disables CSRF protection for simplicity, sets the session management to stateless (since JWT is stateless), and defines the endpoints that require authentication.

## Database Design
![database_schema.png](images%2Fdatabase_schema.png)

User 1 ---> 0..* Task (User has many task)

## Database DDL Script
```sql
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`email`),
  UNIQUE KEY (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
```sql
CREATE TABLE `tasks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `completed` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```