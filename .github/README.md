# Mediscreen09 Project

Mediscreen09 is a multi-module Maven project that includes various services and a client user interface. This project is designed to provide a comprehensive healthcare solution.

## Modules

This project consists of the following modules:

- `patient-service`: Handles patient-related functionalities.
- `note-service`: Manages medical notes for patients.
- `assessment-service`: Performs health assessments based on patient data.
- `discovery-service`: Service discovery for microservices.
- `client-ui`: User interface for interacting with the services.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) 17
- Apache Maven
- Docker (optional, for containerization)

## Building the Project

To build the project, run the following command from the project's root directory:

```bash
mvn clean install
```

This command will compile the project, run tests, and package it as a Maven POM.

## Running the Services
You can run each service individually. Navigate to the respective module directory (e.g., patient-service) and use the following command to run the service:

```bash
mvn spring-boot:run
```
## Swagger API Documentation
This project uses Swagger for API documentation. You can access the Swagger UI for each service by visiting the following URLs:

- `patient-service`: http://localhost:8080/swagger-ui.html
- `note-service`: http://localhost:8081/swagger-ui.html
- `assessment-service`: http://localhost:8082/swagger-ui.html

## Dependencies
- Springdoc OpenAPI Starter: v2.1.0
- Project Lombok: v1.18.28
- Spring Cloud OpenFeign: v4.0.4
- Spring Cloud Commons: v4.0.4
- Spring Boot Test: v3.1.2

## Contributing
If you would like to contribute to this project, please follow our contribution guidelines.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

