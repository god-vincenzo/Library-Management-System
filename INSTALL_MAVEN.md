# Installing Maven - Quick Guide

## Option 1: Install Maven (Recommended)

### Windows - Using Chocolatey (Fastest)
1. Open PowerShell as Administrator
2. Run:
```powershell
choco install maven
```
3. Close and reopen PowerShell
4. Verify: `mvn -version`

### Windows - Manual Install
1. Download Maven from: https://maven.apache.org/download.cgi
2. Extract to `C:\Program Files\Apache\maven`
3. Add to PATH:
   - Open System Properties > Environment Variables
   - Add `C:\Program Files\Apache\maven\bin` to PATH
4. Restart PowerShell
5. Verify: `mvn -version`

### Windows - Using Scoop
```powershell
scoop install maven
```

## Option 2: Use IDE (Easiest - No Maven Installation Needed)

### IntelliJ IDEA Community Edition (Free)
1. Download: https://www.jetbrains.com/idea/download/
2. Install and open IntelliJ
3. Open the `library-management-system` folder as a project
4. IntelliJ will automatically detect Maven and download dependencies
5. Run `LibraryApplication.java` directly from IntelliJ

### Eclipse IDE
1. Download: https://www.eclipse.org/downloads/
2. Install Eclipse IDE for Java Developers
3. Import the project as Maven project
4. Run `LibraryApplication.java`

### VS Code
1. Install Java Extension Pack
2. Install Maven Extension
3. Open the project folder
4. Use the Maven extension to build and run

## Option 3: Use Maven Wrapper (After Maven is Installed Once)

After installing Maven, run:
```bash
mvn wrapper:wrapper
```

This creates `mvnw.cmd` (Windows) and `mvnw` (Linux/Mac) that don't require Maven to be installed.

## Quick Test

After installing Maven, verify it works:
```bash
mvn -version
```

You should see Maven version information.

## Next Steps

Once Maven is installed:
1. Navigate to project: `cd library-management-system`
2. Build: `mvn clean install`
3. Run: `mvn spring-boot:run`

