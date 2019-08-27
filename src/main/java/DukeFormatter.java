import java.text.SimpleDateFormat;
import java.util.List;
import java.text.SimpleDateFormat;

class DukeFormatter {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    private static final String INDENT = "    ";

    /**
     * An array of date formats that Duke can parse. A valid date can take any
     * of the following formats:
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

    /**
     * Prints a horizontal line of width 60 characters.
     */
    private static void printBorder() {
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    /**
     * Prints the given text with indentation of five spaces.
     * @param text      Single line of text to be printed
     */
    private static void print(String text) {
        System.out.println(INDENT + " " + text);
    }

    /**
     * Prints the given text with a horizontal line above and below the text
     * and appropriate indentation.
     * @param text      Formatted text with appropriate line breaks. Maximum
     *                  width for each line is 58 characters.
     */
    static void prettyPrint(String text) {
        String[] lines = text.split("\n");
        printBorder();
        for (String line : lines) {
            print(line);
        }
        printBorder();
    }

    /**
     * Prints the items in the given list with their corresponding index
     * numbers. A horizontal line is printed above and below the list, and
     * output is indented throughout.
     * @param list      A List of Tasks to be printed.
     */
     // TODO: Wrap text for longer user input
     static void prettyPrint(List<Task> list) {
        printBorder();
        for (int i = 1; i <= list.size(); i++) {
            print(i + "." + list.get(i - 1).toString());
        }
        printBorder();
     }

    /**
     * Prints a welcome message that consists of the Duke logo and a greeting.
     */
    static void printWelcomeMessage() {
         prettyPrint(LOGO + "\n"
                 + "Hello! I'm Duke\n"
                 + "What can I do for you?");
     }

    /**
     * Prints an apology followed by details of the given exception.
     * @param exception     Exception that caused the error
     */
    static void printErrorMessage(Exception exception) {
         prettyPrint("Sorry, " + exception.getMessage());
     }
}
