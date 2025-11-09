@echo off
echo ========================================
echo Maven Installation via Chocolatey
echo ========================================
echo.
echo This will install Chocolatey (if needed) and Maven
echo.
echo NOTE: This requires Administrator privileges!
echo.
pause

powershell -Command "Start-Process powershell -Verb RunAs -ArgumentList '-NoExit', '-Command', 'cd /d \"%CD%\"; .\install-maven-choco.ps1'"

