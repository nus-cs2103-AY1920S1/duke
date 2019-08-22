
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
   if ! javac -cp ~/Desktop/CS2103T/duke/src -Xlint:none -d ~/Desktop/CS2103T/duke/bin ~/Desktop/CS2103T/duke/src/main/java/*.java
   then
       echo "********** BUILD FAILURE **********"
       exit 1
   fi
   
   # run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
   java -classpath ~/Desktop/CS2103T/duke/bin Duke < input.txt > ACTUAL.TXT
   
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

