package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    /**
     * Constructs an object that handles display of prompts and receiving user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a message to the console.
     *
     * @param message the message to be printed.
     */
    public void printMessage(String message) {
        String[] messages = message.split("\n");
        System.out.println("    ____________________________________________________________");
        for (String line : messages) {
            System.out.println("     " + line);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    /**
     * Prints an error to the console.
     *
     * @param e the error to be printed.
     */
    public void printError(Exception e) {
        printMessage("☹ OOPS!!! " + e.getMessage());
    }

    /**
     * Reads a command from user input.
     *
     * @return the raw command string read from user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        printMessage("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Converts the item specified in its plural form.
     *
     * @param item the item to be converted.
     * @param quantity the number of the item.
     * @return the plural form of the item with quantifier.
     */
    public static String pluralize(String item, Integer quantity) {
        if (quantity == 1) {
            return "1 " + item;
        } else {
            return quantity + " " + item + "s";
        }
    }
}
