package duke;

import java.util.Scanner;

/**
 * Ui class deals with interactions with the user.
 *
 * @author scwaterbear
 */
public class Ui {

    /** Scanner to receive user input */
    Scanner s;

    /**
     * Class constructor.
     */
    Ui() {
        this.s = new Scanner(System.in);
    }

    /**
     * Prints Duke welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Returns trimmed user input.
     *
     * @return String trimmed user input.
     */
    public String readCommand() {
        return s.nextLine().trim();
    }

    /**
     * Prints a loading error message.
     */
    public void showLoadingError() {
        System.out.println("Error loading data from file");
    }

    /**
     * Prints an error message.
     *
     * @param errorMessage errorMessage to print.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
