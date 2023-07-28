# User API Project

## Description

This project is an API that interacts with an external service to obtain user information. It allows operations such as getting all users, getting a user by ID, creating a new user, updating an existing user and deleting a user.
## Tecnologies

- Java
- Spring Boot
- Spring Web
- Spring RestTemplate
- Swagger (Springfox) for API documentation

## Description

The Users API is developed in Java using the Spring Boot framework. The application interacts with an external service using Spring RestTemplate to obtain and manage user information.

## API endpoints

- `GET /users`: Get all available users from the external service.
- `GET /users/{id}`: Gets a specific user by ID from the external service.
- `POST /users`: Creates a new user and adds it to the external service.
- `PUT /users/{id}`: Updates the information of an existing user in the external service.
- `DELETE /users/{id}`: Deletes an existing user from the external service.

## Documentation with Swagger

The API is documented using Swagger (Springfox) for easy understanding and testing. To access the interactive documentation, you can visit the following URL once the application is running: `http://localhost:8001/swagger-ui/index.html`.

## Instructions for executing the project

1. Clone this repository in your local environment.
2. Make sure you have Java and Maven installed on your system.
3. Run mvn clean install to compile and build the project.
4. Run mvn spring-boot:run to start the application.
5. Once the application is running, you can access the endpoints through `http://localhost:8001/users`.

## Additional notes

- The external service used in this project is "https://jsonplaceholder.typicode.com/users". Make sure that the service is available and accessible from your environment.
