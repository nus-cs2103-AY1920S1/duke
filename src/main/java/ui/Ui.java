package ui;

import javafx.MainWindow;

/**
 * User interface to communicate with user.
 * Responsible for every output printing.
 */
public class Ui {

    private static MainWindow javafx;

    /**
     * Bind this Ui to javafx MainWindow for output printing.
     * @param window a javafx scene.
     */
    public static void bindWindow(MainWindow window) {
        javafx = window;
    }

    /**
     * Show messages on the javafx window.
     * @param msg can be warnings / information to show to user.
     */
    private void showMessages(String msg) {
        javafx.showMessage(msg);
    }

    /**
     * Shows greeting message at start of the program.
     */
    public void showWelcome() {
        showMessages("Hello from! I'm Duke\n"
                + "What can I do for you?");
    }

    /**
     * Shows storage creating error.
     */
    public void showStorageError() {
        showMessages("File corrupted / File path doesnt exist.\n"
                + "You can still use Duke, but progress will not be saved.");
    }

    /**
     * Show error about unable to load data stored in user's hard disk.
     */
    public void showTaskLoadingError() {
        showMessages("Note: TaskList storage is initially empty / the file is corrupted\n"
                + "New empty file will be created.");
    }

}