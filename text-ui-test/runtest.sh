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

# delete the recorded data from previous run
if [ -e "../data/duke.txt" ]
then
    rm ../data/duke.txt
fi

# compile ALL the java code into the bin folder, terminates if error occurred
if ! javac -cp ../src -Xlint:none -d ../bin ../src/main/java/*.java 
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Test 1
# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin Duke < input.txt > ACTUAL.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test 1 result: PASSED"
else
    echo "Test 1 result: FAILED"
    exit 1
fi

# Test 2
# delete the recorded data from previous run(Test 1)
if [ -e "../data/duke.txt" ]
then
    rm ../data/duke.txt
fi

# run test 2
java -classpath ../bin Duke < input2.txt > ACTUAL2.TXT

diff ACTUAL2.TXT EXPECTED2.TXT
if [ $? -eq 0 ]
then
    echo "Test 2 result: PASSED"
    exit 0
else
    echo "Test 2 result: FAILED"
    exit 1
fi
