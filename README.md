# 🚀 User Management API (Spring Boot + Docker + PostgreSQL)

A production-ready backend application built using **Spring Boot**, featuring authentication, role-based access, pagination, filtering, and cloud deployment.

---

## 🌐 Live Demo

* 🔗 API Base URL: `https://user-management-api-z6et.onrender.com`
* 🔗 Swagger UI: `https://user-management-api-z6et.onrender.com/swagger-ui/index.html`
* 🔗 Health Check: `https://user-management-api-z6et.onrender.com/actuator/health`

---

## 🧰 Tech Stack

* **Backend:** Spring Boot, Spring Security, Spring Data JPA
* **Database:** PostgreSQL (Render) / MySQL (Local)
* **Authentication:** JWT (JSON Web Token)
* **Build Tool:** Maven
* **Containerization:** Docker, Docker Compose
* **Cloud Deployment:** Render

---

## ✨ Features

* 🔐 JWT-based Authentication (Login API)
* 👥 Role-based Authorization (USER / ADMIN)
* 📄 Pagination & Sorting APIs
* 🔍 Dynamic Filtering using Specifications
* 📊 Actuator Health Monitoring
* 📘 Swagger API Documentation
* 🐳 Dockerized setup (App + DB)
* ☁️ Deployed on Render (Production-ready)

---

## 📦 API Endpoints

### 🔑 Auth APIs

* `POST /auth/login` → Generate JWT token

### 👤 User APIs

* `POST /users` → Create user
* `GET /users/userlist` → Get all users
* `GET /users/page` → Paginated users
* `GET /users/search` → Filter users

> 🔐 Protected APIs require Bearer Token

---

## ⚙️ Local Setup (Without Docker)

```bash
# Clone repo
git clone <your-repo-url>

# Navigate
cd user-management-api

# Build project
mvn clean install

# Run app
mvn spring-boot:run
```

---

## 🐳 Docker Setup (Recommended)

### ▶️ Run Application

```bash
docker compose up --build
```

### ⛔ Stop Application

```bash
docker compose down -v
```

---

## 🔐 Environment Variables

Create a `.env` file in root:

```env
DB_URL=jdbc:mysql://mysql-db:3306/user_mgmt_db
DB_USERNAME=<<your_db_username_here>>
DB_PASSWORD=<<your_db_password_here>>
JWT_SECRET=<<your_secret_key_here>>
```

---

## 🧪 Running Tests

```bash
mvn test
```

---

## ⚙️ Configuration

### Profiles

* `dev` → Local development (MySQL, debug logs)
* `prod` → Production (PostgreSQL, optimized logs)

---

## 🚀 Deployment (Render)

1. Push code to GitHub
2. Create Web Service in Render
3. Add environment variables:

    * `DB_URL`
    * `DB_USERNAME`
    * `DB_PASSWORD`
    * `JWT_SECRET`
4. Deploy 🚀

---

## 🛠️ Future Improvements

* ✅ Global Exception Handling
* 🔄 Refresh Token mechanism
* 📈 Rate Limiting
* 🔐 Secure Swagger with Authentication
* ⚙️ CI/CD Pipeline (GitHub Actions)
* 🌍 Custom Domain + Nginx

---

## 👨‍💻 Author

**Amrit Singh**

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!
