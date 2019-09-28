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
            "____________________________________________________________";

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
        System.out.println(getErrorMsg(e));
        System.out.println(horizontalLine);
        System.out.println();
    }

    /**
     * Returns the DukeException's formatted error message.
     *
     * @param e The DukeException where the error message is from.
     * @return String containing the formatted error message of the DukeException.
     */
    public String getErrorMsg(DukeException e) {
        return ":( OOPS!!! " + e.getMessage();
    }

    /**
     * Prints out the greeting to the user at the start of the Duke program.
     */
    public void printGreetingMsg() {
        // Greet
        System.out.println(horizontalLine);
        System.out.println(getGreetingMsg());
        System.out.println(horizontalLine);
        System.out.println();
    }

    /**
     * Returns the greeting message shown at the start of the Duke program.
     *
     * @return String containing the greeting message.
     */
    public String getGreetingMsg() {
        return "Hello I'm Duke!\nWhat can I do for you?";
    }

    /**
     * Prints out the exit message when the user ends the Duke program.
     */
    public void printExitMsg() {
        // Exit
        System.out.println(horizontalLine);
        System.out.println(getExitMsg());
        System.out.println(horizontalLine);
    }

    /**
     * Returns the exit message shown when the user ends the Duke program.
     *
     * @return String containing the exit message.
     */
    public String getExitMsg() {
        return "Your tasks have been saved.\n"
                + "Bye! The app will close in 3 seconds.\n"
                + "Hope to see you again soon!";
    }

    /**
     * Prints out each Task in the TaskList.
     * Each Task is prepended with their ID, which is its index + 1.
     *
     * @param tasks List of tasks being tracked by Duke.
     */
    public void printTasksListing(TaskList tasks) {
        System.out.println(horizontalLine);
        System.out.println(getTasksListing(tasks));
        System.out.println(horizontalLine);
        System.out.println();
    }

    /**
     * Returns a String containing each Task separated by a new line.
     * Each Task is prepended with their ID, which is its index + 1.
     *
     * @param tasks List of tasks being tracked by Duke.
     * @return String containing all the tasks in the TaskList.
     */
    public String getTasksListing(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

        int id = 1;
        for (Task task : tasks) {
            sb.append(id + ". " + task + "\n");
            id++;
        }

        return sb.toString().trim();
    }

    /**
     * Prints out that the task was marked as done.
     *
     * @param task Task which was marked as done.
     */
    public void printMarkTaskAsDoneMsg(Task task) {
        System.out.println(horizontalLine);
        System.out.println(getMarkTaskAsDoneMsg(task));
        System.out.println(horizontalLine);
        System.out.println();
    }

    /**
     * Returns a String indicating the task was marked as done.
     *
     * @param task Task which was marked as done.
     * @return String indicating the task was marked as done.
     */
    public String getMarkTaskAsDoneMsg(Task task) {
        assert task != null : "Task should not be null.";

        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Prints out that the task was deleted.
     *
     * @param task Task which was deleted.
     */
    public void printDeleteTaskMsg(Task task) {
        System.out.println(horizontalLine);
        System.out.println(getDeleteTaskMsg(task));
        System.out.println(horizontalLine);
        System.out.println();
    }

    /**
     * Returns a String indicating the task was deleted.
     *
     * @param task Task which was deleted.
     * @return String indicating the task was deleted.
     */
    public String getDeleteTaskMsg(Task task) {
        assert task != null : "Task should not be null.";

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
        System.out.println(getAddTaskMsg(task, numOfTasks));
        System.out.println(horizontalLine);
        System.out.println();
    }

    /**
     * Returns a String indicating the task was added.
     *
     * @param task Task which was added.
     * @param numOfTasks Number of tasks in the TaskList after task was added.
     * @return String indicating the task was added and the number of tasks in the list.
     */
    public String getAddTaskMsg(Task task, int numOfTasks) {
        assert task != null : "Task should not be null.";
        assert numOfTasks > 0 : "There should be at least 1 task in the TaskList after a task is added.";

        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + numOfTasks + " tasks in the list.";
    }

    /**
     * Prints out searched tasks.
     *
     * @param tasksWithKeyword List of tasks with description containing the user's searched keyword.
     */
    public void printFoundTasksMsg(TaskList tasksWithKeyword) {
        System.out.println(horizontalLine);
        System.out.println(getFoundTasksMsg(tasksWithKeyword));
        System.out.println(horizontalLine);
        System.out.println();
    }

    /**
     * Returns a String containing each found Task separated by a new line.
     *
     * @param tasksWithKeyword List of tasks with description containing the user's searched keyword.
     * @return String containing all the found tasks in the TaskList.
     */
    public String getFoundTasksMsg(TaskList tasksWithKeyword) {
        if (tasksWithKeyword.size() > 0) {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");

            int id = 1;
            for (Task task : tasksWithKeyword) {
                sb.append(id + "." + task + "\n");
                id++;
            }

            return sb.toString().trim();
        } else {
            return "Sorry, there are no matching tasks in your list!";
        }
    }
}
