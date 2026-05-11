# 🚀 Journal App

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)
![MongoDB](https://img.shields.io/badge/MongoDB-NoSQL-green?style=for-the-badge&logo=mongodb)
![Redis](https://img.shields.io/badge/Redis-Cache-red?style=for-the-badge&logo=redis)
![Kafka](https://img.shields.io/badge/Apache_Kafka-Event_Streaming-black?style=for-the-badge&logo=apachekafka)
![Swagger](https://img.shields.io/badge/API-Swagger-success?style=for-the-badge&logo=swagger)
![Maven](https://img.shields.io/badge/Maven-Build-blue?style=for-the-badge&logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

### Production-Style Spring Boot Backend Project

A feature-rich Journal Management Backend Application built using Java, Spring Boot, MongoDB, Redis, Kafka, and modern backend engineering practices.

</div>

---

# ✨ Features

## 🔐 Authentication & Security
- User Registration & Login
- Spring Security Authentication
- Password Encryption
- Role-Based Authorization
- Protected REST APIs

## 📝 Journal Management
- Create Journal Entries
- Update Journal Entries
- Delete Journal Entries
- View User-Specific Journals
- Associate Journals with Users

## ⚡ Redis Caching
- Faster Response Times
- Reduced Database Calls
- Frequently Used Data Caching

## 📩 Email Notifications
- Email Sending using Spring Mail
- Automated Notification Support
- SMTP Integration

## 🌦 Weather Integration
- External Weather API Integration
- Dynamic Weather Information Fetching

## 📊 Sentiment Analysis
- Kafka-based Event Processing
- Asynchronous Message Consumers
- Sentiment Categorization

## ⏰ Scheduler Support
- Scheduled Background Jobs
- Automated Processing using `@Scheduled`

## 📘 Swagger Documentation
- Interactive API Testing
- OpenAPI Integration

---

# 🛠 Tech Stack

| Category | Technologies |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3 |
| Security | Spring Security |
| Database | MongoDB |
| Cache | Redis |
| Messaging | Apache Kafka |
| API Docs | Swagger / OpenAPI |
| Build Tool | Maven |
| Email Service | Spring Mail |
| Scheduling | Spring Scheduler |

---

# 🏗 Architecture Diagram

```text
                        +----------------+
                        |     Client     |
                        +----------------+
                                 |
                                 v
                    +------------------------+
                    |   REST Controllers     |
                    +------------------------+
                                 |
                                 v
                    +------------------------+
                    |     Service Layer      |
                    +------------------------+
                      |      |        |
          ------------      |        ------------
          |                 |                   |
          v                 v                   v
   +-------------+   +-------------+   +----------------+
   |   MongoDB   |   |    Redis    |   | Kafka Producer |
   +-------------+   +-------------+   +----------------+
                                                    |
                                                    v
                                           +----------------+
                                           | Kafka Consumer |
                                           +----------------+
```

---

# 📂 Project Structure

```text
src/main/java/com/surya/journalApp
│
├── controller
│   ├── AdminController
│   ├── JournalEntryController
│   ├── UserController
│   └── PublicController
│
├── entity
│   ├── JournalEntry
│   ├── User
│   └── ConfigJournalAppEntity
│
├── repository
│   ├── JournalEntryRepository
│   ├── UserRepository
│   └── ConfigJournalAppRepository
│
├── service
│   ├── JournalEntryService
│   ├── UserService
│   ├── EmailService
│   ├── RedisService
│   ├── WeatherService
│   └── SentimentConsumerService
│
├── configuration
│   ├── SpringSecurity
│   ├── RedisConfig
│   ├── MongoConfig
│   └── WebConfig
│
├── scheduler
│   └── UserScheduler
│
└── api/response
    └── WeatherResponse
```

---

# ⚙️ Installation & Setup

## 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/journal-app.git
cd journal-app
```

---

## 2️⃣ Configure MongoDB

Start MongoDB locally.

```bash
mongodb://localhost:27017/journaldb
```

---

## 3️⃣ Configure Redis

Start Redis server:

```bash
redis-server
```

Default Port:

```bash
6379
```

---

## 4️⃣ Start Zookeeper

### Windows

```bash
zookeeper-server-start.bat ..\..\config\zookeeper.properties
```

### Linux / Mac

```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

---

## 5️⃣ Start Kafka Server

### Windows

```bash
kafka-server-start.bat ..\..\config\server.properties
```

### Linux / Mac

```bash
bin/kafka-server-start.sh config/server.properties
```

---

## 6️⃣ Configure `application.yml`

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/journaldb

  redis:
    host: localhost
    port: 6379

  kafka:
    bootstrap-servers: localhost:9092

  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-app-password
```

---

## 7️⃣ Run Application

```bash
mvn spring-boot:run
```

OR run:

```text
JournalAppApplication.java
```

---

# 🔑 API Endpoints

## 🧑 Authentication APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/public/signup` | Register User |
| POST | `/public/login` | Login User |

---

## 📝 Journal APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/journal` | Get All Journals |
| POST | `/journal` | Create Journal |
| PUT | `/journal/id/{id}` | Update Journal |
| DELETE | `/journal/id/{id}` | Delete Journal |

---

## 👤 User APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/user` | Get User Details |
| PUT | `/user` | Update User |
| DELETE | `/user` | Delete User |

---

# 📬 API Request Examples

## Create Journal Entry

### Request

```http
POST /journal
Content-Type: application/json
```

```json
{
  "title": "Daily Learning",
  "content": "Learned Kafka Integration today",
  "sentiment": "HAPPY"
}
```

### Response

```json
{
  "message": "Journal Created Successfully"
}
```

---

# 📘 Swagger Documentation

After running the application:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 📸 Setup Screenshots

## Swagger UI

```text
Add screenshot here:
/docs/screenshots/swagger-ui.png
```

---

## MongoDB Collections

```text
Add screenshot here:
/docs/screenshots/mongodb.png
```

---

## Kafka Running

```text
Add screenshot here:
/docs/screenshots/kafka.png
```

---

## Redis Cache

```text
Add screenshot here:
/docs/screenshots/redis.png
```

---

# 🔄 Kafka Workflow

```text
Producer → Kafka Topic → Consumer → Sentiment Processing
```

---

# ⚡ Redis Caching Flow

```text
Client Request
      ↓
Check Redis Cache
      ↓
Cache Hit → Return Response
      ↓
Cache Miss → Fetch MongoDB → Store in Redis
```

---

# 🔒 Security Features

- Spring Security Integration
- Password Encryption
- Role-Based Access
- Secure API Endpoints
- Authentication Filters

---

# 🧪 Testing

Run Tests:

```bash
mvn test
```

---

# 🚀 Future Enhancements

- JWT Authentication
- Docker Deployment
- Kubernetes Support
- CI/CD Pipeline
- Microservices Architecture
- AI-based Sentiment Analysis
- Frontend Integration
- Cloud Deployment

---

# 📚 Concepts Demonstrated

This project covers:

- REST APIs
- Layered Architecture
- Spring Boot Best Practices
- Authentication & Authorization
- MongoDB Integration
- Redis Caching
- Kafka Messaging
- Scheduler Jobs
- External API Integration
- Email Services
- API Documentation

---

# 👨‍💻 Author

## Surya Kumar K V

### Java Full Stack Developer

### Skills
- Java
- Spring Boot
- MongoDB
- Redis
- Kafka
- SQL
- REST APIs
- Backend Development
- Microservices

---

# 🌟 Why This Project Stands Out

✅ Production-Style Architecture  
✅ Real-World Backend Concepts  
✅ Multiple Technologies Integration  
✅ Event-Driven Design  
✅ Cache Optimization  
✅ Clean Layered Structure  
✅ Resume-Worthy Project  

---

# 🤝 Contribution

Contributions are welcome.

### Steps

1. Fork Repository
2. Create Feature Branch
3. Commit Changes
4. Push Changes
5. Open Pull Request

---

# 📄 License

This project is licensed under the MIT License.

---

# ⭐ Support

If you liked this project:

⭐ Star the repository  
🍴 Fork the project  
🛠 Contribute improvements  
📢 Share feedback  

---

<div align="center">

# Thank You ❤️

### Built with Java & Spring Boot

</div>
