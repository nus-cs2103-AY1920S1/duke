@ECHO OFF

REM create \build\text-ui-test directory if it doesn't exist
if not exist ..\build\text-ui-test mkdir ..\build\text-ui-test

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\build\text-ui-test ..\src\main\java\weomucat\duke\Duke.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\build\text-ui-test -Dfile.encoding=UTF-8 weomucat.duke.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT