package oop;

import exceptions.DukeException;

import java.util.Scanner;

/**
 * The Ui class handles the display of information to the user.
 */
public class Ui {
    /**
     * A Scanner used for reading user inputs.
     */
    private Scanner scanner;

    /**
     * Constructs and initializes the attributes of a new Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * A method to display welcome message at the start of the application.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        return ("Hello from\n" + logo);
    }

    /**
     * A method to display text to the user.
     * @param text The text to be displayed.
     */
    public String showText(String text) {
        System.out.println(text);
        return text;
    }

    /**
     * A method to display the error message to the user.
     * @param error The Error to be displayed.
     */
    public String showError(DukeException error) {
        System.out.println(error.getMessage());
        return error.getMessage();
    }

    /**
     * A method to display the error message upon reading data from text file at the
     * start of the application.
     * @param error The Error to be displayed.
     */
    public String showLoadingError(DukeException error) {
        System.out.println(error.getMessage());
        return error.getMessage();
    }

    /**
     * A method to display the exit message to be shown when the application terminates.
     */
    public String showExitMessage() {
        String exitMessage = "Bye! Hope to see you again soon.";
        scanner.close();
        return exitMessage;
    }

    /**
     * A method to read in user input for commands.
     * @return Returns a response as a result of the command executed by the application.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}

