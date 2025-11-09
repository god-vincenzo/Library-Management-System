# How to Run the Library Management System

## 🚀 Quick Start - Choose Your Method

### Method 1: Using IntelliJ IDEA (Easiest - No Maven Needed!) ⭐ RECOMMENDED

This is the **fastest way** to run the project without installing Maven.

#### Steps:
1. **Download IntelliJ IDEA Community Edition (Free)**
   - Go to: https://www.jetbrains.com/idea/download/
   - Download "Community" version (free)
   - Install it

2. **Open the Project**
   - Launch IntelliJ IDEA
   - Click "Open"
   - Navigate to: `C:\Users\mbads\OneDrive\Documents\nnnnnew\library-management-system`
   - Select the folder and click "Open"

3. **Wait for Setup**
   - IntelliJ will automatically detect it's a Maven project
   - It will download all dependencies (first time only - takes 2-5 minutes)
   - Wait for the Maven sync to complete (bottom right corner)

4. **Run the Application**
   - Find `LibraryApplication.java` in the project tree (left side)
   - Right-click on `LibraryApplication.java`
   - Select **"Run 'LibraryApplication.main()'"**
   - The application will start!

5. **Login**
   - Username: `admin`
   - Password: `admin123`

**That's it! No Maven installation needed!** 🎉

---

### Method 2: Using Maven Command Line (After Installing Maven)

#### Step 1: Install Maven First
If you haven't installed Maven yet:
- Run `RUN_ME_FIRST.bat` as Administrator
- Or see `INSTALL_INSTRUCTIONS.md`

#### Step 2: Open PowerShell/Terminal
```powershell
cd C:\Users\mbads\OneDrive\Documents\nnnnnew\library-management-system
```

#### Step 3: Build the Project
```powershell
mvn clean install
```
This will:
- Download all dependencies
- Compile the project
- Create the executable JAR file
- Takes 2-5 minutes on first run

#### Step 4: Run the Application
```powershell
mvn spring-boot:run
```

#### Step 5: Login
- Username: `admin`
- Password: `admin123`

---

### Method 3: Using Eclipse IDE

1. **Download Eclipse IDE for Java Developers**
   - https://www.eclipse.org/downloads/
   - Install Eclipse

2. **Import the Project**
   - File > Import
   - Maven > Existing Maven Projects
   - Select the `library-management-system` folder
   - Click "Finish"

3. **Run the Application**
   - Find `LibraryApplication.java`
   - Right-click > Run As > Java Application

---

### Method 4: Using VS Code

1. **Install Extensions**
   - Install "Java Extension Pack"
   - Install "Maven for Java"

2. **Open the Project**
   - File > Open Folder
   - Select `library-management-system` folder

3. **Run**
   - Find `LibraryApplication.java`
   - Click "Run" button above the main method

---

## 📋 Step-by-Step: Running with IntelliJ (Recommended)

### Detailed IntelliJ Steps:

1. **Download IntelliJ IDEA Community Edition**
   ```
   https://www.jetbrains.com/idea/download/
   ```

2. **Install IntelliJ**
   - Run the installer
   - Follow the installation wizard
   - Launch IntelliJ IDEA

3. **Open Project**
   - Click "Open" on welcome screen
   - Navigate to: `C:\Users\mbads\OneDrive\Documents\nnnnnew\library-management-system`
   - Click "OK"

4. **Wait for Maven Sync**
   - IntelliJ will show "Maven projects need to be imported"
   - Click "Import Maven Project" or wait for auto-import
   - Bottom right will show "Maven: Downloading..."
   - Wait until it says "Maven: Sync completed"

5. **Configure JDK (if needed)**
   - File > Project Structure > Project
   - Set "SDK" to Java 17 or higher
   - Click "OK"

6. **Run the Application**
   - In the project tree (left), find: `src/main/java/com/library/LibraryApplication.java`
   - Right-click on `LibraryApplication.java`
   - Select "Run 'LibraryApplication.main()'"
   - Or click the green play button next to the main method

7. **Application Starts**
   - You'll see the login window appear
   - Login with: `admin` / `admin123`
   - Start using the application!

---

## 🎯 Quick Reference

### Login Credentials:
- **Username:** `admin`
- **Password:** `admin123`

### Alternative User:
- **Username:** `librarian`
- **Password:** `lib123`

### Default Port:
- Application runs on default settings
- Database: H2 embedded (no setup needed)

### First Run:
- Database will be created automatically
- Sample data will be loaded (10 books, 5 members)

---

## 🔧 Troubleshooting

### Issue: "Maven not found" in IntelliJ
**Solution:**
- IntelliJ has Maven built-in, it should work automatically
- Go to File > Settings > Build Tools > Maven
- Check "Use plugin registry"

### Issue: Dependencies not downloading
**Solution:**
- Go to View > Tool Windows > Maven
- Click the refresh icon (circular arrow)
- Wait for downloads to complete

### Issue: JavaFX errors
**Solution:**
- IntelliJ should handle JavaFX automatically
- If errors occur, the app should still run with Spring Boot

### Issue: "Java version" error
**Solution:**
- Make sure Java 17 or higher is installed
- In IntelliJ: File > Project Structure > Project
- Set SDK to Java 17+

### Issue: Application won't start
**Solution:**
- Check if port 8080 is available
- Check Java version (must be 17+)
- Check console for error messages

---

## ✅ What You Should See

When the application starts successfully:

1. **Login Screen appears** with:
   - Gradient blue background
   - Login form in the center
   - Username and password fields

2. **After Login:**
   - Dashboard with statistics cards
   - Tabs for: Dashboard, Books, Add Book, Members, Add Member, Issue/Return
   - Sample data already loaded

3. **You can:**
   - View books and members
   - Add new books and members
   - Issue books to members
   - Return books
   - Search and filter data

---

## 🎉 Success!

Once you see the login screen, you're all set! 

**Recommended Next Steps:**
1. Login with admin/admin123
2. Explore the dashboard
3. Add a book
4. Add a member
5. Issue a book
6. Return a book

Enjoy your Library Management System!

---

## 📞 Need Help?

- Check `README.md` for full documentation
- Check `QUICKSTART.md` for quick reference
- Check `SETUP_GUIDE.md` for detailed setup
- Check console/terminal for error messages


