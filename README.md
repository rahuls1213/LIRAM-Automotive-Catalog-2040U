# LIRAM Inc. - Automotive Catalog

This project is an interactive Java-based Car Viewer application built using Swing and FlatLaf, featuring:

- User authentication (login/register/guest)
- Browsable vehicle catalog with images, reviews, price, year, fuel type
- Favorites system tied to user accounts
- Car comparison tool
- Light/Dark mode toggle
- Cross-platform deployment via `.bat` and `.sh` scripts

This README serves as a complete build + run guide and final documentation

---

## **Prerequisites**
Before running the build scripts, make sure you have:
- **Java Development Kit (JDK 17 or later)** installed  
- **Git** installed (if cloning the repo)  
- **Required JAR dependencies** (already included in `lib/`)
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
│── bin/                  # Compiled Java class files (generated)
│── data/                 # JSON files for cars and users (cars.json, users.json)
│── deploy/               # Packaged output or runtime builds
│── data/                 # FinalTestReport.pdf and other documentation
│── lib/                  # JAR dependencies (FlatLaf, JSON, JUnit, etc.)
│── src/                  # Source code (Java files)
│── test/                 # JUnit unit tests
│── build_run_deploy.bat  # Windows build + run script
│── build_run_deploy.sh   # Mac/Linux build + run script
│── README.md             # Documentation (this file)
│── CONTRIBUTIONS.md      # Team member GitHub contribution breakdown
│── .gitignore            # Git ignore rules
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
```
---
## **Test Report**
[`docs/FinalTestReport.pdf`](docs/FinalTestReport.pdf)

Includes:

- Unit test results (JUnit 5)
- Integration test results (workflows)
- System Tests (manually tested functional aspects)

All tests passed successfully. Screenshots and detailed evidence are included in the PDF.

## **Team Contributions**

[`CONTRIBUTIONS.md`](CONTRIBUTIONS.md)

This file contains:

- Git commit summaries per teammate
- Pull request and issue history
- Role responsibilities and non-code contributions (e.g., project planning, testing, design)

## Demonstration of Functionality

A short walkthrough video is available at:  
[Demo Video](https://youtu.be/lasfK1eWVCg)

The demo includes:
- Application startup and login/register/guest flow  
- Vehicle catalog with search, sort, and dark mode  
- Viewing car details and reviews  
- Adding/removing from favorites  
- Car comparison feature

Optionally, see `FinalTestReport.pdf` for key UI snapshots if the video is unavailable.