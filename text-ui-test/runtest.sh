#!/bin/bash

# Note: this script assumes the date file is stored in ~/.local/share/Duke

# Backup existing data file
data_dir="$HOME/.local/share/Duke"
if [ -f "$data_dir/duke.txt" ]; then
  mv "$data_dir/duke.txt" "$data_dir/duke.bak"
fi

# Delete output from previous run
if [ -e "./actual.txt" ]; then
    rm actual.txt
fi

# run the program, feed commands from input.txt file and redirect the output to the actual.txt
java -classpath ../build/classes/java/main duke.Launcher cli < input.txt > actual.txt

# compare the output to the expected output
diff actual.txt expected.txt
result="$?"

# Move the old data file back
if [ -f "$data_dir/duke.bak" ]; then
  mv "$data_dir/duke.bak" "$data_dir/duke.txt"
fi

exit "$result"
