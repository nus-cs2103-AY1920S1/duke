package duke.ui;

import java.util.Scanner;

/**
 * Represents an UI interface which handles user's input and output.
 */
public class UI {
    Scanner sc;

    /**
     * Initialize the object with a scanner to handle user input.
     * @param sc Scanner object.
     */
    public UI(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Print welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
    }

    /**
     * Read the user's input.
     * @return
     */
    public String readCommand() {

        String result = sc.nextLine();
        return result;
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
