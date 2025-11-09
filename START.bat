@echo off
echo ========================================
echo Library Management System
echo ========================================
echo.

echo Method 1: Trying JavaFX Maven Plugin...
echo.
call mvn clean compile javafx:run

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Application started successfully!
    goto :end
)

echo.
echo ========================================
echo JavaFX plugin failed. Trying exec plugin...
echo ========================================
echo.

call mvn exec:java -Dexec.mainClass="com.library.JavafxLauncher"

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Application started successfully!
    goto :end
)

echo.
echo ========================================
echo Both methods failed.
echo ========================================
echo.
echo RECOMMENDED SOLUTION: Use IntelliJ IDEA
echo.
echo 1. Download IntelliJ IDEA Community Edition (free)
echo 2. Open the library-management-system folder
echo 3. Right-click JavafxLauncher.java ^> Run
echo.
echo IntelliJ will handle JavaFX automatically - no configuration needed!
echo.
echo See QUICK_FIX.txt for more solutions.

:end
pause

