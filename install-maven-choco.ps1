# Install Maven using Chocolatey
# This script must be run as Administrator

Write-Host "========================================" -ForegroundColor Green
Write-Host "Maven Installation via Chocolatey" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""

# Check if running as Administrator
$isAdmin = ([Security.Principal.WindowsPrincipal] [Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)

if (-not $isAdmin) {
    Write-Host "ERROR: This script requires Administrator privileges!" -ForegroundColor Red
    Write-Host ""
    Write-Host "Please run PowerShell as Administrator:" -ForegroundColor Yellow
    Write-Host "1. Right-click on PowerShell" -ForegroundColor White
    Write-Host "2. Select 'Run as Administrator'" -ForegroundColor White
    Write-Host "3. Navigate to: cd '$PSScriptRoot'" -ForegroundColor White
    Write-Host "4. Run: .\install-maven-choco.ps1" -ForegroundColor White
    Write-Host ""
    Write-Host "Or run this command directly:" -ForegroundColor Cyan
    Write-Host "Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))" -ForegroundColor White
    Write-Host ""
    pause
    exit 1
}

Write-Host "Running as Administrator: OK" -ForegroundColor Green
Write-Host ""

# Check if Chocolatey is installed
Write-Host "Checking for Chocolatey..." -ForegroundColor Yellow
$chocoInstalled = Get-Command choco -ErrorAction SilentlyContinue

if (-not $chocoInstalled) {
    Write-Host "Chocolatey is not installed. Installing Chocolatey..." -ForegroundColor Yellow
    Write-Host ""
    
    # Install Chocolatey
    Set-ExecutionPolicy Bypass -Scope Process -Force
    [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072
    iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
    
    # Refresh environment variables
    $env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User")
    
    Write-Host ""
    Write-Host "Chocolatey installed successfully!" -ForegroundColor Green
    Write-Host ""
} else {
    Write-Host "Chocolatey is already installed." -ForegroundColor Green
    Write-Host ""
}

# Check if Maven is already installed
Write-Host "Checking for Maven..." -ForegroundColor Yellow
try {
    $mvnVersion = mvn -version 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Maven is already installed!" -ForegroundColor Green
        Write-Host $mvnVersion
        Write-Host ""
        Write-Host "You can now run: mvn clean install" -ForegroundColor Yellow
        pause
        exit 0
    }
} catch {
    Write-Host "Maven is not installed. Proceeding with installation..." -ForegroundColor Yellow
}

# Install Maven using Chocolatey
Write-Host "Installing Maven using Chocolatey..." -ForegroundColor Yellow
Write-Host "This may take a few minutes..." -ForegroundColor Yellow
Write-Host ""

choco install maven -y

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "Maven installed successfully!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    Write-Host ""
    
    # Refresh environment variables
    $env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User")
    
    Write-Host "Verifying installation..." -ForegroundColor Yellow
    Start-Sleep -Seconds 2
    
    # Verify installation
    try {
        $mvnVersion = mvn -version 2>&1
        Write-Host ""
        Write-Host "Maven Version:" -ForegroundColor Green
        Write-Host $mvnVersion
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Green
        Write-Host "Installation Complete!" -ForegroundColor Green
        Write-Host "========================================" -ForegroundColor Green
        Write-Host ""
        Write-Host "Next steps:" -ForegroundColor Cyan
        Write-Host "1. Close and reopen PowerShell (to refresh PATH)" -ForegroundColor White
        Write-Host "2. Navigate to: cd '$PSScriptRoot'" -ForegroundColor White
        Write-Host "3. Build project: mvn clean install" -ForegroundColor White
        Write-Host "4. Run application: mvn spring-boot:run" -ForegroundColor White
        Write-Host ""
    } catch {
        Write-Host "Maven installed but not in PATH yet." -ForegroundColor Yellow
        Write-Host "Please close and reopen PowerShell, then verify with: mvn -version" -ForegroundColor Yellow
    }
} else {
    Write-Host ""
    Write-Host "ERROR: Maven installation failed!" -ForegroundColor Red
    Write-Host "Please check the error messages above." -ForegroundColor Red
    Write-Host ""
    Write-Host "You can try manual installation:" -ForegroundColor Yellow
    Write-Host "1. Download Maven from: https://maven.apache.org/download.cgi" -ForegroundColor White
    Write-Host "2. See INSTALL_MAVEN.md for detailed instructions" -ForegroundColor White
}

Write-Host ""
Write-Host "Press any key to exit..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")

