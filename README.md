# 📚 Bookstore Microservices Project

## 🚀 Overview

This project is a **Microservices-based Bookstore Application** built using **Spring Boot** and modern distributed system principles.

The system is designed to simulate a real-world scalable backend where each service is independently developed, deployed, and managed.

---

## 🏗️ Architecture

The application follows **Microservices Architecture** with:

* **API Gateway** → Single entry point
* **Service Discovery (Eureka)** → Dynamic service registration
* **Config Server** → Centralized configuration
* **Independent Services** → Each service handles a specific domain

---

## 🧩 Services Included

| Service                    | Description                   |
| -------------------------- | ----------------------------- |
| `admin-service`            | Admin operations              |
| `api-gateway`              | Central routing layer         |
| `cart-service`             | Cart management               |
| `config-server`            | Centralized config management |
| `customer-details-service` | Customer info                 |
| `eureka-server`            | Service discovery             |
| `feedback-service`         | Reviews & feedback            |
| `notification-service`     | Email/SMS notifications       |
| `order-service`            | Order processing              |
| `product-service`          | Product/catalog management    |
| `user-service`             | User management               |
| `wishlist-service`         | Wishlist handling             |

---

## ⚙️ Tech Stack

* **Java 17+**
* **Spring Boot**
* **Spring Cloud**
* **Spring Data JPA**
* **MySQL / H2**
* **Eureka Server**
* **Spring Cloud Gateway**
* **Maven**
* **REST APIs**

---

## 🔄 System Workflow

1. Client sends request to **API Gateway**
2. Gateway routes request to appropriate service
3. Services communicate via REST
4. Service discovery handled via **Eureka**
5. Configurations fetched from **Config Server**

---

## 📂 Project Structure

```
bookstore-microservices/
│
├── api-gateway/
├── eureka-server/
├── config-server/
├── user-service/
├── product-service/
├── cart-service/
├── order-service/
├── wishlist-service/
├── notification-service/
├── feedback-service/
├── admin-service/
├── customer-details-service/
```

---

## 🛠️ Setup Instructions

### 1. Clone Repository

```
git clone git@github.com:Wave-Shot/bookstore.git
cd bookstore
```

### 2. Start Services in Order

1. `config-server`
2. `eureka-server`
3. All microservices
4. `api-gateway`

---

## ▶️ Running a Service

```
cd service-name
mvn spring-boot:run
```

---

## 🔐 Key Concepts Implemented

* Microservices architecture
* API Gateway routing
* Service discovery (Eureka)
* Centralized configuration
* REST-based communication
* Modular service design

---

## 📌 Future Enhancements

* JWT Authentication & Authorization
* Docker containerization
* Kubernetes deployment
* Kafka/RabbitMQ integration
* Distributed tracing & monitoring

---

## 👨‍💻 Author

**V Srihari**
B.Tech CSE (IT)
SRM Institute of Science and Technology

---

## ⭐ Notes

This project is built for:

* Learning microservices architecture
* Backend system design practice
* Real-world distributed system understanding

---
