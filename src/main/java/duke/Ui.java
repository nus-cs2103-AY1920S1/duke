package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private MainWindow window;

    /**
     * Constructs a command line interface that handles display of prompts and receiving user input.
     *
     * @deprecated Use {@link #Ui(MainWindow)} instead.
     */
    @Deprecated
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Constructs a graphic interface that handles display of prompts and receiving user input.
     */
    public Ui(MainWindow window) {
        this.window = window;
    }

    /**
     * Prints a message to the console.
     *
     * @deprecated Use {@link #printGuiMessage(String)} instead.
     * @param message the message to be printed.
     */
    @Deprecated
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
     * Prints a message to the GUI.
     *
     * @param message the message to be printed.
     */
    public void printGuiMessage(String message) {
        window.handleDukeResponse(message);
    }

    /**
     * Prints an error to the console.
     *
     * @param e the error to be printed.
     */
    public void printError(Exception e) {
        printGuiMessage("☹ OOPS!!! " + e.getMessage());
    }

    /**
     * Reads a command from user input in the console.
     *
     * @deprecated Because of the addition of the graphic interface.
     * @return the raw command string read from user input.
     */
    @Deprecated
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        printGuiMessage("Hello! I'm Duke\nWhat can I do for you?");
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
