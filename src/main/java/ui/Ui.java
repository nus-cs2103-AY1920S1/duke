package ui;

import task.Task;
import task.TaskList;

import java.util.Scanner;

/**
 * Determines what is displayed to user.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * Called at the start of program.
     */
    public void showWelcome() {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        showLine();
        System.out.println("    Hello! I'm \n" + logo + "\n    What can I do for you?");
        showLine();
    }

    /**
     * Called when execute method of ExitCommand is called.
     */
    public void showGoodbye() {
        System.out.println("    Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Used for printing border.
     */
    public void showLine() {
        String output = "    ";
        for (int n = 0; n < 80; n++) {
            output += "_";
        }
        System.out.println(output);
    }

    /**
     * reads user input.
     *
     * @return String to be parsed by parser.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Called when a task is entered by user.
     * Called when execute of AddTask is called.
     *
     * @param newTask task entered.
     * @param taskNum  number of tasks in Task List.
     */
    public void readTask(Task newTask, int taskNum) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTask.toString());
        System.out.println("     Now you have " + taskNum + " tasks in the list.");
        showLine();
    }

    /**
     * Displays delete message.
     * Called when execute of DeleteCommand is called.
     *
     * @param removedTask Task being removed.
     * @param taskNum      Number of Task(s) left in the Task List.
     */
    public void readDelete(Task removedTask, int taskNum) {
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + removedTask.toString());
        System.out.println("     Now you have " + taskNum + " tasks in the list.");
        showLine();
    }

    /**
     * Displays message when a user finishes a task.
     * Calls when execute of EditCommand is called.
     *
     * @param completedTask Task which has been marked as done.
     */
    public void readDone(Task completedTask) {
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + completedTask.toString());
        showLine();
    }

    /**
     * Displays contents of Task List.
     * Called when execute of ListCommand is called.
     *
     * @param tasks TaskList
     */
    public void readList(TaskList tasks) {
        System.out.println("     Here are the task(s) in your list: ");
        System.out.println(tasks);
        showLine();
    }


    /**
     * Displays matching Tasks.
     * Called when execute method of FindCommand is called.
     *
     * @param tasks TaskList of tasks with keyword.
     */
    public void showMatches(TaskList tasks) {
        System.out.println("     Here are the matching task(s) in your list: ");
        System.out.println(tasks);
        showLine();
    }


    /**
     * Display message when DukeException is caught.
     *
     * @param errorMsg DukeException e.getMessage()
     */
    public void showError(String errorMsg) {
        System.out.println("     ☹ OOPS!!! " + errorMsg);
        showLine();
    }

    /**
     * Called when Duke Exception is thrown when instantiating Task List and/ or Storage Objects.
     * Called by run method in Duke class.
     */
    public void showLoadingError() {
        System.out.println("     ☹ OOPS!!! Loading Error");
        showLine();
    }
}