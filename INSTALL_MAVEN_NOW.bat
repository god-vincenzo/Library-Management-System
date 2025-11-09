@echo off
echo ========================================
echo Installing Maven with Chocolatey
echo ========================================
echo.
echo This will install Chocolatey (if needed) and Maven
echo.
echo NOTE: Requires Administrator privileges!
echo.
pause

powershell -Command "Start-Process powershell -Verb RunAs -ArgumentList '-NoExit', '-Command', 'Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; if (-not (Get-Command choco -ErrorAction SilentlyContinue)) { Write-Host \"Installing Chocolatey...\" -ForegroundColor Yellow; iex ((New-Object System.Net.WebClient).DownloadString(\"https://community.chocolatey.org/install.ps1\")); $env:Path = [System.Environment]::GetEnvironmentVariable(\"Path\",\"Machine\") + \";\" + [System.Environment]::GetEnvironmentVariable(\"Path\",\"User\") }; Write-Host \"Installing Maven...\" -ForegroundColor Yellow; choco install maven -y; Write-Host \"`n========================================`nMaven installed successfully!`n========================================`n`nClose this window and open a new PowerShell, then run:`nmvn -version`n`nTo build project: mvn clean install`nTo run project: mvn spring-boot:run`n========================================\" -ForegroundColor Green; pause'"

