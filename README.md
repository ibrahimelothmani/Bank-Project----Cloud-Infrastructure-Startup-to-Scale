# Bank Project - Cloud Infrastructure (Startup to Scale)

A Spring Boot banking service that supports core account operations:
- Deposit
- Withdraw
- Print account statement (latest transaction first)

The project includes:
- Java 21 + Spring Boot 3
- Maven build pipeline
- Docker multi-stage build
- GitHub Actions CI workflow

---

## Tech Stack

- Java 21
- Spring Boot 3.5.3
- Maven
- JUnit 5 / Spring Boot Test
- Docker
- GitHub Actions

---

## Project Structure

```text
.
|-- src/
|   |-- main/
|   |   |-- java/com/example/bank/
|   |   |   |-- BankApplication.java
|   |   |   |-- Model/
|   |   |   |   `-- Transaction.java
|   |   |   `-- Service/
|   |   |       |-- AccountService.java
|   |   |       `-- AccountServiceImpl.java
|   |   `-- resources/
|   |       `-- application.yml
|   `-- test/java/com/example/bank/
|       `-- BankApplicationTests.java
|-- Dockerfile
|-- pom.xml
`-- .github/workflows/ci.yml
```

---

## Current Application Flow

1. `BankApplication` boots Spring.
2. It creates an account service instance.
3. Service methods process deposits and withdrawals.
4. Transactions are stored in memory.
5. Statement is printed to console in reverse chronological order.

---

## Architecture

### Current Architecture (Simple Service-First)

This codebase currently follows a simple layered style:

- **Entry point**: `BankApplication`
- **Business layer**: `AccountService` + `AccountServiceImpl`
- **Domain model**: `Transaction`
- **Storage**: in-memory list inside the service

This is a good starting point for local development and coding exercises.

### Recommended "Good" Architecture for Growth

For startup-to-scale evolution, use a **Clean / Hexagonal Architecture**:

```text
Clients (REST/API, CLI, Events)
        |
        v
Application Layer (Use Cases)
        |
        v
Domain Layer (Entities, Domain Rules)
        |
        v
Ports (Repository Interfaces, External Service Interfaces)
        |
        v
Adapters (DB, Cache, Message Broker, External APIs)
```

#### Suggested package layout

```text
com.example.bank
|-- domain/
|   |-- model/
|   `-- service/
|-- application/
|   `-- usecase/
|-- infrastructure/
|   |-- persistence/
|   |-- messaging/
|   `-- config/
|-- interfaces/
|   `-- rest/
`-- BankApplication.java
```

#### Why this architecture is better

- Clear separation of business logic from frameworks
- Easier unit and integration testing
- Easier migration from in-memory storage to database
- Better maintainability as features and teams grow
- Supports cloud-native scaling (stateless app + externalized state)

---

## Getting Started

### Prerequisites

- Java 21
- Maven 3.9+
- Docker (optional, for container run)

### Run locally

```bash
mvn clean spring-boot:run
```

### Run tests

```bash
mvn test
```

### Package artifact

```bash
mvn clean package
```

---

## Docker

Build image:

```bash
docker build -t bank .
```

Run container:

```bash
docker run --rm bank
```

---

## CI/CD

The GitHub Actions workflow (`.github/workflows/ci.yml`) currently performs:

1. Checkout code
2. Setup Java 21
3. Maven package (`-DskipTests`)
4. Maven test
5. Docker build
6. Docker Hub login
7. Push image to Docker Hub

Required GitHub repository secrets:

- `DOCKER_USERNAME`
- `DOCKER_PASSWORD`

---

## Roadmap (Recommended Next Steps)

- Add REST controllers for account operations and statement retrieval
- Add validation and centralized exception handling
- Replace in-memory transactions with persistent storage (PostgreSQL)
- Add repository layer and migration tool (Flyway/Liquibase)
- Add structured logging, health checks, and metrics (Actuator + Prometheus)
- Add container orchestration manifests (Kubernetes/Helm) for scale
