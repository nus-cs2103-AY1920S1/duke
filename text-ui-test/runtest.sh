#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    echo "Removing ACTUAL.TXT from previous test"
    rm ACTUAL.TXT
fi


# compile the code into the bin folder, terminates if error occurred
find ../src/main -name "*.java" > sources.txt
if ! javac @sources.txt -d ../bin
#if ! javac -cp ../src -Xlint:none -d ../bin ../src/main/java/duke.util.Duke.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# delete source file directories 
if [ -e "./sources.TXT" ]
then
    echo "Removing sources.txt after compilation"
    rm sources.TXT
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin duke.util.Duke < input.txt > ACTUAL.TXT

# remove history generated from test
if [ -d "./saved" ]
then
    echo "Removing saved folder from previous test"
    rm -r saved
fi

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
