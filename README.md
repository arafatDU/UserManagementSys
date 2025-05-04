# User Management & Role Assignment System (Clean Architecture)

This project implements a User Management System using Spring Boot, following Clean Architecture principles as specified in the assignment.

## Requirements

*   Java 17 or higher
*   Maven 3.6 or higher

## Architecture

The codebase is structured according to Clean Architecture layers:

*   `domain`: Contains core business entities (User, Role) with no framework dependencies.
*   `application`: Contains use cases (UserService, RoleService) and repository interfaces (ports).
*   `infrastructure`: Contains framework-specific implementations:
   *   `controller`: REST API endpoints and DTOs.
   *   `persistence`: JPA entities, Spring Data repositories, and repository adapters (implementing application interfaces).
*   `config`: Spring Boot configuration (Bean wiring).

## How to Build

Use Maven to build the project:

```bash
mvn clean package
```

This will compile the code, run tests, and create an executable JAR file in the `target/` directory.

## How to Run

Once built, you can run the application using:

```bash
java -jar target/usermanagement-0.0.1-SNAPSHOT.jar
```

The application will start, and the API will be available at `http://localhost:8080`.

The H2 database console will be available at `http://localhost:8080/h2-console`.
Use the following details to connect:
*   **Driver Class:** `org.h2.Driver`
*   **JDBC URL:** `jdbc:h2:mem:testdb`
*   **User Name:** `sa`
*   **Password:** (leave blank)

## How to Test

Unit tests for the application layer can be run using Maven:

```bash
mvn test
```

## API Endpoints for Testing

You can use tools like `curl`, Postman, or Insomnia to test the endpoints.

### 1. Create User

*   **Method:** `POST`
*   **URL:** `http://localhost:8080/users`
*   **Example Request Body:**
    ```json
    {
        "name": "Bob Johnson",
        "email": "bob.j@sample.net"
    }
    ```
*   **Example Success Response (201 Created):** (The actual UUID will vary)
    ```json
    {
        "id": "c4a3b2d1-f6e5-9087-4321-abcdef123456"
    }
    ```
*   **Example Error Response (400 Bad Request - Validation):**
    ```json
    {
        "timestamp": 1678886400000,
        "status": 400,
        "errors": {
            "email": "Email should be valid"
        }
    }
    ```
*   **Example Error Response (400 Bad Request - Duplicate):**
    ```json
    {
        "error": "User with email bob.j@sample.net already exists."
    }
    ```

### 2. Create Role

*   **Method:** `POST`
*   **URL:** `http://localhost:8080/roles`
*   **Example Request Body:**
    ```json
    {
        "roleName": "VIEWER"
    }
    ```
*   **Example Success Response (201 Created):** (The actual UUID will vary)
    ```json
    {
        "id": "0198fedc-a5b6-1234-8765-fedcba987654"
    }
    ```
*   **Example Error Response (400 Bad Request - Validation):**
    ```json
    {
        "timestamp": 1678886401000,
        "status": 400,
        "errors": {
            "roleName": "Role name cannot be blank"
        }
    }
    ```
*   **Example Error Response (400 Bad Request - Duplicate):**
    ```json
    {
        "error": "Role with name VIEWER already exists."
    }
    ```

### 3. Assign Role to User

*   **Method:** `POST`
*   **URL:** `http://localhost:8080/users/{userId}/assign-role/{roleId}`
   *   *Replace* `{userId}` with a valid user UUID (e.g., `c4a3b2d1-f6e5-9087-4321-abcdef123456`).
   *   *Replace* `{roleId}` with a valid role UUID (e.g., `0198fedc-a5b6-1234-8765-fedcba987654`).
*   **Request Body:** (Empty)
*   **Example Success Response (200 OK):**
    ```json
    {
        "message": "Role assigned successfully"
    }
    ```
*   **Example Error Response (404 Not Found - User):**
    ```json
    {
        "error": "User not found with ID: c4a3b2d1-f6e5-9087-4321-abcdef123456"
    }
    ```
*   **Example Error Response (404 Not Found - Role):**
    ```json
    {
        "error": "Role not found with ID: 0198fedc-a5b6-1234-8765-fedcba987654"
    }
    ```

### 4. Get User Details

*   **Method:** `GET`
*   **URL:** `http://localhost:8080/users/{id}`
   *   *Replace* `{id}` with a valid user UUID (e.g., `c4a3b2d1-f6e5-9087-4321-abcdef123456`).
*   **Request Body:** (None)
*   **Example Success Response (200 OK):** (Roles array includes assigned roles)
    ```json
    {
        "id": "c4a3b2d1-f6e5-9087-4321-abcdef123456",
        "name": "Bob Johnson",
        "email": "bob.j@sample.net",
        "roles": [
            "VIEWER" // Role assigned in step 3
        ]
    }
    ```
*   **Example Success Response (200 OK - No Roles):**
    ```json
    {
        "id": "some-other-uuid-...",
        "name": "Charlie Brown",
        "email": "charlie@peanuts.com",
        "roles": []
    }
    ```
*   **Example Error Response (404 Not Found):**
    ```
    (Empty response body with HTTP status 404)
    ```

## Error Handling Summary

*   Validation errors (blank fields, invalid email format) return `400 Bad Request` with details.
*   Resource not found (User or Role by ID) returns `404 Not Found`.
*   Duplicate email or role name during creation returns `400 Bad Request` with a specific error message.
*   Other unexpected server errors return `500 Internal Server Error`. 