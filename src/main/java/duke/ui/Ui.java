package duke.ui;

import duke.shared.Messages;

import java.util.Scanner;

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
     * @param errorMessages error message
     */
    public void showError(String... errorMessages) {
        printLines(errorMessages);
    }

    /**
     * Displays info messages to user.
     * @param messages info message
     */
    public void showMessage(String... messages) {
        printLines(messages);
    }

    /**
     * Reads user command entered by the user.
     * @return commands entered by the user
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Formats the welcome message and print it.
     */
    public void showWelcome() {
        printLines(Messages.GREETING_MESSAGE);
    }

    /**
     * Prints message to the console.
     * @param messagesLines message to be printed to the console
     */
    public void printLines(String... messagesLines) {
        System.out.println(START_HORIZONTAL_LINE);
        for (String line : messagesLines) {
            System.out.println(line);
        }
        System.out.println(END_HORIZONTAL_LINE);
    }
}
