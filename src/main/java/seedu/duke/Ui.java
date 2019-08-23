package seedu.duke;

/**
 * The UI class.
 */
public class Ui {
    public static final String logo = "     ____        _        \n"
            + "    |  _ \\ _   _| | _____ \n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";


    private static String BREAKLINE = "    ____________________________________________________________";
    private static String GREETING = "     Hello! I'm Duke\n" + "     What can I do for you?";
    private static String GOODBYE = "     Bye. Hope to see you again soon!";

    /**
     * Prints a greeting to System.out
     */
    public void greeting() {
        System.out.println(logo);
        printBreakLine();
        System.out.println(GREETING);
        printBreakLine();
    }

    /**
     * Prints a goodbye to System.out
     */
    public void goodbye() {
        System.out.println(GOODBYE);
    }

    /**
     * Prints a line break to System.out
     */
    public void printBreakLine() {
        System.out.println(BREAKLINE);
    }

    /**
     * Prints a new line to System.out
     */
    public void printLine() {
        System.out.println();
    }
}
