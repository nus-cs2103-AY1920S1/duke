package seedu.duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class Duke {

    static final String HORIZONTAL_LINE = "______________________________"
            + "______________________________";

    private static List<String> taskList = new ArrayList<String>();


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

            switch (command) {
                case "list":
                    listAllTasks();
                    break;
                case "bye":
                    exit();
                    continueRunning = false;
                    break;
                default:
                    addToList(command);
                    break;
            }
        }
    }

    /**
     * Print horizontal line
     */
    private static void printLine() {
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    /**
     * Prints the greeting message.
     */
    private static void greet() {
        printLine();
        System.out.println("\t" + "Hello! I'm Duke\n\t"
                + "What can I do for you?");
        printLine();
    }

    /**
     * Echos the input command back to console with formatting.
     * @param command the command entered by user.
     */
    private static void echo(String command) {
        printLine();
        System.out.println("\t" + command);
        printLine();
    }

    /**
     * Prints the exit message.
     */
    private static void exit() {
        printLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Adds the items to list.
     * @param task The task to add to the list.
     */
    private static void addToList(String task) {
        taskList.add(task);
        echo("added: " + task);
    }

    /**
     * Lists all the tasks into console.
     */
    private static void listAllTasks() {
        printLine();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList.get(i));
        }
        printLine();

    }
}
