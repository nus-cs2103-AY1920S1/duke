package duke.ui;

import java.util.Scanner;

/**
 * Manages everything related to the UI.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        print("____________________________________________________________");
    }

    public void showError(String message) {
        print(" " + message);
    }

    /**
     * Shows a welcome message.
     */
    public void showWelcome() {
        showLine();
        print(" Hello! I'm Duke");
        print(" What can I do for you?");
        showLine();
    }

    /**
     * Shows a loading error.
     */
    public void showLoadingError() {
        showLine();
        print(" The save file doesn't seem to be there or is incorrect!");
        print(" Let's start afresh.");
        showLine();
    }

    /**
     * Prints a message.
     */
    public void print() {
        System.out.println();
    }

    /**
     * Prints a message.
     * @param message To be printed.
     */
    public void print(String message) {
        System.out.println(message);
    }
}
