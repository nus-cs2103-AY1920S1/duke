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
     */
    public void showWelcome() {
        this.showLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        this.showLine();
    }

    /**
     * Prints out a horizontal line.
     */
    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
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
