import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Ui is a class that designed to output the how the program
 * responds to the user inputs.
 */
public class Ui {
    public void showWelcome() {
        printLine();

        printIndent();
        System.out.println("Hello! I'm Duke");
        printIndent();
        System.out.println("What can I do for you?");

        printLine();
    }

    public void showGoodbye() {
        String exitMessage = "Bye. Hope to see you again soon";

        printLine();

        printIndent();
        System.out.println(exitMessage);

        printLine();
    }

    public static void printIndent() {
        System.out.print("    ");
    }

    public static void printLine() {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < 60; i++) {
            line.append("_");
        }

        String stringLine = line.toString();
        printIndent();
        System.out.println(stringLine);
    }

    public void printTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            printLine();

            printIndent();
            System.out.println("There are no tasks in the list currently.");

            printLine();
        } else {
            LinkedList<Task> taskLinkedList = taskList.getList();
            ListIterator<Task> iter = taskLinkedList.listIterator();

            int count = 1;

            printLine();

            printIndent();
            System.out.println("Here are the tasks in your list:");

            while (iter.hasNext()) {
                String currentTask = iter.next().toString();
                printIndent();
                System.out.println(count + "." + currentTask);
                count++;
            }

            printLine();
        }
    }

    public void showTaskDone(Task task) {
        printLine();

        printIndent();
        System.out.println("Nice! I've marked this task as done:");
        printIndent();
        System.out.println("  " + task.toString());

        printLine();
    }

    public void showTaskAlreadyDone(Task task) {
        printLine();

        printIndent();
        System.out.println("This task has already been done.");
        printIndent();
        System.out.println("  " + task.toString());

        printLine();
    }

    public void showTaskAdded(Task task, TaskList taskList) {
        printLine();

        printIndent();
        System.out.println("Got it. I've added this task:");
        printIndent();
        System.out.println("  " + task.toString());
        printIndent();

        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }

        printLine();
    }

    public void showTaskDeleted(Task task, TaskList taskList) {
        printLine();

        printIndent();
        System.out.println("Noted. I've removed this task:");
        printIndent();
        System.out.println("  " + task.toString());

        printIndent();

        if (taskList.size() == 2) {
            System.out.println("Now you have 1 task in your list.");
        } else if (taskList.size() == 1) {
            System.out.println("Now you have no tasks in your list.");
        } else {
            System.out.println("Now you have " + (taskList.size() - 1) + " tasks in your list.");
        }

        printLine();
    }

    public void showFoundTasks(LinkedList<Task> taskList) {
        ListIterator<Task> iter = taskList.listIterator();
        Task current;
        int count = 1;

        printLine();

        if (taskList.isEmpty()) {
            printIndent();
            System.out.println("There are no matching tasks in your list.");
        } else {
            printIndent();
            System.out.println("Here are the matching tasks in your list:");
        }

        while(iter.hasNext()) {
            current = iter.next();
            printIndent();
            System.out.println(count + "." + current.toString());
        }

        printLine();
    }

    public void showException(DukeException e) {
        printLine();

        printIndent();
        System.out.println(e.getMessage());

        printLine();
    }

    public void showLoadingError() {
        printLine();

        printIndent();
        System.out.println("You do not have a saved task list.");

        printLine();
    }
}