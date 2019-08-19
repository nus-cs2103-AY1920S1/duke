@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\out mkdir ..\out

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the out folder
javac  -cp ..\src -Xlint:none -d ..\out ..\src\main\java\Duke.java
IF ERRORLEVEL 1 (
   echo ********** BUILD FAILURE **********
   exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\out main/java/Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT