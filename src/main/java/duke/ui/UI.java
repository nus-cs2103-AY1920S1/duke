package duke.ui;

import java.util.Scanner;

/**
 * Represents an UI interface which handles user's input and output.
 */
public class UI {

    /**
     * Initialize the object with a scanner to handle user input.
     */
    public UI() {

    }

    /**
     * Print welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints the msg passed in.
     * @param message A string message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints a divider line.
     */
    public void showLine() {
        System.out.println("-------------------------------------------------------------------");
    }
}
