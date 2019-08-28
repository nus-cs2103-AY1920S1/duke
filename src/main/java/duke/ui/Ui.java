package duke.ui;

import java.lang.StringBuilder;
import java.util.Scanner;

/**
 * Implements the text-based user interface.
 * @author Lim Yong Shen, Kevin
 */
public class Ui {

    private Scanner inputScanner;
    private static final String LOADING_ERROR_MESSAGE = "The data file could not be loaded."
            + "Creating a new task list.\n";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String WELCOME_MESSAGE = "Hello from\n"
            + LOGO + "What can I do for you?\n";
    private static final int HORIZONTAL_BORDER_LENGTH = 80;

    /**
     * Constructs a Ui.
     */
    public Ui() {
        inputScanner = new Scanner(System.in);
    }

    /**
     * Closes the Ui.
     */
    public void close() {
        inputScanner.close();
    }

    /**
     * Returns the next line of input from the user.
     * @return The next line of input from the user.
     */
    public String readInput() {
        return inputScanner.nextLine();
    }

    /**
     * Shows an empty line.
     */
    public void showEmptyLine() {
        System.out.println();
    }

    /**
     * Shows a horizontal border.
     */
    public void showHorizontalBorder() {
        StringBuilder border = new StringBuilder();
        for (int i = 0; i < HORIZONTAL_BORDER_LENGTH; i++) {
            border.append('_');
        }
        System.out.println(border);
    }

    /**
     * Shows the loading error message.
     */
    public void showLoadingError() {
        showHorizontalBorder();
        System.out.print(LOADING_ERROR_MESSAGE);
        showHorizontalBorder();
        showEmptyLine();
    }

    /**
     * Shows the specified message.
     * @param message The specified message.
     */
    public void showMessage(String message) {
        System.out.print(message);
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        showHorizontalBorder();
        System.out.print(WELCOME_MESSAGE);
        showHorizontalBorder();
        showEmptyLine();
    }

}
