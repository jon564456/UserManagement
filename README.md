# User Management API

A simple CRUD (Create, Read, Update, Delete) API for user management built with **Java 21** and **Spring Boot**.  
This personal project was developed to reinforce backend development concepts, apply clean architecture practices, and gain hands-on experience with the Spring ecosystem.

---

## 📌 Features

- Create new users
- Retrieve all users or a specific user
- Update users (full and partial)
- Delete users
- Passwords are securely stored using BCrypt hashing

---

## 🧱 Architecture

This project follows a clean, layered architecture:

- **Controller Layer** – Handles HTTP requests
- **Service Layer** – Contains business logic
- **Repository Layer** – Interacts with the database
- **DTO Layer** – Handles response and request API
- **Mapper Layer** - Converts between Entities and DTOs.

---

## ⚙️ Technologies Used

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven
- Postman (for testing)
- BCrypt (for password hashing)

---

## 📁 Project Structure

UserManagement
├── controller
│ └── UserController.java
├── model
│ └── User.java
├── dto
│ └──user
│   └──UserRequestDTO.java
│   └──UserResponseDTO.java
├── exception
│ └──UserGlobalException.java
│ └──UserNotFound.java
├── repository
│ └── UserRepository.java
├── mapper
│ └── UserMapper.java
├── service
│ └── UserService.java
└── UsermanagementApplication.java

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven
- MySQL (or compatible database)

### Installation & Running

1. **Clone the repository**
   git clone https://github.com/jon564456/UserManagement.git
2. **Open the project in your preferred IDE.**
3. Set up the database by running the following SQL:
   
   ```sql
   CREATE DATABASE users_api;

    USE users_api;
	
    CREATE TABLE users (
      iduser INT AUTO_INCREMENT PRIMARY KEY,
      username VARCHAR(50) NOT NULL UNIQUE,
      password TEXT NOT NULL,
      email TEXT NOT NULL UNIQUE, 
    );
   ```

4. Update your application.properties file with your database configuration (if needed):

	```sql
	spring.datasource.url=jdbc:mysql://localhost:3306/users_api
	spring.datasource.username=your_username
	spring.datasource.password=your_password
	```

5. Run the application:

 	Execute UsermanagementApplication.java from your IDE

	Or run:

    ```sql
    mvn spring-boot:run
	```
6. Access the API:

| Method | Endpoint         | Description               |
| ------ | ---------------- | ------------------------- |
| GET    | `/users/v1`      | Get all users             |
| GET    | `/users/v1/{id}` | Get a specific user by ID |
| POST   | `/users/v1`      | Create a new user         |
| PUT    | `/users/v1/{id}` | Fully update a user       |
| PATCH  | `/users/v1/{id}` | Partially update a user   |
| DELETE | `/users/v1/{id}` | Delete a user             |

# 📦 Example Request (POST)

    POST /users/v1
    Content-Type: application/json
    
    {
      "username": "johndoe",
      "password": "mypassword"
    }

🔐 Passwords are automatically encrypted using BCrypt before storage.

##🧪 Testing
You can test the API using Postman, cURL, or any API testing tool.
Make sure the application is running and the database is properly configured.**



The application will start on http://localhost:8080