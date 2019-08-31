package duke;

import java.util.Scanner;

/**
 * Ui class that handles user interaction.
 * Stores methods that read the input provided by the user,
 * as well as methods that print messages addressed to the user
 * or appropriate responses to user commands.
 */
public class Ui {

    /** Scanner object that redirects the input to user input. */
    private Scanner scanner = new Scanner(System.in);
    /** Horizontal line to be used extensively in printed messages and responses. */
    private String hrzLine = "    ____________________________________________________________\n";

    /**
     * Prints a welcome message.
     *
     * @return String welcome message.
     */
    public String showWelcome() {
        String store = hrzLine;
        store += "     Hello! I'm Duke\n     What can I do for you?\n";
        store += String.format("%s\n", hrzLine);
        return store;
    }

    /**
     * Reads the user input and returns it as a String.
     *
     * @return String containing the full line of text retrieved from user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the Opening Line.
     */
    public String showOpeningLine() {
        return hrzLine;
    }

    /**
     * Prints the Closing Line.
     */
    public String showClosingLine() {
        return String.format("%s\n", hrzLine);
    }

    /**
     * Prints the message associated with an exception.
     *
     * @param err String containing the message of the exception.
     */
    public void showError(String err) {
        System.out.println(err);
    }

    /**
     * Prints the message associated with a exception
     * that occurred on loading from the file.
     *
     * @param err String containing the message of the exception.
     */
    public void showLoadingError(String err) {
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
