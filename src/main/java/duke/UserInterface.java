package duke;

import java.util.Scanner;

/**
 * Interacts with the user with beautified interface.
 */
public class UserInterface {

    /**
     * Constructs the UserInterface object.
     */
    UserInterface() {

    }

    /**
     * Displays the welcome message for the user and prompts their inputs.
     */
    void showWelcome() {
        showLine();
        System.out.println("\tHello! I'm duke.Duke\n\tWhat can I do for you?");
        showLine();
    }

    /**
     * Beautifies the interface with line dividers.
     */
    void showLine() {
        System.out.println("\t____________________________________________________________");
    }


    /**
     * Reads the command from the user.
     * @return Command string.
     */
    String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    /**
     * Displays error in loading of data.
     */
    void showLoadingError() {
        System.out.println("No data was loaded into the task list.");
    }

    /**
     * Displays the error message.
     * @param message Error message.
     */
    void showError(String message) {
        System.out.println(message);
    }
}
