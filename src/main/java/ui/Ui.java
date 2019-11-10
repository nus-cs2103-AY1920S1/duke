package ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class for the Ui (User Interaction) object.
 * Handles all user input and program output to the user.
 */
public class Ui {

    private Scanner sc;

    /**
     * Constructor for Ui.
     * Initializes the Scanner object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the successful completion message after the completion of a command.
     *
     * @param msg completion message to be printed
     * @return string representation of the command response
     */
    public String showResultOfCommand(String msg) {
        return msg;
    }

    /**
     * Returns the error message whenever a error is found.
     *
     * @param msg error message to be printed.
     * @return string representation of the error message
     */
    public String showError(String msg) {
        return "     ☹ OOPS!!! " + msg;
    }

    /**
     * Returns the loading error whenever the text file in which Duke reads and writes from cannot
     * be opened.
     *
     * @return string representation of loading error
     */
    public String showLoadingError() {
        return "     ☹ OOPS!!! The file cannot be opened";
    }
}
