# Quick Start Guide - Library Management System

## Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

## Quick Setup (2 minutes)

### Step 1: Navigate to Project
```bash
cd library-management-system
```

### Step 2: Build Project
```bash
mvn clean install
```

### Step 3: Run Application

**Windows:**
```bash
run.bat
```

**Linux/Mac:**
```bash
chmod +x run.sh
./run.sh
```

**Or directly with Maven:**
```bash
mvn spring-boot:run
```

### Step 4: Login
- Username: `admin`
- Password: `admin123`

## Features Available

1. **Dashboard** - View statistics (total books, members, issued books, etc.)
2. **Books Tab** - View and search books
3. **Add Book Tab** - Add new books to the library
4. **Members Tab** - View and search members
5. **Add Member Tab** - Add new members
6. **Issue/Return Tab** - Issue books to members and return them

## Sample Data

The application comes with:
- 10 sample books (programming, design, architecture)
- 5 sample members
- 2 user accounts (admin/admin123, librarian/lib123)

## Troubleshooting

### JavaFX Not Found
If you get JavaFX errors, make sure Java 17+ is installed and try:
```bash
mvn clean compile exec:java -Dexec.mainClass="com.library.LibraryApplication"
```

### Port Already in Use
Change port in `src/main/resources/application.properties`:
```properties
server.port=8081
```

### Database Reset
Delete the `data/` folder and restart the application.

## Next Steps

1. Explore the dashboard
2. Add a new book
3. Add a new member
4. Issue a book to a member
5. Return the book

Enjoy your Library Management System!

