package duke.io;

import java.util.ArrayList;

/**
 * Class which acts as the buffer where output is stored before it needs to be displayed, and responsible for
 * formatting the output to be displayed.
 */
public class Output {

    // output buffer
    private ArrayList<String> outputLines;

    public Output() {
        outputLines = new ArrayList<String>();
    }

    /**
     * Adds a line to the output message
     *
     * @param lineSegments The segments of the string meant to be displayed on the same line, if not wrapped
     * @return The output handler which the line was added to
     */
    public Output addLine(String... lineSegments) {
        // string concatenation behind the scenes
        StringBuffer line = new StringBuffer();
        for (String segment : lineSegments) {
            line.append(segment);
        }
        outputLines.add(line.toString());
        return this;
    }

    /**
     * Clears the messages stored in the output handler without displaying it
     */
    public void clear() {
        outputLines = new ArrayList<String>();
    }

    public String dumpMessage() {
        StringBuffer copy = new StringBuffer();
        for (String s : resolveNewLines(outputLines)) {
            copy.append(s + "\n");
        }
        clear();
        return copy.toString();
    }

    public String copyMessage() {
        StringBuffer copy = new StringBuffer();
        for (String s : resolveNewLines(outputLines)) {
            copy.append(s + "\n");
        }
        return copy.toString();
    }

    // helper method to list of strings which may include new line characters with list of single line strings
    private static ArrayList<String> resolveNewLines(ArrayList<String> lines) {
        ArrayList<String> result = new ArrayList<>();

        for (String line : lines) {
            for (String lineSplit : line.split("[\\n\\r]")) {
                result.add(lineSplit);
            }
        }

        return result;
    }
}
