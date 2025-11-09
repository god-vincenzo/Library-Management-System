@echo off
echo ========================================
echo Library Management System
echo ========================================
echo.

REM Check if Maven is installed
where mvn >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven is not found in PATH!
    echo.
    echo Please close and reopen PowerShell, then try again.
    echo Or restart your computer.
    pause
    exit /b 1
)

echo Maven is installed!
echo.

REM Navigate to project directory
cd /d "%~dp0"

echo Building project...
echo This may take 2-5 minutes on first run (downloading dependencies)...
echo.
call mvn clean install

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo Build failed! Check errors above.
    pause
    exit /b 1
)

echo.
echo ========================================
echo Build successful!
echo ========================================
echo.
echo Starting application...
echo.
echo Login credentials:
echo   Username: admin
echo   Password: admin123
echo.
echo ========================================
echo.

call mvn spring-boot:run

pause

