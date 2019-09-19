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

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp /Users/sihao/Desktop⁩/NUS\ AY19:20\ Sem\ 1/CS2103/Duke/src -Xlint:none -d ../bin ⁨/Users/sihao/Desktop⁩/NUS\ AY19:20\ Sem\ 1/CS2103/Duke/src/main/java/Duke.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath /Users/sihao/Desktop⁩/NUS\ AY19:20\ Sem\ 1/CS2103/Duke/bin Duke < input.txt > ACTUAL.TXT

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