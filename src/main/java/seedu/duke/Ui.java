package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the user interface.
 */
public class Ui {
    /** Scanner for parsing user input.*/
    protected static Scanner sc;
    /** String of a line for formatting.*/
    private static String line = "____________________________________________________________";

    /** Constructor.*/
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the intro when starting the application.
     */
    public static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(format(logo + "\n Hello! I'm Duke\n What can I do for you?"));
    }

    /**
     * Handles the formatting when required.
     * @param s String of content to be printed.
     * @return formatted String.
     */
    public static String format(String s) {
        return line + "\n " + s + "\n" + line;
    }

    /**
     * Prints the line for formatting.
     */
    public static void showLine() {
        System.out.println(line);
    }

    /**
     * Prints error message.
     * @param str String containing error message.
     */
    public static void showError(String str) {
        System.out.println(format(str));
    }

    /**
     * Reads user input.
     * @return String containing user input.
     */
    public static String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints message if there is a problem with loading.
     */
    public static void showLoadingError() {
        System.out.println(format("There seems to be a problem with loading"));
    }

    /**
     * Prints message when marking a task as done.
     * @param str String representing Task done.
     */
    public static void doneLine(String str) {
        System.out.println("Nice! I've marked this task as done:\n " + str);
    }

    /**
     * Prints message when deleting a task.
     * @param str String representing Task deleted.
     * @param size integer representing the current size of the TaskList.
     */
    public static void deleteLine(String str, int size) {
        System.out.println("Noted. I've removed this task: \n   " + str + "\n Now you have " +
        size + " tasks in the list.");
    }

    /**
     * Prints message when adding a task.
     * @param str String representing Task added.
     * @param size integer representing the current size of the TaskList.
     */
    public static void addLine(String str, int size) {
        System.out.println("Got it. I've added this task:\n " + str +
                "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Prints exit message.
     */
    public static void exitLine() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the list of all Tasks in the TaskList.
     * @param tl Current TaskList.
     */
    public static void list(TaskList tl) {
        if (tl.list.size() != 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= tl.list.size(); i++) {
                System.out.println(" " + i + ". " + tl.list.get(i - 1).toString());
            }
        } else {
            System.out.println("You have no tasks in your list.");
        }
    }

    public static void listMatching(ArrayList<Task> tl) {
        if (tl.size() != 0) {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 1; i <= tl.size(); i++) {
                System.out.println(" " + i + ". " + tl.get(i - 1).toString());
            }
        } else {
            System.out.println("You have no matching tasks in your list.");
        }
    }
}