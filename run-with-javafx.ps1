# PowerShell script to run Library Management System with JavaFX

Write-Host "========================================" -ForegroundColor Green
Write-Host "Library Management System" -ForegroundColor Green
Write-Host "Starting with JavaFX support..." -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""

# Build project first
Write-Host "Building project..." -ForegroundColor Yellow
mvn clean compile -q

if ($LASTEXITCODE -ne 0) {
    Write-Host "Build failed!" -ForegroundColor Red
    pause
    exit 1
}

Write-Host "Build successful!" -ForegroundColor Green
Write-Host ""

# Get Maven local repository path
$mavenRepo = "$env:USERPROFILE\.m2\repository"

# Find JavaFX modules
$javafxControls = Get-ChildItem -Path "$mavenRepo\org\openjfx\javafx-controls\21" -Filter "*.jar" -Recurse | Select-Object -First 1
$javafxFxml = Get-ChildItem -Path "$mavenRepo\org\openjfx\javafx-fxml\21" -Filter "*.jar" -Recurse | Select-Object -First 1

if (-not $javafxControls -or -not $javafxFxml) {
    Write-Host "JavaFX modules not found in Maven repository!" -ForegroundColor Red
    Write-Host "Please run: mvn dependency:resolve" -ForegroundColor Yellow
    pause
    exit 1
}

# Get module paths
$javafxDir = Split-Path -Parent $javafxControls.FullName
$modulePath = $javafxDir

Write-Host "Found JavaFX modules at: $modulePath" -ForegroundColor Green
Write-Host ""

# Build classpath
$classpath = "target/classes"
$dependencies = mvn dependency:build-classpath -Dmdep.outputFile=target/classpath.txt 2>$null
if (Test-Path "target/classpath.txt") {
    $cp = Get-Content "target/classpath.txt"
    $classpath = "$classpath;$cp"
}

Write-Host "Starting application..." -ForegroundColor Yellow
Write-Host ""

# Run with JavaFX modules
java --module-path "$modulePath" --add-modules javafx.controls,javafx.fxml -cp "$classpath" com.library.JavafxLauncher

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "Error running application. Trying alternative method..." -ForegroundColor Yellow
    Write-Host "Run: mvn javafx:run" -ForegroundColor Cyan
}

pause

