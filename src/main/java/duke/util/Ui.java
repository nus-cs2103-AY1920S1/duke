package duke.util;

import java.util.Scanner;

/**
 * Class that handles all Duke Ui elements, which includes formatting output
 * and printing them to the screen.
 */
public class Ui {
    // Class attributes
    public static final String INDENT = "      ";
    public static final String HELLO = " ___        _        \n"
            + "|  _ \\ _  _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/\\__,_|_|\\_\\___|\n"
            + "Hello! I'm Duke. What can I do for you?";
    public static final String GOODBYE  = "Bye. Hope to see you again soon!\n"
            + "Hang on while I close this window for you...";
    public static final String LOADING_ERROR = "I couldn't find a saved task list...\n"
            + "Let me start up a brand new list!";
    public static final String LINE = "    ____________________________________________________________";

    // Object attributes
    private Scanner sc;

    /**
     * Returns a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a formatted String to the screen. This method first calls
     * indentMessage() to indent all lines within the output.
     *
     * @param message String containing message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.print(this.indentMessage(message));
    }

    /**
     * Prints a line with default indents to the screen. Used to demarcate
     * Duke output from user input.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Returns a String padded with default indentation. This method
     * looks for all newlines within the String and pads the default indentation
     * of 4 spaces to the front.
     *
     * @param s String to be formatted
     * @return String formatted with indentation.
     */
    public String indentMessage(String s) {
        String[] lines = s.split("\n");
        StringBuilder indentedOutput = new StringBuilder();
        for (String line : lines) {
            indentedOutput.append(INDENT);
            indentedOutput.append(line);
            indentedOutput.append("\n");
        }
        return indentedOutput.toString();
    }

    /**
     * Returns a String padded with user-specified indentation. This method
     * looks for all newlines within the String and pre-pads each line with
     * (4 + extraIdent) amount of spaces.
     *
     * @param s String to be formatted
     * @param extraIndent additional number of spaces to be added after
     *                    the default indent
     * @return formatted String
     */
    public String indentMessage(String s, int extraIndent) {
        String[] lines = s.split("\n");
        StringBuilder indentedOutput = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        sb.append(INDENT);
        for (int i = 0; i < extraIndent; i++) {
            sb.append(" ");
        }
        String indent = sb.toString();

        for (String line : lines) {
            indentedOutput.append(indent);
            indentedOutput.append(line);
            indentedOutput.append("\n");
        }
        return indentedOutput.toString();
    }

    /**
     * Prints out a formatted saved list loading error message.
     */
    public void showLoadingError() {
        this.showLine();
        this.displayMessage(LOADING_ERROR);
        this.showLine();
    }
}
