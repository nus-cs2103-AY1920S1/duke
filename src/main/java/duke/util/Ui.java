package duke.util;

import java.util.Scanner;

/**
 * Creates a UI that interacts with the user, takes in user input and shows errors.
 */
public class Ui {
    public static final String LOGO =
              " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final Scanner SCAN = new Scanner(System.in);

    public Ui() {
    }

    /**
     * Prints the welcome message for Duke
     */
    public String showLogo() {
        String result = "Hello from\n" + LOGO;
        return result;
    }

    public String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }
    /**
     * Takes in input from the users.
     *
     * @return A line of input from the command prompt.
     */
    public String readCommand() {
        String command = SCAN.nextLine();
        return command;
    }

    /**
     * Displays an error when the data file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("Unable to load files");
    }

    /**
     * Returns error message for all other errors.
     *
     * @param error Returns a String containing an error message.
     */
    public String showError(String error) {
        return(error);
    }

    /**
     * Displays exit message for Duke.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }
}
