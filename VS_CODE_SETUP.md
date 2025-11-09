# Running Library Management System in VS Code

## Prerequisites

### 1. Install VS Code Extensions

Open VS Code and install these extensions:

1. **Extension Pack for Java** (by Microsoft)
   - Includes: Language Support for Java, Debugger for Java, Test Runner for Java, Maven for Java, etc.
   - Install from: Extensions panel (Ctrl+Shift+X)
   - Search: "Extension Pack for Java"

2. **Maven for Java** (if not included in Extension Pack)
   - Search: "Maven for Java"

### 2. Verify Java and Maven

Open VS Code terminal (Ctrl+`) and verify:
```bash
java -version
mvn -version
```

## Running the Application

### Method 1: Using Maven Terminal Command (Recommended)

1. Open the `library-management-system` folder in VS Code
2. Open Terminal (Ctrl+` or Terminal > New Terminal)
3. Run this command:

```bash
mvn javafx:run
```

### Method 2: Using VS Code Tasks

1. Press `Ctrl+Shift+P` (or `Cmd+Shift+P` on Mac)
2. Type: "Tasks: Run Task"
3. Select: "maven: javafx:run" (if available)
4. Or create a custom task (see below)

### Method 3: Run from Java Files

1. Open `src/main/java/com/library/JavafxLauncher.java`
2. Click the "Run" button above the `main` method
3. Or right-click in the file → "Run Java"

## VS Code Tasks Configuration

Create `.vscode/tasks.json` file in the project root:

```json
{
    "version": "2.0.0",
    "tasks": [
        {
            "label": "Run Library Management System",
            "type": "shell",
            "command": "mvn",
            "args": [
                "javafx:run"
            ],
            "group": {
                "kind": "build",
                "isDefault": true
            },
            "problemMatcher": []
        },
        {
            "label": "Build Project",
            "type": "shell",
            "command": "mvn",
            "args": [
                "clean",
                "compile"
            ],
            "group": "build",
            "problemMatcher": []
        }
    ]
}
```

## VS Code Launch Configuration

Create `.vscode/launch.json` for debugging:

```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Run Library Management System",
            "request": "launch",
            "mainClass": "com.library.JavafxLauncher",
            "projectName": "library-management-system",
            "vmArgs": "--module-path ${env:USERPROFILE}/.m2/repository/org/openjfx/javafx-controls/21/javafx-controls-21-win.jar:${env:USERPROFILE}/.m2/repository/org/openjfx/javafx-fxml/21/javafx-fxml-21-win.jar --add-modules javafx.controls,javafx.fxml"
        }
    ]
}
```

## Quick Commands

### Build Project
```bash
mvn clean compile
```

### Run Application
```bash
mvn javafx:run
```

### Build and Run
```bash
mvn clean compile javafx:run
```

### Run with Exec Plugin (Alternative)
```bash
mvn exec:java -Dexec.mainClass="com.library.JavafxLauncher"
```

## Troubleshooting

### Issue: JavaFX modules not found
**Solution:** Run `mvn dependency:resolve` first to download JavaFX dependencies.

### Issue: "JavaFX runtime components are missing"
**Solution:** Use IntelliJ IDEA instead, or ensure JavaFX modules are in the module path.

### Issue: VS Code doesn't recognize Java
**Solution:** 
1. Install "Extension Pack for Java"
2. Reload VS Code
3. Check Java version: `java -version` (must be 17+)

### Issue: Maven not found
**Solution:** 
1. Install Maven (you already did this)
2. Restart VS Code
3. Verify: `mvn -version`

## Recommended: Use Integrated Terminal

1. Open VS Code
2. Open the `library-management-system` folder
3. Open Terminal (Ctrl+`)
4. Run: `mvn javafx:run`

That's it! The application should start.

## Alternative: Use IntelliJ IDEA

If VS Code gives you JavaFX issues, IntelliJ IDEA handles JavaFX automatically:
1. Download IntelliJ IDEA Community Edition (free)
2. Open the project folder
3. Right-click `JavafxLauncher.java` → Run

No configuration needed!

