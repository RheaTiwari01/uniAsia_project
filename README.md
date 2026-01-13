
# UniAsia Project - Read-Only REST API Service

A robust, read-only backend service built with Java Spring Boot that provides REST APIs for data retrieval. This project demonstrates clean architecture principles with a focus on GET-only operations for a dashboard or external data consumers.

##  Table of Contents

- [Project Overview](#-project-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Database Schema](#-database-schema)
- [API Documentation](#-api-documentation)
- [Setup Instructions](#-setup-instructions)
- [Usage Examples](#-usage-examples)
- [Project Structure](#-project-structure)
- [Contributing](#-contributing)

---

##  Project Overview

This project implements a **read-only backend service** exposing REST APIs using GET requests only. It's designed to fetch and display data for frontend dashboards or external consumers while maintaining clean separation of concerns through proper layered architecture.

### Objective
Build clean, well-structured GET APIs that retrieve data from MySQL database and return it in consistent JSON format with proper error handling and HTTP status codes.

### Scope
- Fetch all records from database tables
- Fetch specific record details by ID
- Filter data using query parameters
- Handle errors gracefully with appropriate HTTP status codes
- Follow industry-standard REST API conventions

---

##  Features

- **Read-Only APIs**: Secure and multiple  GET-only endpoints 
- **Clean Architecture**: Proper separation of Controller, Service, and Repository layers
- **Error Handling**: Comprehensive error handling with meaningful HTTP status codes
- **Query Parameters**: Support for filtering, sorting, and pagination
- **JSON Responses**: Consistent, well-structured JSON response format
- **MySQL Integration**: Robust database connectivity using Spring Data JPA
- **Input Validation**: Request validation and sanitization
- **Logging**: Structured logging for debugging and monitoring

---

##  Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17+ | Programming Language |
| **Spring Boot** | 3.x | Backend Framework |
| **Spring Data JPA** | - | ORM & Data Access |
| **MySQL** | 8.0+ | Relational Database |
| **Maven** | 3.8+ | Dependency Management |
| **Lombok** | - | Reduce Boilerplate Code |
| **Hibernate** | - | JPA Implementation |

---

## 🏗️ Architecture

This project follows a **three-layered architecture** pattern:

```
┌─────────────────────────────────────────────┐
│          Controller Layer                    │
│  (Handles HTTP requests/responses)           │
│  - Input validation                          │
│  - Response formatting                       │
└──────────────────┬──────────────────────────┘
                   │
┌──────────────────▼──────────────────────────┐
│          Service Layer                       │
│  (Business logic & processing)               │
│  - Data transformation                       │
│  - Business rules                            │
└──────────────────┬──────────────────────────┘
                   │
┌──────────────────▼──────────────────────────┐
│          Repository Layer                    │
│  (Data access & persistence)                 │
│  - Database queries                          │
│  - JPA operations                            │
└──────────────────┬──────────────────────────┘
                   │
┌──────────────────▼──────────────────────────┐
│          MySQL Database                      │
│  (Data storage)                              │
└─────────────────────────────────────────────┘
```

### Layer Responsibilities

**Controller Layer** (`@RestController`)
- Receives HTTP GET requests
- Validates input parameters
- Delegates to service layer
- Formats JSON responses
- Handles HTTP status codes

**Service Layer** (`@Service`)
- Implements business logic
- Processes and transforms data
- Handles exceptions
- Calls repository methods

**Repository Layer** (`@Repository` / JPA Repository)
- Interacts with MySQL database
- Executes queries using Spring Data JPA
- Returns entity objects

---

## 🗄️ Database Schema

### Example Entity: `User`

```sql
Id
name
email
```



---

## 📡 API Documentation
### Endpoints

#### 1. **Get All Users**
```http
GET /getall
```

**Response (200 OK):**
```json
{
  
  
      "id": 1,
      "name": "johndoe",
      "email": "john@example.com",
  
}
```

#### 2. **Get User by ID**
```http
GET /getbyid/{id}
```

**Parameters:**
- `id` (path) - User ID (Long)

**Response (200 OK):**
```json
{
  
    "id": 1,
    "name": "johndoe",
    "email": "john@example.com",
    
}
```

**Error Response (404 Not Found):**
```json
{
  "status": "error",
  "data": null,
  "message": "User not found with id: 1",
  "timestamp": "2025-01-14T12:00:00"
}
```

#### 3. **Get Users with Filters**
```http
GET  /getbyname?name=Joh
```



**Response (200 OK):**
```json
{

"id": 1,
"name": "johndoe",
"email": "john@example.com",

}
```


### HTTP Status Codes

| Code | Description |
|------|-------------|
| `200` | Success - Resource found and returned |
| `400` | Bad Request - Invalid parameters |
| `404` | Not Found - Resource doesn't exist |
| `500` | Internal Server Error - Server-side error |

---

##  Setup Instructions

### Prerequisites

Ensure you have the following installed:
- **Java 17** or higher ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.8+** ([Download](https://maven.apache.org/download.cgi))
- **MySQL 8.0+** ([Download](https://dev.mysql.com/downloads/))
- **Git** ([Download](https://git-scm.com/downloads))
- **Postman** or similar API testing tool (optional)

### Step 1: Clone the Repository

```bash
git clone https://github.com/RheaTiwari01/uniAsia_project.git
cd uniAsia_project
```

### Step 2: Configure  project
**Update `application.properties`:**

Navigate to `src/main/resources/application.properties` and configure:

```properties
# Server Configuration
server.port=8080

# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/uniasia_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


```

### Step 3: Install Dependencies

```bash
mvn clean install
```

### Step 4: Run the Application

```bash
mvn spring-boot:run
```

Or run directly:

```bash
java -jar target/uniAsia-project-0.0.1-SNAPSHOT.jar
```


---

##  Project Structure

```text
src/main/java/com/java/portfolio
├── controller
│   └── CustomerController.java
├── service
│   └── CustomerService.java
├── repository
│   └── CustomerRepo.java
├── entity
│   └── Customer.java
```
---
### Key Files

- **`pom.xml`** - Maven dependencies and project configuration
- **`application.properties`** - Database and application settings
- **Controller classes** - REST endpoint definitions
- **Service classes** - Business logic implementation
- **Repository interfaces** - Database query methods
- **Model/Entity classes** - Database table mappings

---



##  Troubleshooting

### Common Issues

**1. Database Connection Error**
```
Error: Access denied for user 'root'@'localhost'
```
**Solution:** Verify MySQL credentials in `application.properties`

**2. Port Already in Use**
```
Error: Port 8080 is already in use
```
**Solution:** Change port in `application.properties`:
```properties
server.port=8081
```

**3. Table Not Found**
```
Error: Table 'emp_try.customer' doesn't exist
```
**Solution:** Set `spring.jpa.hibernate.ddl-auto=create` initially, then change to `update`

---