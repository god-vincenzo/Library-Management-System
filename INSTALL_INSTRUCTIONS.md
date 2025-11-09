# Quick Install Instructions - Maven via Chocolatey

## Step 1: Open PowerShell as Administrator

1. Press `Windows Key + X`
2. Select "Windows PowerShell (Admin)" or "Terminal (Admin)"
3. Click "Yes" when prompted by User Account Control

## Step 2: Navigate to Project Directory

```powershell
cd C:\Users\mbads\OneDrive\Documents\nnnnnew\library-management-system
```

## Step 3: Run Installation Script

```powershell
.\install-maven-choco.ps1
```

## Alternative: Manual Installation

If the script doesn't work, run these commands manually in Administrator PowerShell:

### Install Chocolatey (if not installed):
```powershell
Set-ExecutionPolicy Bypass -Scope Process -Force
[System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072
iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
```

### Install Maven:
```powershell
choco install maven -y
```

### Verify Installation:
```powershell
mvn -version
```

## Step 4: Close and Reopen PowerShell

After installation, close PowerShell and open a new window to refresh the PATH environment variable.

## Step 5: Build and Run Project

```powershell
cd C:\Users\mbads\OneDrive\Documents\nnnnnew\library-management-system
mvn clean install
mvn spring-boot:run
```

## Troubleshooting

### If Chocolatey installation fails:
- Make sure you're running as Administrator
- Check your internet connection
- Try the manual installation steps above

### If Maven is not recognized after installation:
- Close and reopen PowerShell
- Verify PATH: `$env:PATH`
- Manually add Maven to PATH if needed:
  - System Properties > Environment Variables
  - Add `C:\ProgramData\chocolatey\lib\maven\apache-maven-*\bin` to PATH

### If you get "execution policy" error:
```powershell
Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
```

## Success!

Once Maven is installed, you can:
1. Build: `mvn clean install`
2. Run: `mvn spring-boot:run`
3. Login: admin/admin123

## Need Help?

See `BUILD_WITHOUT_MAVEN.md` for alternative methods (using IDE without Maven installation).

