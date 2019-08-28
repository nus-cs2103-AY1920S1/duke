/**
 * Represents the user interface of the application.
 */
public class Ui {

    /**
     * Prints message to indicate error loading the target file.
     */
    public void showLoadingError() {
        System.out.println("Loading Error");
    }

    /**
     * Prints welcome message when application starts up.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints exit message and exits the application.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * Prints error message.
     * @param errorMessage input error message from any exception
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
