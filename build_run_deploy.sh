#!/bin/bash

echo "--------------------------------"
echo "Compiling Java Project..."
echo "--------------------------------"

# Ensure bin directory exists
mkdir -p bin

# Compile Java files
javac -cp "lib/*" -d bin src/*.java
if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi

echo "--------------------------------"
echo "Compilation Successful!"
echo "Running Application..."
echo "--------------------------------"

# Run the project
java -cp "bin:lib/*" Main
if [ $? -ne 0 ]; then
    echo "Execution failed!"
    exit 1
fi

echo "--------------------------------"
echo "Deployment Process Started..."
echo "--------------------------------"

# Create deployment directory
mkdir -p deploy/bin deploy/lib deploy/src

# Copy compiled files to deploy directory
cp -r bin/* deploy/bin/
cp -r lib/* deploy/lib/
cp -r src/* deploy/src/

echo "Deployment completed! Deployed to deploy/ directory."