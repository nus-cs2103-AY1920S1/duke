package duke;

import java.util.Scanner;

/**
 * Ui class that handles user interaction.
 * Stores methods that read the input provided by the user,
 * as well as methods that print messages addressed to the user
 * or appropriate responses to user commands.
 */
public class Ui {

    /** Scanner object that redirects the input to user input */
    private Scanner scanner = new Scanner(System.in);
    /** Horizontal line to be used extensively in printed messages and responses */
    private String hLine = "    ____________________________________________________________";

    /**
     * Prints a welcome message.
     */
    public void showWelcome() {
        System.out.println(hLine);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.printf("%s\n\n", hLine);
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
    public void showOpeningLine() {
        System.out.println(hLine);
    }

    /**
     * Prints the Closing Line.
     */
    public void showClosingLine() {
        System.out.printf("%s\n\n", hLine);
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
        System.out.println(hLine);
        System.out.printf("     %s\n", err);
        System.out.printf("%s\n\n", hLine);
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
