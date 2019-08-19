#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
mkdir ../bin
fi

#Find bash file location
SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"

#Check if test files are available
if [ -f FILENAME ]
then
echo "Could not find any test files in: ${SCRIPTPATH}"
exit 1
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src -Xlint:none -d ../bin ../src/main/java/*.java
then
echo "********** BUILD FAILURE **********"
exit 1
fi

#Run test
for f in $SCRIPTPATH/*.in
do
   if [ -f ${f%.in}.out ]
   then
      echo "Running: $(basename $f)"
      java -classpath ../bin Duke < input.txt > $(basename ${f%.in}).actual
      # compare the output to the expected output
      diff -u $(basename ${f%.in}).actual ${f%.in}.out
      if [ $? -eq 0 ]
      then
          echo "Test result: PASSED"
      else
          echo "Test result: FAILED"
      fi
   else
      echo "Could not find output file for $(basename $f)"
   fi
done





