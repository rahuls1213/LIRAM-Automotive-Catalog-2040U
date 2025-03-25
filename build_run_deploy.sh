#!/bin/bash

echo "--------------------------------"
echo "Compiling Java Project..."
echo "--------------------------------"

# Clean and recreate bin directory
rm -rf bin/
mkdir -p bin

# Compile Java files
javac -cp "lib/*" -d bin src/*.java
if [ $? -ne 0 ]; then
    echo "❌ Compilation failed!"
    exit 1
fi

echo "✅ Compilation Successful!"
echo "--------------------------------"
echo "Running Application..."
echo "--------------------------------"

# Run the project
java -cp "bin:lib/*" Main
if [ $? -ne 0 ]; then
    echo "❌ Execution failed!"
    exit 1
fi

echo "✅ Application ran successfully!"
echo "--------------------------------"
echo "Starting Deployment..."
echo "--------------------------------"

# Clean and recreate deploy directory (no src duplication)
rm -rf deploy/
mkdir -p deploy/bin deploy/lib

# Copy compiled classes and libraries
cp -r bin/* deploy/bin/
cp -r lib/* deploy/lib/

echo "✅ Deployment completed! Files are in the deploy/ directory."