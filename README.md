# Demo Spring Boot Application

A Spring Boot backend that implements:

1. **Problem 1** – compute a custom series term  
2. **Problem 2** – process text replacing runs of “a”  
3. **Problem 3** – login & product CRUD with hashed passwords and encrypted prices  
4. **Problem 4** – secured JSON REST API for mobile

It also includes Swagger UI for interactive API exploration.

---

## Table of Contents

1. [Prerequisites](#prerequisites)  
2. [Clone & Build](#clone--build)  
3. [Configuration (H2)](#configuration-h2)  
4. [Run Locally](#run-locally)  
5. [API Endpoints](#api-endpoints)  
6. [Swagger UI](#swagger-ui)  
7. [Push to GitHub](#push-to-github)  
8. [Deploy to Heroku](#deploy-to-heroku)  
9. [Switching to MySQL](#switching-to-mysql)  
10. [Contributing](#contributing)  
11. [License](#license)

---

## Prerequisites

- **Java 21**  
- **Maven**  
- **Git**

---

## Clone & Build

```bash
git clone https://github.com/<your‑username>/demo-springboot.git
cd demo-springboot
mvn clean package -DskipTests
