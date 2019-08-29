package seedu.duke;

import java.util.Scanner;

public class Duke {

    static final String HORIZONTAL_LINE = "______________________________"
            + "______________________________";


    /**
     * Main Method.
     * @param args string arguments for console.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();

        Scanner in = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            String command  = in.nextLine();

            if (!command.equals("bye")) {
                echo(command);
            } else {
                exit();
                continueRunning = false;
            }
        }
    }

    /**
     * Prints the greeting message.
     */
    private static void greet() {
        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\t" + "Hello! I'm Duke\n\t"
                + "What can I do for you?");
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    /**
     * Echos the input command back to console with formatting.
     * @param command the command entered by user.
     */
    private static void echo(String command) {
        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\t" + command);
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    /**
     * Prints the exit message.
     */
    private static void exit() {
        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println("\t" + HORIZONTAL_LINE);
    }
}
