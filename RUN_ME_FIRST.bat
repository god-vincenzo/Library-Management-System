@echo off
echo ========================================
echo Library Management System - Setup
echo ========================================
echo.
echo This will help you install Maven using Chocolatey
echo.
echo IMPORTANT: You need Administrator privileges!
echo.
echo Choose an option:
echo 1. Install Maven using Chocolatey (requires Admin)
echo 2. View installation instructions
echo 3. Use IDE instead (IntelliJ - no Maven needed)
echo.
set /p choice="Enter your choice (1-3): "

if "%choice%"=="1" (
    echo.
    echo Opening installer as Administrator...
    powershell -Command "Start-Process powershell -Verb RunAs -ArgumentList '-NoExit', '-Command', 'cd /d \"%CD%\"; .\install-maven-choco.ps1'"
) else if "%choice%"=="2" (
    echo.
    echo Opening installation instructions...
    notepad INSTALL_INSTRUCTIONS.md
) else if "%choice%"=="3" (
    echo.
    echo Opening IDE instructions...
    notepad BUILD_WITHOUT_MAVEN.md
) else (
    echo Invalid choice!
    pause
)

