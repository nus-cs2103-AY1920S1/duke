package duke.util;

import java.util.Scanner;

/**
 * Class that handles all Duke Ui elements, which includes formatting output
 * and printing them to the screen.
 */
public class Ui {
    // Class attributes
    private static final String INDENT = "      ";

    // Object attributes
    private Scanner sc;

    /**
     * Returns a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns a string containing input prompt from the user.
     *
     * @return String containing user input
     */
    public String getUserCommand() {
        System.out.println(); // Injects a spacing for future user input
        return sc.nextLine();
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
     * Prints a formatted String to the screen. This overloaded method provides
     * an additional field for specifiying additional indents to pass to
     * indentMessage().
     *
     * @param message String to be formatted and printed to screen
     * @param extraIndent number of extra leading spaces on top of default indent
     */
    public void displayMessage(String message, int extraIndent) {
        System.out.print(this.indentMessage(message, extraIndent));
    }

    /**
     * Prints a single-line formatted String with default indent to the screen.
     *
     * @param message String to be formatted and printed to screen
     */
    public void displaySingleLine(String message) {
        System.out.println(INDENT + message);
    }

    /**
     * Prints a line with default indents to the screen. Used to demarcate
     * Duke output from user input.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
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
     * Prints out a formatted hello message with its
     * own enclosing lines.
     */
    public void greetHello() {
        String hello = "Hello! I'm Duke\n"
                + "What can I do for you?";
        this.showLine();
        this.displayMessage(hello);
        this.showLine();
    }

    /**
     * Prints out a formatted goodbye greeting on the screen.
     */
    public void greetGoodbye() {
        String goodbye = "Bye. Hope to see you again soon!";
        //this.showLine();
        this.displaySingleLine(goodbye);
        //this.showLine();
    }

    /**
     * Prints out a formatted saved list loading error message.
     */
    public void showLoadingError() {
        this.showLine();
        this.displayMessage("I couldn't find a saved task list...\n"
                + "Let me start up a brand new list!");
        this.showLine();
    }

    /*
     Prints the target string between two horizontal
     bars. Adds a newline to the input string
     before printing. Currently not used.

     @param output  The string to be printed
     */
    private void formattedPrintln(String output) {
        System.out.println("____________________________________________________________\n"
                + output
                + "\n"
                + "____________________________________________________________\n");
    }

    /*
     Prints the target string between two horizontal
     bars. Newline is not added to input string.
     Currently not used.

     @param output  The string to be printed
    */
    private void formattedPrint(String output) {
        System.out.println("____________________________________________________________\n"
                + output
                + "____________________________________________________________\n");
    }
}
