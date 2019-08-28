package duke.ui;

import java.util.Scanner;

/**
 * Represents the UI of the {@link duke.Duke} programme.
 */
public class Ui {

    private static final String INDENT = "    ";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints out a line.
     */
    public void showLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }

    /**
     * Prints out an indented message.
     *
     * @param message the message to be printed out.
     */
    public void showMessage(String message) {
        System.out.println(INDENT + " " + message);
    }

    /**
     * Prints out an indented error message.
     *
     * @param error the error message to be printed out.
     */
    public void showError(String error) {
        showLine();
        showMessage("☹ OOPS!!! " + error);
        showLine();
    }

    /**
     * Prints out a welcome message.
     */
    public void showWelcome() {
        showLine();
        showMessage("Hello! I'm Duke");
        showMessage("What can I do for you?");
        showLine();
    }

    /**
     * Advances this {@link Scanner} past the current line and returns the command that was skipped.
     * @return the command that was skipped.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
