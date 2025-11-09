@echo off
echo ========================================
echo Library Management System
echo Starting with JavaFX support...
echo ========================================
echo.

REM Get JavaFX modules from Maven dependencies
setlocal enabledelayedexpansion

REM Build the classpath with JavaFX dependencies
call mvn dependency:build-classpath -DincludeScope=compile -Dmdep.outputFile=target\classpath.txt >nul 2>&1

REM Read classpath
set CLASSPATH=
if exist target\classpath.txt (
    for /f "delims=" %%i in (target\classpath.txt) do set CLASSPATH=%%i
)

REM Extract JavaFX module path
set JAVAFX_MODULES=
for %%p in (%CLASSPATH%) do (
    set path_part=%%p
    if "!path_part:javafx-controls=!" NEQ "!path_part!" (
        set "JAVAFX_MODULES=!JAVAFX_MODULES!;%%~dpi"
    )
    if "!path_part:javafx-fxml=!" NEQ "!path_part!" (
        set "JAVAFX_MODULES=!JAVAFX_MODULES!;%%~dpi"
    )
)

REM Clean up module path (remove duplicates and format)
set JAVAFX_MODULES=%JAVAFX_MODULES:;=;%
set JAVAFX_MODULES=%JAVAFX_MODULES:~1%

echo Building project...
call mvn clean compile -q

if %ERRORLEVEL% NEQ 0 (
    echo Build failed!
    pause
    exit /b 1
)

echo.
echo Starting application...
echo.

REM Run with JavaFX modules
java --module-path "%JAVAFX_MODULES%" --add-modules javafx.controls,javafx.fxml -cp "target/classes;%CLASSPATH%" com.library.LibraryApplication

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo If you get JavaFX errors, try running with:
    echo mvn javafx:run
    pause
)

