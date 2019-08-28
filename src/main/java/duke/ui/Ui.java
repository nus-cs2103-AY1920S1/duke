package duke.ui;

import duke.todo.Task;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Displays error when loading file.
     */
    public void showLoadingError() {
        System.out.println("Unable to load file");
    }

    /**
     * Waits for user input and
     * returns the next line of input.
     *
     * @return Input from the user.
     */
    public String takeCommand() {
        return sc.nextLine();
    }

    /**
     * Prints input text with formatting.
     *
     * @param text Input text from other methods.
     */
    public static void printFormattedText(String text) {
        printDivider();
        System.out.println(text);
        printDivider();
    }

    private static void printDivider() {
        indent();
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    /**
     * Prints greeting message.
     */
    public static void greet() {
        printFormattedText("    Hello! I'm Duke\n" +
                "    What can I do for you?");
    }

    /**
     * Prints formatted report on the task done.
     *
     * @param task Task done.
     */
    public static void reportDone(Task task) {
        printFormattedText("    Nice! I've marked this task as done:\n" +
                "        " + task.getStatusIcon() +
                task.getDescription());
    }

    /**
     * Prints formatted report on the task removed.
     *
     * @param task Task removed.
     * @param numOfTasks Number of tasks left in the task list.
     */
    public static void reportRemove(Task task, int numOfTasks) {
        printFormattedText("    Noted. I've removed this task:\n" +
                "       " + task +
                "\n    Now you have " + numOfTasks +
                " task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    /**
     * Prints formatted report on the task added.
     *
     * @param task Task added.
     * @param numOfTasks Number of tasks left in the task list.e
     */
    public static void reportAdd(Task task, int numOfTasks) {
        printFormattedText("    Got it. I've added this task:\n" +
                "      " + task +
                "\n    Now you have " + numOfTasks +
                " task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    public static void reportFound(String tasksFound) {
        printFormattedText(tasksFound);
    }

    /**
     * Prints goodbye message.
     */
    public static void bye() {
        printFormattedText("    Bye. Hope to see you again soon!");
    }

    /**
     * Prints formatted error message.
     *
     * @param e Error message.
     */
    public static void printError(String e) {
        printFormattedText(e);
    }

    private static void indent() {
        for (int i = 0; i < 4; i++) System.out.print(" ");
    }
}
