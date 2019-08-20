#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./actual$1.txt" ]
then
    rm actual$1.txt
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -classpath ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Duke < input$1.txt > actual$1.txt

# compare the output to the expected output
diff actual$1.txt output$1.txt
if [ $? -eq 0 ]
then
    echo "Test $1 result: PASSED"
    exit 0
else
    echo "Test $1 result: FAILED"
    exit 1
fi
