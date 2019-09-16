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
        return "Hello! I'm duke.Duke\nWhat can I do for you?";
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
    public String showLoadingError(String msg) {
        return msg;
    }

    public String showLoadingSuccess() {
        return "Data was loaded from file.";
    }

    /**
     * Displays the error message.
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
