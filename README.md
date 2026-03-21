# User Management API

A Spring Boot backend application with JWT Authentication, Role-Based Authorization, Swagger UI, and Postgresql integration.

---

## 🚀 Features

- 🔐 JWT Authentication
- 👥 Role-Based Access Control (ADMIN / USER)
- 🔑 Password Hashing using BCrypt
- 📄 Swagger API Documentation
- 🧾 DTO-based API responses (no password exposure)
- 🗄️ postgresql Database Integration
- ⚙️ Environment-based configuration

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- Postgresql
- JWT (jjwt)
- Swagger (springdoc-openapi)

---

## 📂 Project Structure
com.amrit.user_management_api


├── controller

├── service

├── repository

├── entity

├── dto

├── security

├── exception

└── config


---

## ⚙️ Setup Instructions

```bash
1. Clone the repository

git clone <your-repo-url>
cd user-management-api

2. Configure Environment Variables

Set the following environment variable:

DB_PASSWORD=your_postgresql_password

👉 In IntelliJ:

Run Configurations → Environment Variables

3. Application Properties

application.properties

spring.application.name=user-management-api

spring.datasource.url=jdbc:postgresql://localhost:5432/user_mgmt_db
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.profiles.active=dev
4. Run the Application
mvn spring-boot:run

```

### **🔐 Authentication Flow**
1. Login
POST /auth/login


{
"username": "user1",
"password": "password"
}

👉 Response:
JWT Token

2. Use Token

In headers:
Authorization: Bearer <your-token>

### 📄 Swagger UI

Open in browser:
http://localhost:8080/swagger-ui/index.html

Steps:
Call /auth/login
Copy token
Click Authorize

Enter:
Bearer <token>

### 🔑 Roles & Access

API	Access
* Create User	- ADMIN
* Delete User	- ADMIN
* Get Users	- USER, ADMIN

### 🧪 Testing

**_Run tests:_**
mvn test

**_Skip tests:_**
mvn clean install -DskipTests


### 📌 Future Improvements

* Refresh Tokens
* Unit & Integration Tests
