# User Management & Role Assignment System

## Overview
Clean Architecture (Uncle Bob) example in Spring Boot, in-memory H2 DB, with Swagger UI + H2 console.

## Requirements
- Java 17+
- Maven

## Run
```bash
mvn clean spring-boot:run
```

Access API at http://localhost:8080

H2 console at http://localhost:8080/h2-console

Swagger UI at http://localhost:8080/swagger-ui.html

yaml
Copy
Edit

---

## Running the Application

1. **Clone & Build**  
   ```bash
   git clone <repo-url>
   cd user-management
   mvn clean package
Run

bash
Copy
Edit
mvn spring-boot:run
Test

bash
Copy
Edit
mvn test
Browse

API: http://localhost:8080

H2 Console: http://localhost:8080/h2-console (JDBC URL jdbc:h2:mem:db)

Swagger UI: http://localhost:8080/swagger-ui.html