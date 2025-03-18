# Automotive Catalog - Build & Deployment Guide

This project provides a **Car Viewer Application** with an interactive UI, built using Java and FlatLaf.  
This guide will help you **install dependencies, compile, run, and deploy the application** using our automated build scripts.

---

## **Prerequisites**
Before running the build scripts, make sure you have:
- ✅ **Java Development Kit (JDK 17 or later)** installed  
- ✅ **Git** installed (if cloning the repo)  
- ✅ **Required JAR dependencies** (already included in `lib/`)
    - flatlaf-3.5.2.jar	// Provides the modern FlatLaf UI theme for styling the GUI
    - json-20250107.jar	// Supports JSON parsing for handling vehicle data
    - junit-jupiter-api-5.9.3.jar // Needed for JUnit 5 testing framework
    - junit-jupiter-engine-5.9.3.jar // Runs JUnit 5 unit tests
    - junit-platform-console-standalone-1.9.3.jar // Enables command-line test execution

To check if Java is installed, run:
```sh
java -version
```

To check if Git is installed, run:
```sh
git --version
```
### 1. Cloning the GitHub Repository

git clone https://github.com/YOUR_USERNAME/Automotive-Catalog-2040U.git
cd Automotive-Catalog-2040U

### 2. Running the Build Script

#### Windows Users: 

Run the following command in PowerShell or Command Prompt:

build_run_deploy.bat

Or, if using PowerShell:
```sh
.\build_run_deploy.bat
```

This will:
- Compile Java files into the bin/ directory.
- Run the application with the required dependencies.
- Deploy all necessary files into deploy/.

If the script runs successfully, you should see:
```sql
Compilation Successful!
Running Application...
Deployment completed!
```
#### Mac/Linux Users
Run the following command in Terminal:
```sh
chmod +x build_run_deploy.sh  # (Only needed the first time)
./build_run_deploy.sh
```
The script does the same steps as the Windows version.

### 3. Manual Compilation & Execution (If Needed)

Compile
```sh
javac -cp "lib/*" -d bin src/*.java
```
Run 
```sh
java -cp "bin;lib/*" Main  # Windows
java -cp "bin:lib/*" Main  # Mac/Linux (Use `:` instead of `;`)
```
### 4. Project Structure
```bash
Automotive-Catalog-2040U/
│── bin/               # Compiled Java class files (generated)
│── deploy/            # Deployment directory (generated)
│── lib/               # JAR dependencies (FlatLaf, JSON, JUnit, etc.)
│── src/               # Source code (Java files)
│── test/              # Unit tests
│── build_run_deploy.bat   # Windows build script
│── build_run_deploy.sh    # Mac/Linux build script
│── README.md          # Documentation (this file)
│── .gitignore         # Git ignore rules
```

### Troubleshooting
#### 1. "Command not found" when running the script
Ensure you're in the correct directory:
```sh
cd /path/to/Automotive-Catalog-2040U
```
Try adding execution permissions on Mac/Linux:
```sh
chmod +x build_run_deploy.sh
```
#### 2. "javac not recognized" or "java command not found"
Check if Java is installed and added to the system path:
```sh
java -version
javac -version