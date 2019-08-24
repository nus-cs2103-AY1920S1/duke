package duke.ui;

import duke.shared.Messages;

import java.util.Scanner;

/**
 * Displays message to the user via terminal.
 */
public class Ui {
    public static final String START_HORIZONTAL_LINE = "   _______________________________________________________"
            + "_____";
    public static final String END_HORIZONTAL_LINE = "   _________________________________________________________"
            + "___\n";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays error messages to user.
     * @param errorMessages error message that Ui will displayed to user
     */
    public void showError(String... errorMessages) {
        printLines(errorMessages);
    }

    /**
     * Displays info messages to user.
     * @param messages info message that Ui will displayed to user
     */
    public void showMessage(String... messages) {
        printLines(messages);
    }

    /**
     * Reads user command entered by the user.
     * @return command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Print welcome message to user.
     */
    public void showWelcome() {
        printLines(Messages.GREETING_MESSAGE);
    }

    /**
     * Prints messages to the terminal.
     * @param messagesLines messages to be printed to the console
     */
    public void printLines(String... messagesLines) {
        System.out.println(START_HORIZONTAL_LINE);
        for (String line : messagesLines) {
            System.out.println(line);
        }
        System.out.println(END_HORIZONTAL_LINE);
    }
}
