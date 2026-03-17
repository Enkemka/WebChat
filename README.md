# Chat Application (Spring Boot + MongoDB)

## Overview

This project is a **full-stack chat application** built to demonstrate modern backend architecture using **Spring Boot**, **MongoDB**, and a **JavaScript frontend**. The application allows users to authenticate, create chats, send messages, and manage chat members.

The goal of the project is not only to build a working messaging system but also to explore **scalable backend design, security practices, and system architecture improvements**.

---

# Tech Stack

## Backend

* **Java**
* **Spring Boot**
* **Spring Security (JWT Authentication)**
* **Spring Web**
* **MongoDB**
* **MongoTemplate**
* **REST API architecture**

## Frontend

* **Vanilla JavaScript**
* **HTML**
* **CSS**
* **Fetch API**

## Database

* **MongoDB (NoSQL)**
* Document-based chat storage
* Embedded message collections

## Development Tools

* **Maven**
* **Postman / API testing tools**
* **LocalStorage (token management)**
* **Git / GitHub**

---

# Core Features

## Authentication

* JWT based authentication
* Token stored in browser `localStorage`
* Protected API routes using Spring Security

## Chat Management

* Create chat groups
* View chat details
* Add users to chats
* Delete chats

## Messaging

* Send messages to a chat
* Retrieve messages in chronological order
* Message metadata:

  * sender id
  * sender name
  * creation timestamp

## REST Endpoints

Example endpoints:

```
POST   /auth/login
POST   /auth/register

GET    /chat/{chatId}/view
PATCH  /chat/{chatId}/add
POST   /chat/create
DELETE /chat/{chatId}
```

---

# Project Structure

```
backend
│
├── controller
│   └── chatController
│
├── service
│   └── chatService
│
├── model
│   ├── chat
│   └── message
│
├── security
│   └── JWT configuration
│
└── configuration
    └── Mongo configuration
```

Frontend:

```
frontend
│
├── index.html
├── chat.html
├── login.html
│
├── js
│   ├── auth.js
│   └── chat.js
│
└── css
```

---

# Example Chat Document (MongoDB)

```json
{
  "_id": "chatId",
  "name": "groupTwo",
  "usersInChatId": [
    "user1",
    "user2"
  ],
  "messagesInChat": [
    {
      "_id": "msg1",
      "senderId": "user1",
      "senderName": "Alice",
      "message": "Hello",
      "creationDate": "2025-10-03T18:00:00"
    }
  ]
}
```

---

# Security

Security is handled using **JWT tokens** and **Spring Security**.

Features include:

* Authenticated API routes
* Bearer token verification
* Role-based security (planned expansion)

Future improvements will include:

* Role hierarchy
* Permission based access
* Resource level authorization

---

# Current Architecture

Client → REST API → Service Layer → MongoDB

```
Frontend
   │
HTTP Requests
   │
Spring Controller
   │
Service Layer
   │
MongoTemplate
   │
MongoDB
```

---

# Future Improvements

This project is intended to evolve into a more **production-ready system**.

## Performance Improvements

### Database Indexing

Add indexes for frequently queried fields:

* chatId
* senderId
* creationDate

Benefits:

* faster message retrieval
* better scalability

---

### Caching

Introduce caching for frequently accessed data.

Possible technologies:

* Redis
* Spring Cache

Use cases:

* chat metadata
* recent messages
* user profiles

---

### Load Balancing

Prepare the system for horizontal scaling using:

* multiple backend instances
* reverse proxy load balancers

Possible tools:

* Nginx
* Kubernetes
* Docker containers

---

### Message Pagination

Currently messages are loaded entirely.

Future improvement:

```
GET /chat/{chatId}/messages?page=1&size=50
```

Benefits:

* faster loading
* lower memory usage

---

### WebSockets (Real-Time Messaging)

Current implementation relies on HTTP requests.

Future improvement:

* WebSocket based real-time messaging
* live message updates
* typing indicators

Technologies:

* Spring WebSocket
* STOMP protocol

---

### Better Validation

Introduce stronger input validation:

* message length limits
* username rules
* request schema validation

Technologies:

* Hibernate Validator
* Spring Validation

---

### Stronger Role Enforcement

Implement role-based access control:

Roles could include:

* Admin
* Chat owner
* Member

Permissions:

* delete chat
* add/remove users
* moderate messages

---

### Rate Limiting

Prevent spam and abuse.

Possible implementations:

* API rate limiting
* user message limits

Tools:

* Redis
* API Gateway rules

---

### Message Search

Add ability to search messages:

```
GET /chat/{chatId}/search?q=hello
```

Using:

* MongoDB text indexes

---

### Observability

Improve monitoring and debugging:

* request logging
* metrics
* tracing

Tools:

* Prometheus
* Grafana
* Spring Actuator

---

# Running the Project

## Backend

1. Start MongoDB
2. Run Spring Boot application

```
mvn spring-boot:run
```

Backend will run on:

```
http://localhost:8080
```

---

## Frontend

Open the frontend with a local server (for example):

```
Live Server (VSCode)
```

Example URL:

```
http://127.0.0.1:5500
```

---

# Learning Goals

This project focuses on learning:

* REST API architecture
* Spring dependency injection
* authentication and security
* MongoDB data modeling
* frontend/backend integration
* scalable backend design

---

# Status

Current stage: **Functional prototype**

Next milestones:

* real-time messaging
* production-grade security
* scalability improvements
* infrastructure enhancements

---



Personal backend engineering project focused on learning **distributed systems, backend scalability, and modern Java frameworks**.
