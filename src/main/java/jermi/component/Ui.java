package jermi.component;

import java.util.Scanner;

/**
 * A class that deals with interaction with the user.
 */
public class Ui {
    /** Keyboard input reader */
    private Scanner scanner;

    /**
     * Public constructor for class.
     * Initialises {@link Scanner};
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Formats a message.
     *
     * @param message Message.
     * @return Formatted message.
     */
    private String formatMessage(String message) {
        return "     " + message + "\n";
    }

    /**
     * Reads input command.
     *
     * @return Command read.
     */
    public String readCommand() {
        return this.scanner.next();
    }

    /**
     * Reads input details.
     *
     * @return Details read.
     */
    public String readDetails() {
        return this.scanner.nextLine().trim();
    }

    /**
     * Prints formatted messages.
     *
     * @param messages Message.
     */
    public void echo(String... messages) {
        String border = "    ____________________________________________________________\n";
        StringBuilder toEcho = new StringBuilder(border);
        for (String message : messages) {
            toEcho.append(formatMessage(message));
        }
        toEcho.append(border);
        System.out.println(toEcho);
    }

    /**
     * Prints welcome message.
     */
    public void greet() {
        echo("Hello! I'm Jermi", "What can I do for you?");
    }

    /**
     * Prints exit message.
     */
    public void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    /**
     * Prints run failure message.
     */
    public void runFail() {
        echo("Client has failed to run.", "Please resolve the issue above before running again.");
    }
}