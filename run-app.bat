@echo off
echo ========================================
echo Library Management System
echo ========================================
echo.

REM Try using JavaFX Maven plugin first
echo Attempting to run with JavaFX Maven plugin...
call mvn javafx:run

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo JavaFX plugin failed. Trying alternative method...
    echo.
    REM Alternative: Use exec plugin with JavaFX modules
    call mvn clean compile exec:java -Dexec.mainClass="com.library.LibraryApplication" -Dexec.classpathScope=compile
)

pause

