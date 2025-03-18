@echo off
echo --------------------------------
echo Compiling Java Project...
echo --------------------------------

rem Ensure the bin directory exists
if not exist bin mkdir bin

rem Compile Java files
javac -cp "lib/*" -d bin src/*.java
if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    exit /b %ERRORLEVEL%
)

echo --------------------------------
echo Compilation Successful!
echo Running Application...
echo --------------------------------

rem Run the project
java -cp "bin;lib/*" Main
if %ERRORLEVEL% NEQ 0 (
    echo Execution failed!
    exit /b %ERRORLEVEL%
)

echo --------------------------------
echo Deployment Process Started...
echo --------------------------------

rem Create deployment directory
if not exist deploy mkdir deploy

rem Copy compiled files to deploy directory
rem Ensure deployment directories exist
if not exist deploy mkdir deploy
if not exist deploy\bin mkdir deploy\bin
if not exist deploy\lib mkdir deploy\lib
if not exist deploy\src mkdir deploy\src

rem Copy compiled files to deploy directory
xcopy /E /Y bin deploy/bin
xcopy /E /Y lib deploy/lib
xcopy /E /Y src deploy/src

echo Deployment completed! Deployed to deploy/ directory.
