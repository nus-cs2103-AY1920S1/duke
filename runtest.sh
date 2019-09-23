#!/bin/bash

if ! gradle assemble; then
    echo "Build failed."
    exit 1
fi

diff <(java -cp build/classes/java/main seedu.duke.Duke < input.txt) expected.txt
if [ $? -eq 0 ]; then
    echo "Output matches."
    exit 0
else
    echo "Output mismatch."
    exit 1
fi
