@echo off
echo --------------------------------
echo Cleaning old build directories...
echo --------------------------------

rem Delete bin and deploy directories if they exist
if exist bin rmdir /S /Q bin
if exist deploy rmdir /S /Q deploy

echo --------------------------------
echo Compiling Java Project...
echo --------------------------------

rem Create bin directory
mkdir bin

rem Compile Java files
javac -cp "lib/*" -d bin src/*.java
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Compilation failed!
    exit /b %ERRORLEVEL%
)

echo ✅ Compilation Successful!
echo --------------------------------
echo Running Application...
echo --------------------------------

rem Run the project
java -cp "bin;lib/*" Main
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Execution failed!
    exit /b %ERRORLEVEL%
)

echo ✅ Application ran successfully!
echo --------------------------------
echo Starting Deployment...
echo --------------------------------

rem Create deployment directories
mkdir deploy
mkdir deploy\bin
mkdir deploy\lib

rem Copy compiled files and libraries
xcopy /E /Y bin deploy\bin >nul
xcopy /E /Y lib deploy\lib >nul

echo ✅ Deployment completed! Files are in the deploy\ directory.