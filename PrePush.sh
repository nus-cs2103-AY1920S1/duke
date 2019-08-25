#!/bin/bash

echo "Running integrated tests..."
./TestScript.sh

echo "Checking formatting..."
./gradlew checkstyleMain checkstyleTest