@echo off
echo ========================================
echo Library Management System
echo ========================================
echo.

echo Building project...
call mvn clean compile -q

if %ERRORLEVEL% NEQ 0 (
    echo Build failed!
    pause
    exit /b 1
)

echo.
echo Starting application with JavaFX...
echo.

REM Use JavaFX Maven plugin
call mvn javafx:run

pause

