package duke.io;

import java.util.ArrayList;

import java.io.PrintStream;

/**
 * Class which acts as the buffer where output is stored before it needs to be displayed, and responsible for
 * formatting the output to be displayed.
 */
public class Output {
    // formatting fields
    private String[] headerLines;
    private String[] footerLines;
    private String leftBorder;
    private int leftIndentWidth;

    // formatting settings
    private boolean printHeaderOn;
    private boolean printFooterOn;
    private boolean printLeftBorderOn;
    private boolean printIndentOn;
    private boolean wrapOn;

    // output buffer
    private ArrayList<String> outputLines;

    //output destination
    private PrintStream printStream;

    /**
     * Constructs the output formatter and buffer
     *
     * @param printStream The print stream which prints the output
     */
    public Output(PrintStream printStream) {
        this.printStream = printStream;

        // no header, footer, or leftborder by default
        headerLines = new String[]{};
        footerLines = new String[]{};
        leftBorder = "";

        // indent width 0 by default
        leftIndentWidth = 0;

        // all true by default, no need to toggle boolean when the header footer etc are set
        printHeaderOn = true;
        printFooterOn = true;
        printLeftBorderOn = true;
        printIndentOn = true;

        // text wrapping off by default
        wrapOn = false;

        // empty buffer
        outputLines = new ArrayList<String>();
    }

    /**
     * Sets the headers for the output
     *
     * @param headerLines The headers for the output, from top to bottom, if any
     */
    public void setHeader(String... headerLines) {
        this.headerLines = headerLines;
    }

    /**
     * Sets the footers for the output
     *
     * @param footerLines The footers for the output, from top to bottom, if any
     */
    public void setFooter(String... footerLines) {
        this.footerLines = footerLines;
    }

    /**
     * Sets the left border of the output
     *
     * @param leftBorder The string be be appended to each line of the output message, if any
     */
    public void setLeftBorder(String leftBorder) {
        this.leftBorder = leftBorder;
    }

    /**
     * Sets the number of spaces to indent the message of each line of output
     *
     * @param count The number of spaces to indent the message by
     */
    public void setLeftIndent(int count) {
        this.leftIndentWidth = count;
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
     * Enables displaying of the headers set for the output handler, if any
     */
    public void setHeaderOn() {
        printHeaderOn = true;
    }

    /**
     * Disables displaying of the headers set for the output handler, if any
     */
    public void setHeaderOff() {
        printFooterOn = false;
    }

    /**
     * Enables displaying of the footers set for the output handler, if any
     */
    public void setFooterOn() {
        printFooterOn = true;
    }

    /**
     * Disables displaying of the footers set for the output handler, if any
     */
    public void setFooterOff() {
        printFooterOn = false;
    }

    /**
     * Enables displaying of the left border set for the output handler, if any
     */
    public void setLeftBorderOn() {
        printLeftBorderOn = true;
    }

    /**
     * Disables displaying of the left border set for the output handler, if any
     */
    public void setLeftBorderOff() {
        printLeftBorderOn = false;
    }

    /**
     * Enables displaying of the indentation set for the output handler, if any
     */
    public void setIndentOn() {
        printIndentOn = true;
    }

    /**
     * Disables displaying of the indentation set for the output handler, if any
     */
    public void setIndentOff() {
        printIndentOn = false;
    }

    /**
     * Enables wrapping of the output message if it would go past the header/footer for the output handler, if any
     */
    public void setWrapOn() {
        wrapOn = true;
    }

    /**
     * Disables wrapping of the output message if it would go past the header/footer for the output handler, if any
     */
    public void setWrapOff() {
        wrapOn = false;
    }

    /**
     * Clears the messages stored in the output handler without displaying it
     */
    public void clear() {
        outputLines = new ArrayList<String>();
    }


    /**
     * Instructs the print stream of the output handler to print the messages stored in the buffer
     */
    public void print() {
        // prints header if enabled
        if (printHeaderOn) {
            for (String header : headerLines) {
                if (printLeftBorderOn) {
                    printStream.print(leftBorder);
                }
                printStream.println(header);
            }
        }

        // split all the output into separate lines if there are still \n or \r left
        outputLines = resolveNewLines(outputLines);

        // appends each line to the left indent and left border if any and enabled
        for (String output : outputLines) {
            if (wrapOn) {
                // chops strings that are too long for the header/footer and appends the next line to the remaining
                for (String wrapped : wrap(outputWidth() - leftIndentWidth, output)) {
                    if (printLeftBorderOn) {
                        printStream.print(leftBorder);
                    }
                    if (printIndentOn) {
                        printStream.print(" ".repeat(leftIndentWidth));
                    }
                    printStream.println(wrapped);
                }
            } else {
                if (printLeftBorderOn) {
                    printStream.print(leftBorder);
                }
                if (printIndentOn) {
                    printStream.print(" ".repeat(leftIndentWidth));
                }
                printStream.println(output);
            }
        }

        //clears the buffer so other output can be added without wrong output
        clear();

        // prints footer appended to leftborder if enabled
        if (printFooterOn) {
            for (String footer : footerLines) {
                if (printLeftBorderOn) {
                    printStream.print(leftBorder);
                }
                printStream.println(footer);
            }
        }

        printStream.println();
    }

    // helper method to calculate the max width of output. minimum output width 1 otherwise infinite loops
    private int outputWidth() {
        int width = 1;

        for (String line : headerLines) {
            width = Math.max(width, line.length());
        }

        for (String line : footerLines) {
            width = Math.max(width, line.length());
        }

        return width;
    }

    // helper method to wrap overlength strings
    private static ArrayList<String> wrap(int width, String unwrapped) {
        ArrayList<String> wrapped = new ArrayList<>();

        String remaining = unwrapped;
        while (remaining.length() > width) {
            wrapped.add(remaining.substring(0, width));
            remaining = remaining.substring(width);
        }
        wrapped.add(remaining);

        return wrapped;
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
