import java.util.Scanner;

import java.text.SimpleDateFormat;

/**
 * This class handles the user interface – all activities relating to input and
 * output, including format or printing.
 */
class TextUi {

    /* STATIC VARIABLES */

    private static final Scanner scanner = new Scanner(System.in);

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    private static final String INDENT = "    ";

    /**
     * Array of date formats that Duke is able to parse. A valid date can take
     * any of the following formats:
     * 1. dd-MM-yyyy
     * 2. hh:mm
     * 3. dd-MM-yyyy hh:mm
     * 4. EEE, dd MMM yy, hh:mm
     */
    static final SimpleDateFormat[] DATE_FORMATS = {
            new SimpleDateFormat("EEE, dd MMM yy, hh:mm"),
            new SimpleDateFormat("dd-MM-yy hh:mm"),
            new SimpleDateFormat("dd-MM-yyyy"),
            new SimpleDateFormat("dd-MM-yy"),
            new SimpleDateFormat("hh:mm"),
            new SimpleDateFormat("EEE")
    };

    /* METHODS TO READ INPUT */

    /**
     * Reads a command from standard input. If user input is terminated,
     * automatically returns an exit command.
     * @return  String representing a user-given command
     */
    String readCommand() {
        return scanner.hasNext() ? scanner.nextLine() : "bye";
    }

    /* METHODS TO DISPLAY OUTPUT */

    /**
     * Prints a horizontal line of width 60 characters.
     */
    void showLine() {
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    /**
     * Prints the given text with indentation of five spaces.
     * @param text      Single line of text to be printed
     */
    // TODO: Wrap text for longer lines
    private void show(String text) {
        System.out.println(INDENT + " " + text);
    }

    /**
     * Prints the items in the given list with their corresponding index
     * numbers. A horizontal line is printed above and below the list, and
     * output is indented throughout.
     * @param list      A task list to be printed
     */
    void showList(TaskList list) {
        for (int i = 1; i <= list.size(); i++) {
            show(i + "." + list.get(i - 1).toString());
        }
    }

    /**
     * Prints the given text with a horizontal line above and below the text
     * and appropriate indentation.
     * @param text      Formatted text with appropriate line breaks. Maximum
     *                  width for each line is 58 characters.
     */
    void showText(String text) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            show(line);
        }
    }

    /**
     * Prints an apology followed by details of the given exception.
     * @param message   Error message from exception that caused the error
     */
    void showErrorMessage(String message) {
        showText("Sorry, " + message);
    }

    /**
     * Prints a welcome message that consists of the Duke logo and a greeting.
     */
    void showWelcomeMessage() {
        showLine();
        showText(LOGO + "\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?");
        showLine();
    }

    /**
     * Prints a message indicating that tasks could not be loaded from storage.
     */
    void showLoadingError() {
        showLine();
        showText("Sorry, I could not retrieve your previous tasks.");
        showLine();
    }
}
