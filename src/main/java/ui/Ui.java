package ui;

import java.util.Scanner;

/**
 * UI of the application.
 */
public class Ui {
    Scanner myScanner;

    public Ui() {
        myScanner = new Scanner(System.in);
    }

    public void showLoadingError() {

    }

    public String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Reads user input.
     *
     * @return full user input
     */
    public String readCommand() {
        return myScanner.nextLine();
    }
}
