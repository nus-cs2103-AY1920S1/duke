package ui;

/**
 * User interface to communicate with user.
 * Responsible for every output printing.
 */
public class Ui {

    /**
     * Shows greeting message at start of the program.
     */
    public String showWelcome() {
        return "Hello from! I'm Duke\n"
                + "What can I do for you?";
    }

    /**
     * Prints out the error.
     * @param e the error message.
     * @return the error message as String.
     */
    public String showError(String e) {
        return e;
    }

    /**
     * Prints out error about unable to load data stored in user's hard disk.
     */
    public String showLoadingError() {
        return "Note: task.TaskList storage is initially empty / the file is corrupted\n"
                + "New empty file will be created.";
    }

}