# Setup Guide - Library Management System

## Complete Setup Instructions

### 1. Verify Prerequisites

**Check Java Version:**
```bash
java -version
```
Should be Java 17 or higher.

**Check Maven:**
```bash
mvn -version
```
Should be Maven 3.6 or higher.

### 2. Project Structure

The project is already set up with the following structure:
```
library-management-system/
├── pom.xml                    # Maven configuration
├── README.md                  # Full documentation
├── QUICKSTART.md             # Quick start guide
├── run.bat                   # Windows run script
├── run.sh                    # Linux/Mac run script
└── src/
    └── main/
        ├── java/com/library/ # All Java source files
        └── resources/        # Configuration and UI files
```

### 3. Build the Project

Open terminal in the `library-management-system` directory and run:

```bash
mvn clean install
```

This will:
- Download all dependencies
- Compile the project
- Run tests (if any)
- Create executable JAR

### 4. Run the Application

**Option 1: Using Run Script (Recommended)**

Windows:
```bash
run.bat
```

Linux/Mac:
```bash
chmod +x run.sh
./run.sh
```

**Option 2: Using Maven**
```bash
mvn spring-boot:run
```

**Option 3: Using Java (after building)**
```bash
java -jar target/library-management-system-1.0.0.jar
```

### 5. Login

When the application starts, you'll see a login screen:

**Admin Account:**
- Username: `admin`
- Password: `admin123`

**Librarian Account:**
- Username: `librarian`
- Password: `lib123`

### 6. Using the Application

#### Dashboard
- View real-time statistics
- See total books, available books, members, issued books, overdue books

#### Books Management
- **View Books**: See all books in a table
- **Search Books**: Search by ISBN, title, author, or category
- **Add Book**: Fill in the form and click "Add Book"
- **Delete Book**: Select a book and click "Delete Selected"

#### Members Management
- **View Members**: See all members
- **Search Members**: Search by member ID, name, or email
- **Add Member**: Fill in the form (Member ID is auto-generated if left empty)
- **Delete Member**: Select a member and click "Delete Selected"

#### Issue/Return Books
- **Issue Book**: Enter Book ID and Member ID, click "Issue Book"
- **Return Book**: Enter Transaction ID, click "Return Book"
- **View Transactions**: See all active transactions in the table

### 7. Sample Data

The application comes pre-loaded with:
- 10 books (programming, design, architecture topics)
- 5 members
- 2 user accounts

### 8. Database

The application uses H2 embedded database. Data is stored in:
```
./data/librarydb.mv.db
```

To reset the database:
1. Stop the application
2. Delete the `data/` folder
3. Restart the application

### 9. Troubleshooting

#### Issue: JavaFX Runtime Not Found
**Solution:**
```bash
mvn clean compile exec:java -Dexec.mainClass="com.library.LibraryApplication"
```

#### Issue: Port Already in Use
**Solution:** Edit `src/main/resources/application.properties`:
```properties
server.port=8081
```

#### Issue: Build Fails
**Solution:**
1. Check Java version (must be 17+)
2. Check Maven version (must be 3.6+)
3. Delete `target/` folder and try again:
```bash
mvn clean install
```

#### Issue: Application Won't Start
**Solution:**
1. Check if port 8080 is available
2. Check Java version
3. Check if all dependencies downloaded correctly:
```bash
mvn dependency:resolve
```

### 10. Development

#### Adding New Features
1. Create model in `src/main/java/com/library/model/`
2. Create repository in `src/main/java/com/library/repository/`
3. Create service in `src/main/java/com/library/service/`
4. Create UI controller in `src/main/java/com/library/ui/`
5. Create FXML file in `src/main/resources/fxml/`

#### Modifying UI
- Edit FXML files in `src/main/resources/fxml/`
- Edit CSS in `src/main/resources/styles.css`
- Controllers are in `src/main/java/com/library/ui/`

### 11. Configuration

Edit `src/main/resources/application.properties` to change:
- Database settings
- Fine amount per day
- Days due for book issue
- Logging level
- Port (if needed)

### 12. Export/Import

Currently, the application doesn't have export/import functionality, but you can:
- Access H2 console: http://localhost:8080/h2-console (if web server enabled)
- Export data using SQL queries
- Import data using SQL scripts

## Support

For issues or questions:
1. Check the README.md for detailed documentation
2. Check QUICKSTART.md for quick reference
3. Review the code comments for implementation details

## Next Steps

1. ✅ Build the project
2. ✅ Run the application
3. ✅ Login with admin account
4. ✅ Explore the dashboard
5. ✅ Add a book
6. ✅ Add a member
7. ✅ Issue a book
8. ✅ Return a book

Enjoy your Library Management System!

