package duke.ui;

import java.util.Scanner;

/**
 * Ui class that handles all IO for the Duke application.
 */
public class Ui {
    private Scanner reader;

    /**
     * Constructor for the Ui class, initiates a new Scanner instance.
     */
    public Ui() {
        reader = new Scanner(System.in);
    }

    /**
     * Prints out welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * Reads next command.
     *
     * @return String - next command.
     */
    public String readCommand() {
        return reader.nextLine();
    }

    /**
     * Prints line.
     *
     * @param line line to be printed.
     */
    public static void printLine(String line) {
        System.out.println(line);
    }

    /**
     * Prints out error.
     *
     * @param err to be printed.
     */
    public void showError(String err) {
        System.out.println(err);
    }

    /**
     * Print error when storage file is empty.
     */
    public void showLoadingError() {
        System.out.println("You have not stored any tasks!");
    }
}
