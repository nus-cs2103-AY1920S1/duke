package duke.ui;

import java.util.Scanner;

public class Ui {

    /**
     * Initialises a Ui.
     */
    public Ui() {
    }

    /**
     * Prints out the logo and welcome message.
     * @return The welcome string.
     */
    public String showWelcome() {
        String s = " __    _  _   _  __  ___    \n"
                + "| _\\  | || | | |/ / | __|   \n"
                + "| v | | \\/ | |   <  | _|    \n"
                + "|__/ \\__/  |_|\\_\\|___|   \n";
        String x = " _                   __    \n"
                + "|  \\ |    | |__/ |__     \n"
                + "|__/\\__/ |   \\ |__    \n"
                + "                       \n";
        String a = " __                   __    \n"
                + "|   \\ |     | |__/ |__     \n"
                + "|__/ \\__/  |  \\  |__    \n"
                + "                       \n";
        return s + "Hello! I'm Duke. \nWhat can I do for you?\n";
    }

    /**
     * Prints out a loading error message.
     */
    public void showLoadingError() {
        System.err.println("Loading error...");
    }

    /**
     * Prints out the given error message.
     *
     * @param message The passed input message
     */
    public void showError(String message) {
        System.err.println(message);
    }

    /**
     * Reads the command input by the user.
     *
     * @return The command input by the user for parsing.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints out the given message.
     *
     * @param msg The passed output message
     */
    public void printMessage(String msg) {
        System.out.println(msg);
    }
}
