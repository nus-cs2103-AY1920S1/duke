package duke.command;

/**
 * Ui class. Performs output (display) actions with the user.
 */
public class Ui {

    /** Divider. */
    private static String divider = "\t____________________________________________________________\n";
    /** Intro message. */
    private static String intro = "\t Hello! I'm Duke\n\t What can I do for you?\n";
    /** Goodbye message. */
    private static String goodbye = "\t Bye. Hope to see you again soon!\n";

    /**
     * Prints the intro.
     */
    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printToUser(intro);
    }

    /**
     * Prints the goodbye mesage.
     */
    public void printGoodbye() {
        printToUser(goodbye);
    }

    /**
     * Prints a message to the user.
     * @param s The string to be printed.
     */
    public void printToUser(String s) {
        System.out.print(divider);
        System.out.print(s);
        System.out.print(divider);
    }

    /**
     * Prints an error message to the user.
     * @param e The exception to be printed.
     */
    public void printErrToUser(Exception e) {
        System.err.print(divider);
        System.err.print(e);
        System.err.print(divider);
    }

}
