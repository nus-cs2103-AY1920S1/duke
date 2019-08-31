package ui;

/**
 * Interacts with the user with beautified interface.
 */
public class UserInterface {
    /**
     * Constructs the UserInterface object.
     */
    public UserInterface() {}

    /**
     * Displays the welcome message for the user and prompts their inputs.
     */
    public String welcome() {
        return "\tHello! I'm duke.Duke\n\tWhat can I do for you?";
    }

    /**
     * Displays the goodbye message for the user.
     */
    public String goodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays error in loading of data.
     */
    public void showLoadingError() {
        System.out.println("No data was loaded into the task list.");
    }

    /**
     * Displays the error message.
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
