# Library Management System

A comprehensive Library Management System built with Java, Spring Boot, and JavaFX.

## Features

- ✅ User Authentication (Admin/Librarian roles)
- ✅ Book Management (CRUD operations)
- ✅ Member Management (CRUD operations)
- ✅ Issue & Return Books
- ✅ Fine Calculation (Automatic)
- ✅ Search Functionality
- ✅ Dashboard with Statistics
- ✅ Modern JavaFX GUI

## Tech Stack

- **Backend**: Spring Boot 3.2.0
- **Frontend**: JavaFX 21
- **Database**: H2 (Embedded)
- **Build Tool**: Maven
- **Java Version**: 17

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Setup Instructions

### 1. Clone/Navigate to Project

```bash
cd library-management-system
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

Or if you have JavaFX modules configured:

```bash
java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -jar target/library-management-system-1.0.0.jar
```

### Alternative: Run with Maven (Recommended)

```bash
mvn clean javafx:run
```

But first, you need to add JavaFX Maven plugin to pom.xml or use this command:

```bash
mvn clean compile exec:java -Dexec.mainClass="com.library.LibraryApplication"
```

## Default Login Credentials

- **Admin**: 
  - Username: `admin`
  - Password: `admin123`

- **Librarian**: 
  - Username: `librarian`
  - Password: `lib123`

## Sample Data

The application comes pre-loaded with:
- 10 sample books
- 5 sample members
- 2 user accounts (admin and librarian)

## Project Structure

```
library-management-system/
├── src/main/java/com/library/
│   ├── LibraryApplication.java
│   ├── config/
│   │   └── DataSeeder.java
│   ├── model/
│   │   ├── User.java
│   │   ├── Book.java
│   │   ├── Member.java
│   │   └── Transaction.java
│   ├── repository/
│   │   ├── UserRepository.java
│   │   ├── BookRepository.java
│   │   ├── MemberRepository.java
│   │   └── TransactionRepository.java
│   ├── service/
│   │   ├── UserService.java
│   │   ├── BookService.java
│   │   ├── MemberService.java
│   │   └── TransactionService.java
│   └── ui/
│       ├── StageManager.java
│       ├── LoginController.java
│       ├── DashboardController.java
│       └── CurrentUser.java
├── src/main/resources/
│   ├── application.properties
│   ├── fxml/
│   │   ├── Login.fxml
│   │   └── Dashboard.fxml
│   └── styles.css
└── pom.xml
```

## Database

The application uses H2 embedded database. Data is stored in `./data/librarydb.mv.db` file.

To access H2 Console:
1. Application must be running
2. Navigate to: http://localhost:8080/h2-console
3. JDBC URL: `jdbc:h2:file:./data/librarydb`
4. Username: `sa`
5. Password: (leave empty)

## Features Guide

### Book Management
- View all books in a table
- Search books by ISBN, Title, Author, or Category
- Add new books
- Delete books

### Member Management
- View all members
- Search members
- Add new members
- Delete members

### Issue/Return Books
- Issue books to members (14 days default)
- Return books
- Automatic fine calculation for overdue books ($1 per day)
- View active transactions

### Dashboard
- Real-time statistics
- Total books, available books, members
- Issued books and overdue books count

## Troubleshooting

### JavaFX Runtime Error
If you get JavaFX runtime errors, make sure:
1. Java 17+ is installed
2. JavaFX dependencies are in classpath
3. Use `mvn spring-boot:run` or configure JavaFX modules properly

### Database Connection Error
- Check if `./data/` directory exists and is writable
- Delete `./data/librarydb.mv.db` to reset database

### Port Already in Use
- Change port in `application.properties`: `server.port=8081`

## Development

### Adding New Features
1. Create model in `model/` package
2. Create repository in `repository/` package
3. Create service in `service/` package
4. Create UI controller in `ui/` package
5. Create FXML file in `resources/fxml/`

## License

This project is for educational purposes.

## Author

Library Management System - Spring Boot + JavaFX

## Project Report

You can view the project report here:

[Project Report](./Smart%20Library%20Management%20System_PROJECT_REPORT.pdf)
