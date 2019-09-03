package duke.util;

import duke.task.Task;

import java.util.Scanner;

/**
 * Deals with the interactions with the user, including the input and output to and from the command line.
 */
public class Ui {
    /** Scanner to read in input from the command line. */
    private Scanner sc;
    /** Horizontal line to be printed before and after each output message. */
    private static final String horizontalLine =
            "    ____________________________________________________________";

    /**
     * Constructor for Ui that instantiates the Scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Takes in the next line of input from the user in the command line.
     *
     * @return The line of input entered by the user.
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Prints out the DukeException's formatted error message.
     *
     * @param e The DukeException to be displayed to the user.
     */
    public void printErrorMsg(DukeException e) {
        System.out.println(horizontalLine);
        System.out.println("     :( OOPS!!! " + e.getMessage());
        System.out.println(horizontalLine);
        System.out.println();
    }

    public String getErrorMsg(DukeException e) {
        return ":( OOPS!!! " + e.getMessage();
    }

    /**
     * Prints out the greeting to the user at the start of the Duke program.
     */
    public void printGreetingMsg() {
        // Greet
        System.out.println(horizontalLine);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(horizontalLine);
        System.out.println();
    }

    public String getGreetingMsg() {
        return "Hello I'm Duke!\nWhat can I do for you?";
    }

    /**
     * Prints out the goodbye message when the user leaves the Duke program.
     */
    public void printExitMsg() {
        // Exit
        System.out.println(horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    public String getExitMsg() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out each Task in the TaskList.
     * Each Task is prepended with their ID, which is its index + 1.
     *
     * @param tasks List of tasks being tracked by Duke.
     */
    public void printTasksListing(TaskList tasks) {
        System.out.println(horizontalLine);
        System.out.println("     Here are the tasks in your list:");

        int id = 1;
        for (Task task : tasks) {
            System.out.println("     " + id + ". " + task);
            id++;
        }

        System.out.println(horizontalLine);
        System.out.println();
    }

    public String getTasksListing(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

        int id = 1;
        for (Task task : tasks) {
            sb.append(id + ". " + task + "\n");
            id++;
        }

        return sb.toString();
    }

    /**
     * Prints out that the task was marked as done.
     *
     * @param task Task which was marked as done.
     */
    public void printMarkTaskAsDoneMsg(Task task) {
        System.out.println(horizontalLine);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println(horizontalLine);
        System.out.println();
    }

    public String getMarkTaskAsDoneMsg(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Prints out that the task was deleted.
     *
     * @param task Task which was deleted.
     */
    public void printDeleteTaskMsg(Task task) {
        System.out.println(horizontalLine);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println(horizontalLine);
        System.out.println();
    }

    public String getDeleteTaskMsg(Task task) {
        return "Noted. I've removed this task:\n  " + task;
    }

    /**
     * Prints out that the task was added.
     *
     * @param task Task which was added.
     * @param numOfTasks Number of tasks in the TaskList after task was added.
     */
    public void printAddTaskMsg(Task task, int numOfTasks) {
        System.out.println(horizontalLine);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
        System.out.println(horizontalLine);
        System.out.println();
    }

    public String getAddTaskMsg(Task task, int numOfTasks) {
        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + numOfTasks + " tasks in the list.";
    }

    /**
     * Prints out searched tasks.
     *
     * @param tasksWithKeyword List of tasks with description containing the user search keyword.
     */
    public void printFoundTasksMsg(TaskList tasksWithKeyword) {
        System.out.println(horizontalLine);
        if (tasksWithKeyword.size() > 0) {
            System.out.println("     Here are the matching tasks in your list:");

            int id = 1;
            for (Task task : tasksWithKeyword) {
                System.out.println("     " + id + ". " + task);
                id++;
            }

        } else {
            System.out.println("     Sorry, there are no matching tasks in your list!");
        }
        System.out.println(horizontalLine);
        System.out.println();
    }

    public String getFoundTasksMsg(TaskList tasksWithKeyword) {
        if (tasksWithKeyword.size() > 0) {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");

            int id = 1;
            for (Task task : tasksWithKeyword) {
                sb.append(id + "." + task);
                id++;
            }

            return sb.toString();
        } else {
            return "Sorry, there are no matching tasks in your list!";
        }
    }
}
