@echo off
setlocal

cd /d "%~dp0"

set "JDK_BIN=C:\Program Files\Eclipse Adoptium\jdk-17.0.18.8-hotspot\bin"

if exist "%JDK_BIN%\javac.exe" (
    "%JDK_BIN%\javac.exe" *.java
    if errorlevel 1 (
        echo Error al compilar.
        pause
        exit /b 1
    )

    "%JDK_BIN%\java.exe" -cp "." Login
    if errorlevel 1 (
        echo Error al ejecutar Login.
        pause
        exit /b 1
    )
) else (
    javac *.java
    if errorlevel 1 (
        echo Error al compilar. Verifica Java/JDK instalado.
        pause
        exit /b 1
    )

    java -cp "." Login
    if errorlevel 1 (
        echo Error al ejecutar Login.
        pause
        exit /b 1
    )
)

endlocal
