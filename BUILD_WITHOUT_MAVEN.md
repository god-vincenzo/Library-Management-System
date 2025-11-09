# Building Without Maven - Alternative Methods

Since Maven is not installed, here are alternative ways to build and run the project:

## Option 1: Use IntelliJ IDEA (Easiest - Recommended)

IntelliJ IDEA has Maven built-in and will handle everything automatically.

### Steps:
1. **Download IntelliJ IDEA Community Edition (Free)**
   - https://www.jetbrains.com/idea/download/
   - Choose "Community" version (free)

2. **Install and Open IntelliJ**
   - Run the installer
   - Open IntelliJ IDEA

3. **Open the Project**
   - File > Open
   - Select the `library-management-system` folder
   - Click "Open"

4. **IntelliJ will automatically:**
   - Detect it's a Maven project
   - Download all dependencies
   - Index the project
   - Set up the project structure

5. **Run the Application**
   - Find `LibraryApplication.java` in the project tree
   - Right-click on it
   - Select "Run 'LibraryApplication.main()'"
   - The application will start!

### Advantages:
- ✅ No Maven installation needed
- ✅ Automatic dependency management
- ✅ Built-in JavaFX support
- ✅ Easy debugging
- ✅ Free (Community Edition)

## Option 2: Use Eclipse IDE

Eclipse also has Maven support built-in.

### Steps:
1. **Download Eclipse IDE for Java Developers**
   - https://www.eclipse.org/downloads/
   - Choose "Eclipse IDE for Java Developers"

2. **Install and Open Eclipse**

3. **Import the Project**
   - File > Import
   - Maven > Existing Maven Projects
   - Select the `library-management-system` folder
   - Click "Finish"

4. **Run the Application**
   - Find `LibraryApplication.java`
   - Right-click > Run As > Java Application

## Option 3: Install Maven (Then Use Command Line)

### Quick Install with Chocolatey:
1. Open PowerShell as Administrator
2. Run: `choco install maven`
3. Close and reopen PowerShell
4. Verify: `mvn -version`
5. Build: `mvn clean install`
6. Run: `mvn spring-boot:run`

### Manual Install:
See `INSTALL_MAVEN.md` for detailed instructions.

## Option 4: Use Maven Wrapper (After One-Time Setup)

If you have access to a computer with Maven:
1. Run `mvn wrapper:wrapper` once
2. This creates `mvnw.cmd` (Windows) that works without Maven installed
3. Use `mvnw.cmd` instead of `mvn` commands

## Recommended: IntelliJ IDEA

For the fastest setup without installing Maven:
1. Download IntelliJ IDEA Community Edition
2. Open the project folder
3. Run `LibraryApplication.java`
4. Done!

The IDE will handle all Maven operations automatically.

## Troubleshooting

### If IntelliJ shows "Maven not found":
- IntelliJ has Maven bundled - it should work automatically
- If not, go to File > Settings > Build, Execution, Deployment > Build Tools > Maven
- Check "Use plugin registry" or configure Maven home

### If dependencies don't download:
- Go to View > Tool Windows > Maven
- Click the "Reload" button (circular arrow icon)
- Wait for dependencies to download

### If JavaFX errors occur:
- IntelliJ should handle JavaFX automatically
- If not, the application should still run with Spring Boot

## Next Steps

Once the project is open in an IDE:
1. Wait for Maven to download dependencies (first time only)
2. Run `LibraryApplication.java`
3. Login with admin/admin123
4. Start using the application!

---

**Quick Start with IntelliJ:**
1. Download IntelliJ IDEA Community Edition
2. Open `library-management-system` folder
3. Run `LibraryApplication.java`
4. Login and use the app!

No Maven installation required! 🎉

