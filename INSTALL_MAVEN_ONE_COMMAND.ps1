# One-Command Maven Installation
# Run this in PowerShell as Administrator

# Install Chocolatey (if not installed) and Maven in one go
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; if (-not (Get-Command choco -ErrorAction SilentlyContinue)) { Write-Host "Installing Chocolatey..." -ForegroundColor Yellow; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1')); $env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User") }; Write-Host "Installing Maven..." -ForegroundColor Yellow; choco install maven -y; Write-Host "`nMaven installed! Close and reopen PowerShell, then run: mvn -version" -ForegroundColor Green


