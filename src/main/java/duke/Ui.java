package duke;

/**
 * Ui class that handles user interaction.
 * Stores methods that read the input provided by the user,
 * as well as methods that print messages addressed to the user
 * or appropriate responses to user commands.
 */
public class Ui {

    /** Horizontal line to be used extensively in printed messages and responses. */
    private String hrzLine = "    ____________________________________________________________\n";

    /**
     * Prints a welcome message.
     *
     * @return String welcome message.
     */
    String showWelcome() {
        String store = hrzLine;
        store += "     Hello! I'm Duke\n     What can I do for you?\n";
        store += String.format("%s\n", hrzLine);
        return store;
    }

    /**
     * Prints the Opening Line.
     */
    String showOpeningLine() {
        return hrzLine;
    }

    /**
     * Prints the Closing Line.
     */
    String showClosingLine() {
        return String.format("%s\n", hrzLine);
    }

    /**
     * Prints the message associated with a exception
     * that occurred on loading from the file.
     *
     * @param err String containing the message of the exception.
     */
    void showLoadingError(String err) {
        System.out.println(hrzLine);
        System.out.printf("     %s\n", err);
        System.out.printf("%s\n\n", hrzLine);
    }

    /**
     * Prints the message associated with an exception
     * that occurred on saving to the file.
     *
     * @param err String containing the message of the exception.
     */
    public void showSavingError(String err) {
        showLoadingError(err);
    }

}
