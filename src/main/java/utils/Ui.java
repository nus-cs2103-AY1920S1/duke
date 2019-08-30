package utils;

import task.Task;

import java.util.List;

/**
 * A Ui class to manage all of Duke's output. It functions with a singleton
 * model so that at any point in time, only one copy of the output String is
 * being built.
 */
public class Ui {
    public static final String LIST_ACTION_TITLE =
            "Here are the tasks in your list:\n";
    public static final String FIND_ACTION_TITLE =
            "Here are the matching tasks in your list:\n";
    private static final String LOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String DIVIDER = "____________________________________________________\n";

    private static StringBuilder outputBuilder;

    /**
     * Generates an instance object of the singleton class Ui to build
     * the outputBuilder.
     *
     * @return An instance of Ui object.
     */
    public static Ui getInstance() {
        return new Ui();
    }

    private Ui() {
        if (outputBuilder == null) {
            outputBuilder = new StringBuilder();
        }
    }

    /**
     * Prints a hello message when program first initializes.
     */
    public void printWelcomeMessage() {
        outputBuilder.append("Hello from\n" + LOGO);
        outputBuilder.append(DIVIDER);
        outputBuilder.append("Hello! I'm Duke\n");
        outputBuilder.append("What can I do for you?\n");
        outputBuilder.append(DIVIDER);
    }

    /**
     * Prints a list of actions that can be used.
     */
    public void printHelpMessage() {
        outputBuilder.append("List of commands:\n");
        outputBuilder.append("bye:\nExits from the program\n");
        outputBuilder.append("list:\nList all existing tasks\n");
        outputBuilder.append("help:\nPrints the list of commands\n");
        outputBuilder.append("done n:\nMarks the n-th task on the list as done\n");
        outputBuilder.append("delete n:\nDeletes the n-th task on the list\n");
        outputBuilder.append("todo taskName :\n"
                + "Adds a new Todo task with the given name.\n");
        outputBuilder.append("event taskName /at DD/MM/YYYY HHmm :\n"
                + "Adds a new Event task with the deadline in the\n"
                + "given format.\n");
        outputBuilder.append("deadline taskName /by DD/MM/YYYY HHmm :\n"
                + "Adds a new Deadline task with the deadline in the\n"
                + "given format.\n");
        outputBuilder.append("search keyword :\n"
                + "Returns a list of task with names containing the\n"
                + "keyword.\n");
    }

    /**
     * Prints a message upon exit of the program.
     */
    public void printByeMessage() {
        outputBuilder.append("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints a divider line for pretty printing.
     */
    public void printDivider() {
        outputBuilder.append(DIVIDER);
    }

    /**
     * Prints a message to indicate that is no existing task.
     */
    public void printEmptyTaskListMessage() {
        outputBuilder.append("You have no task at the moment.\n");
    }

    /**
     * Prints all the tasks in the given task list.
     *
     * @param taskList The list of tasks to be printed.
     * @param title The preamble to be printed before listing the tasks.
     */
    public void printTaskList(List<Task> taskList, String title) {
        outputBuilder.append(title);
        for (int i = 0; i < taskList.size(); i++) {
            outputBuilder.append(String.format("%d.", i + 1));
            outputBuilder.append(String.format("%s\n", taskList.get(i)));
        }
    }

    /**
     * Prints a message that the given task is marked as done.
     *
     * @param task The task that is marked as done.
     */
    public void printMarkedAsDoneMessage(Task task) {
        outputBuilder.append("Nice! I've marked this task as done:\n");
        outputBuilder.append(String.format("%s\n", task));
    }

    /**
     * <p>
     *     Prints a message that the given task has been deleted and then
     *     prints the total number of tasks remaining.
     * </p>
     *
     * @param task The task that is deleted.
     * @param taskListSize The total number of tasks remaining in the task list
     */
    public void printTaskDeletedMessage(Task task, int taskListSize) {
        outputBuilder.append("Noted. I've removed this task:\n");
        outputBuilder.append(String.format("%s\n", task));
        outputBuilder.append(String.format("Now you have %d tasks in the list.\n", taskListSize));
    }

    /**
     * <p>
     *     Prints a message that the given task has been added and then
     *     prints the total number of tasks currently.
     * </p>
     *
     * @param task The task that has been added.
     * @param taskListSize The total number of tasks currently in the task list.
     */
    public void printTaskAddedMessage(Task task, int taskListSize) {
        outputBuilder.append("Got it. I've added this task:\n");
        outputBuilder.append(String.format("%s\n", task));
        outputBuilder.append(String.format("Now you have %d tasks in the list.\n", taskListSize));
    }

    public void addWarningMessage(String message) {
        outputBuilder.append(message);
    }

    public void resetOutputBuilder() {
        outputBuilder = new StringBuilder();
    }

    public String getOutput() {
        return outputBuilder.toString();
    }

}
