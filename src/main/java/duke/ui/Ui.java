package duke.ui;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    private static final String DIVIDER = "\t____________________________________________________________";
    private static final String MESSAGE_GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_BYE      = "Bye. Hope to see you again soon!";

    public String nextCommand() {
        return sc.nextLine();
    }

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints output in a standardised format.
     * @param output String to be printed by Duke.
     */
    public static void printIndented(String output) {
        StringBuilder str = new StringBuilder();
        String[] lines = output.split("\n");
        for (String line : lines) {
            str.append(String.format("\t %s\n", line));
        }
        System.out.print(str);
    }

    /**
     * Prints the greeting message for starting Duke.
     */
    public static void sayGreeting() {
        printDivider();
        printIndented(MESSAGE_GREETING);
        printDivider();
    }

    /**
     * Prints the bye message for exiting Duke.
     */
    public static void sayBye() {
        printIndented(MESSAGE_BYE);
    }
}
