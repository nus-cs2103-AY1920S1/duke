package duke.ui;

import java.util.Scanner;

/**
 * Represents the user interface for the Duke application.
 * Controls how outputs are shown to the user.
 */
public class Ui {

    Scanner sc;

    /**
     * Initialises the UI class with a new scanner to take in user inputs.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Shows welcome message.
     */
    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Ui.sendLine();
        Ui.sendGreet();
        Ui.sendLine();
    }

    /**
     * Reads in user inputs.
     *
     * @return the current line of active user command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints line divider.
     */
    public static void sendLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints greetings to the user.
     */
    public static void sendGreet() {
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
    }

    /**
     * Shows an error to the user when no previously saved file is found.
     */
    public static void showLoadingError() {
        sendLine();
        System.out.println("File not found. Creating new list...");
        sendLine();
    }

    /**
     * Prints any messages the Duke application wants to communicate with the user, with the correct indentation.
     *
     * @param input messages from Duke.
     */
    public static void sendMessage (String input) {
        System.out.println("     " + input);
    }

    /**
     * Prints farewell message to the user.
     */
    public static void sendBye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

}
