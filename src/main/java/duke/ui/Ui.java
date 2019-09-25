package duke.ui;

/**
 * Represents the user interface for the Duke application.
 * Controls how outputs are shown to the user.
 */
public class Ui {

    /**
     * Initialises the UI class.
     */
    public Ui() {
    }

    /**
     * Shows welcome message.
     */
    public static String showWelcome() {
        return "Hello Mocha! \n What can I do for you?";
    }

    /**
     * Shows an error to the user when no previously saved file is found.
     */
    public static String showLoadingError() {
        return "File not found. Creating new list... \n";
    }

    public static String showLoaded() {
        return "File found. Your data has been loaded.";
    }

    /**
     * Prints farewell message to the user.
     */
    public static String sendBye() {
        return "Bye. Hope to see you again soon!";
    }

}
