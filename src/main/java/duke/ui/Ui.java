package duke.ui;

import java.util.Scanner;

/**
 * Manages everything related to the UI.
 */
public class Ui {
    private Scanner scanner;
    public static String frontSpace = "    ";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * print out the welcome message once open the duke.
     *
     * @return The string of the welcome message.
     */

    static String getWelcomeMessage() {
        return "    Hello! I'm Duke.\n"
                + "    What can I do for you?";
    }

    /**
     * print out the input.
     */
    public static void printOutput(Object output) {
        assert output != null : "should have output message to be printed out";

        System.out.println(frontSpace + output);
    }
    
    /**
     * showLoadingError message print out.
     */
    public void showLoadingError() {
        Ui.printOutput(" duke.txt file has problem!");
    }
}