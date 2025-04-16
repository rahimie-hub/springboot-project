# Demo Spring Boot Application

## A Spring Boot backend that implements:

1. **Problem 1** – compute a custom series term  
   - **Controller:** `https://demo-spring-3b137cf374fa.herokuapp.com/test/series?n=1000`  
2. **Problem 2** – process text replacing runs of “a”  
   - **Controller:** `https://demo-spring-3b137cf374fa.herokuapp.com/test/text-process?input=abcdaabcdeabaaacbfaaaabcab`  
3. **Problem 3** – login & product CRUD with hashed passwords and encrypted prices  
   - **Controllers:**  
     - `https://demo-spring-3b137cf374fa.herokuapp.com/auth/login?username=admin&password=admin`  
     - `https://demo-spring-3b137cf374fa.herokuapp.com/products/add?name=tomato&price=10`  
4. **Problem 4** – secured JSON REST API  
   - **Controller:** `https://demo-spring-3b137cf374fa.herokuapp.com/api/product/1`  

   It also includes Swagger UI for interactive API exploration.
---
## LIVE-DEMO

https://demo-spring-3b137cf374fa.herokuapp.com/swagger-ui/index.html
---

## Prerequisites

- **Java 21**  
- **Maven**  
- **Git**

---

## Clone & Build

```bash
git clone https://github.com/rahimie-hub/springboot-project.git
cd demo-springboot
mvn clean package -DskipTests 