package seedu.duke.ui;

import seedu.duke.exceptions.DukeException;

/**
 * The Ui Class is static as there is exactly one Ui in Duke, and more should not be instantiated.
 * A Singleton implementation would have been excessive for a utility class.
 */
public class Ui {

    private static final String HORIZONTAL_LINE = "______________________________"
        + "______________________________";

    private static final String LOGO = "\t ____        _        \n"
        + "\t|  _ \\ _   _| | _____ \n"
        + "\t| | | | | | | |/ / _ \\\n"
        + "\t| |_| | |_| |   <  __/\n"
        + "\t|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints the greeting message.
     */
    public static void greet() {
        System.out.println(LOGO);
        printMessages("\t" + "Hello! I'm Duke", "\tWhat can I do for you?");
    }

    public static void printMessages(String... messages) {
        printLine();
        for (String message : messages) {
            System.out.println("\t" + message);
        }
        printLine();
    }

    public static void printError(DukeException e) {
        printMessages(e.getMessage());
    }

    /**
     * Print horizontal line.
     */
    private static void printLine() {
        System.out.println("\t" + HORIZONTAL_LINE);
    }

}
