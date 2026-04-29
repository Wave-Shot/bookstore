# Bookstore Microservices — Architecture

## Overview
This project follows a microservices architecture pattern where each service:
- Owns its own database (Database-per-Service pattern)
- Communicates via REST (synchronous) or Kafka (asynchronous)
- Registers with Eureka for service discovery
- Fetches config from Spring Cloud Config Server

## Service Communication
- Synchronous: Spring Cloud OpenFeign
- Asynchronous: Apache Kafka

## Security
- JWT-based stateless authentication
- API Gateway handles token validation

## Infrastructure
- Service Discovery: Netflix Eureka (port 8761)
- Config Server: Spring Cloud Config (port 8888)
- API Gateway: Spring Cloud Gateway (port 8080)
