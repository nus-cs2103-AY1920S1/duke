package duke.ui;

import duke.todo.Task;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void showLoadingError() {

    }

    public String takeCommand() {
        return sc.nextLine();
    }

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

    public static void greet() {
        printFormattedText("    Hello! I'm Duke\n" +
                "    What can I do for you?");
    }

    public static void reportDone(Task task) {
        printFormattedText("    Nice! I've marked this task as done:\n" +
                "        " + task.getStatusIcon() +
                task.getDescription());
    }

    public static void reportRemove(Task task, int numOfTasks) {
        printFormattedText("    Noted. I've removed this task:\n" +
                "       " + task +
                "\n    Now you have " + numOfTasks +
                " task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    public static void reportAdd(Task task, int numOfTasks) {
        printFormattedText("    Got it. I've added this task:\n" +
                "      " + task +
                "\n    Now you have " + numOfTasks +
                " task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    public static void reportFound(String tasksFound) {
        printFormattedText(tasksFound);
    }

    public static void bye() {
        printFormattedText("    Bye. Hope to see you again soon!");
    }

    public static void printError(String e) {
        printFormattedText(e);
    }

    private static void indent() {
        for (int i = 0; i < 4; i++) System.out.print(" ");
    }
}
