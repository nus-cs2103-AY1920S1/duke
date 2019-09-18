package duke.util;

/**
 * This class handles all activities relating to the user interface, including
 * formatting or displaying input and output.
 */
public class TextUi {

    /* STATIC VARIABLES */

    /** Horizontal line of width 60 characters. */
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    /** Four space indentation. */
    private static final String INDENT = "    ";

    /* METHODS TO DISPLAY OUTPUT */

    /**
     * Prints the given text with indentation of five spaces.
     *
     * @param text Single line of text to be printed.
     */
    private void show(String text) {
        System.out.println(INDENT + " " + text);
    }

    /**
     * Prints a horizontal line with indentation.
     */
    public void showLine() {
        System.out.println(INDENT + HORIZONTAL_LINE);
    }

    /**
     * Prints the given text with appropriate indentation.
     *
     * @param text Formatted text with appropriate line breaks. Maximum width
     *             of each line is 58 characters.
     */
    public void showText(String text) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            show(line);
        }
    }

    /**
     * Prints a message indicating that tasks were not loaded from storage.
     */
    public void showLoadingError() {
        showLine();
        showText("Sorry, I could not retrieve your previous tasks.");
    }
}
