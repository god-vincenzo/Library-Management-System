# Fixing JavaFX Runtime Error

## Problem
Error: "JavaFX runtime components are missing, and are required to run this application"

## Solution

### Method 1: Use JavaFX Maven Plugin (Recommended)

Run this command:
```bash
mvn javafx:run
```

Or use the batch file:
```bash
RUN.bat
```

### Method 2: Use Exec Plugin

If JavaFX plugin doesn't work, try:
```bash
mvn exec:java
```

### Method 3: Manual Run with Module Path

1. Build the project:
```bash
mvn clean package
```

2. Find JavaFX JARs in your Maven repository (usually in `~/.m2/repository/org/openjfx/`)

3. Run with module path:
```bash
java --module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml -jar target/library-management-system-1.0.0.jar
```

### Method 4: Use IntelliJ IDEA (Easiest)

1. Open the project in IntelliJ IDEA
2. IntelliJ will handle JavaFX automatically
3. Run `JavafxLauncher.java` directly from IntelliJ

## Quick Fix

The easiest solution is to use:
```bash
mvn javafx:run
```

This should work with the new `JavafxLauncher` class.

## Alternative: Run without JavaFX Modules

If you continue to have issues, we can modify the application to run JavaFX without modules (using classpath instead of module path).

