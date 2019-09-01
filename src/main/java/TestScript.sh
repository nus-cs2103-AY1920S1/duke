#!/bin/bash

echo "Changing to correct directory..."
cd /Users/larrylaw/Learn/Uni/Y2S1/CS2103/duke/src/main/java;

echo "Removing saved data..."
rm ../data/duke.txt

echo "Compiling all java files in this directory..."
if ! javac $(find duke/. -name "*.java") && javac Duke.java;
then
    echo "Compilation Failed"
    exit 0;
else
    echo "Compiled java files successfully"
fi

# For each input, generate its output, and chk its diff
echo "Checking Output.txt against Expected.txt..."
java Duke < Test/Input.txt > Test/Output.txt
colordiff Test/Output.txt Test/Expected.txt

echo "Checking data saved..."
colordiff ../data/duke.txt ../data/Expected.txt

echo "Removing saved data..."
rm ../data/duke.txt