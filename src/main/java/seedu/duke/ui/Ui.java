package seedu.duke.ui;

import seedu.duke.exceptions.DukeException;

import java.util.Scanner;

/**
 * Represents the User Interface that the user interacts with. It handles input and output, from and to the user.
 * The Ui Class is static as there is exactly one Ui in Duke, and more should not be instantiated.
 */
public class Ui {

    private static final String HORIZONTAL_LINE = "______________________________"
        + "______________________________";

    private static final String LOGO = "\t ____        _        \n"
        + "\t|  _ \\ _   _| | _____ \n"
        + "\t| | | | | | | |/ / _ \\\n"
        + "\t| |_| | |_| |   <  __/\n"
        + "\t|____/ \\__,_|_|\\_\\___|\n";

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Prints the greeting message.
     */
    public static void greet() {
        System.out.println(LOGO);
        printMessages("Hello! I'm Duke", "What can I do for you?");
    }

    /**
     * Prints out the variable message arguments, each on its own line.
     * @param messages The variable message arguments to print.
     */
    public static void printMessages(String... messages) {
        printLine();
        for (String message : messages) {
            System.out.println("\t" + message);
        }
        printLine();
    }

    /**
     * Prints the message when there is a loading error.
     */
    public static void printLoadingError() {
        printMessages("Creating a new taskList for you...");
    }

    /**
     * Prints the message contained in the DukeException {@code e}
     * @param e the Exception whose message should be printed.
     */
    public static void printError(DukeException e) {
        printMessages(e.getMessage());
    }

    public static String readNextLine() {
        return scanner.nextLine();
    }

    /**
     * Print horizontal line.
     */
    private static void printLine() {
        System.out.println("\t" + HORIZONTAL_LINE);
    }

}
