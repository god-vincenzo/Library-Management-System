@echo off
echo ========================================
echo Library Management System - Run Project
echo ========================================
echo.

REM Check if Maven is installed
where mvn >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    echo Maven is installed!
    echo.
    echo Building project...
    call mvn clean install
    if %ERRORLEVEL% EQU 0 (
        echo.
        echo Build successful!
        echo.
        echo Starting application...
        call mvn spring-boot:run
    ) else (
        echo.
        echo Build failed! Check errors above.
        pause
    )
) else (
    echo Maven is NOT installed.
    echo.
    echo Please choose an option:
    echo 1. Install Maven using Chocolatey
    echo 2. Use IntelliJ IDEA instead (recommended - no Maven needed)
    echo 3. View instructions
    echo.
    set /p choice="Enter choice (1-3): "
    
    if "%choice%"=="1" (
        echo.
        echo Opening Maven installer...
        call RUN_ME_FIRST.bat
    ) else if "%choice%"=="2" (
        echo.
        echo Opening IDE instructions...
        start notepad HOW_TO_RUN.md
        echo.
        echo See HOW_TO_RUN.md for IntelliJ IDEA setup instructions
        echo.
        echo Quick steps:
        echo 1. Download IntelliJ IDEA Community Edition (free)
        echo 2. Open the library-management-system folder
        echo 3. Right-click LibraryApplication.java ^> Run
        echo.
    ) else if "%choice%"=="3" (
        start notepad HOW_TO_RUN.md
    ) else (
        echo Invalid choice!
    )
    
    pause
)


