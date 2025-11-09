# Maven Setup Script for Windows
Write-Host "Maven Setup for Library Management System" -ForegroundColor Green
Write-Host "==========================================" -ForegroundColor Green
Write-Host ""

# Check if Maven is already installed
try {
    $mvnVersion = mvn -version 2>&1
    Write-Host "Maven is already installed!" -ForegroundColor Green
    Write-Host $mvnVersion
    Write-Host ""
    Write-Host "You can now run: mvn clean install" -ForegroundColor Yellow
    exit 0
} catch {
    Write-Host "Maven is not installed." -ForegroundColor Yellow
}

Write-Host ""
Write-Host "Please choose an installation method:" -ForegroundColor Cyan
Write-Host "1. Install using Chocolatey (requires admin)" -ForegroundColor White
Write-Host "2. Install using Scoop" -ForegroundColor White
Write-Host "3. Manual installation instructions" -ForegroundColor White
Write-Host "4. Use IDE instead (IntelliJ IDEA - recommended)" -ForegroundColor White
Write-Host ""

$choice = Read-Host "Enter your choice (1-4)"

switch ($choice) {
    "1" {
        Write-Host "Installing Maven using Chocolatey..." -ForegroundColor Yellow
        Write-Host "Note: This requires administrator privileges" -ForegroundColor Yellow
        Write-Host ""
        Write-Host "Run this command in Administrator PowerShell:" -ForegroundColor Cyan
        Write-Host "choco install maven" -ForegroundColor White
        Write-Host ""
        Write-Host "After installation, close and reopen PowerShell, then run:" -ForegroundColor Cyan
        Write-Host "mvn clean install" -ForegroundColor White
    }
    "2" {
        Write-Host "Installing Maven using Scoop..." -ForegroundColor Yellow
        Write-Host ""
        Write-Host "Run this command:" -ForegroundColor Cyan
        Write-Host "scoop install maven" -ForegroundColor White
        Write-Host ""
        Write-Host "After installation, run:" -ForegroundColor Cyan
        Write-Host "mvn clean install" -ForegroundColor White
    }
    "3" {
        Write-Host "Manual Installation Instructions:" -ForegroundColor Yellow
        Write-Host ""
        Write-Host "1. Download Maven from: https://maven.apache.org/download.cgi" -ForegroundColor White
        Write-Host "2. Extract to C:\Program Files\Apache\maven" -ForegroundColor White
        Write-Host "3. Add C:\Program Files\Apache\maven\bin to PATH" -ForegroundColor White
        Write-Host "4. Restart PowerShell" -ForegroundColor White
        Write-Host "5. Verify: mvn -version" -ForegroundColor White
        Write-Host ""
        Write-Host "See INSTALL_MAVEN.md for detailed instructions" -ForegroundColor Cyan
    }
    "4" {
        Write-Host "Using IDE (Recommended for quick start):" -ForegroundColor Yellow
        Write-Host ""
        Write-Host "IntelliJ IDEA Community Edition (Free):" -ForegroundColor Cyan
        Write-Host "1. Download: https://www.jetbrains.com/idea/download/" -ForegroundColor White
        Write-Host "2. Install and open IntelliJ" -ForegroundColor White
        Write-Host "3. Open the library-management-system folder" -ForegroundColor White
        Write-Host "4. IntelliJ will auto-detect Maven and download dependencies" -ForegroundColor White
        Write-Host "5. Right-click LibraryApplication.java > Run" -ForegroundColor White
        Write-Host ""
        Write-Host "No Maven installation needed!" -ForegroundColor Green
    }
    default {
        Write-Host "Invalid choice" -ForegroundColor Red
    }
}

Write-Host ""
Write-Host "Press any key to exit..."
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")

