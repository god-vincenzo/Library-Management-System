@echo off
echo ========================================
echo Library Management System
echo ========================================
echo.

echo Running with JavaFX Maven plugin...
echo.

call mvn javafx:run

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ========================================
    echo JavaFX plugin failed. Trying exec plugin...
    echo ========================================
    echo.
    call mvn exec:java
)

pause
