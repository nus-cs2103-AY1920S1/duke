#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# From: https://stackoverflow.com/questions/6623161/javac-option-to-compile-all-java-files-under-a-given-directory-recursively
# Get all of our java files
find ../src/main -name *.java > sources.txt

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src -Xlint:none -d ../bin @sources.txt
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin duke/Duke < input.txt > ACTUAL.TXT

# Delete the data file after each to run to prevent state from persisting
rm ../data/duke.txt && touch ../data/duke.txt

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
