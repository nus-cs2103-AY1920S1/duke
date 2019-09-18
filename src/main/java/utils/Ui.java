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
    private static final String SAD_EMOTICON = "\u2639"; // "☹"
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String HELP_MESSAGE =
            "List of commands:\n\n"
                    + "bye:\nExits from the program\n\n"

                    + "list:\nList all existing tasks\n\n"

                    + "help:\nPrints the list of commands\n\n"

                    + "done [n]:\nMarks the n-th task on the list as done\n\n"

                    + "delete [n]:\nDeletes the n-th task on the list\n\n"

                    + "todo [taskName] :\n"
                    + "Adds a new Todo task with the given \"taskName\".\n\n"

                    + "event [taskName] /at [DD/MM/YYYY HHmm] :\n"
                    + "Adds a new Event task with the deadline in the given format.\n\n"

                    + "deadline [taskName] /by [DD/MM/YYYY HHmm] :\n"
                    + "Adds a new Deadline task with the deadline in the given format.\n\n"

                    + "undo:\n"
                    + "Undoes the most recent action.\n\n"

                    + "find [keyword] :\n"
                    + "Returns a list of task with names containing the \"keyword\".\n\n"

                    + "sort [category] r:\n"
                    + "Sorts and returns the list of tasks. Category can be one of \"name\", "
                    + "\"deadline\", \"type\", \"status\"."
                    + "\nOptional argument \"r\" sorts list in reverse order.\n\n";


    private StringBuilder outputBuilder;


    /**
     * Initializes StringBuilder on construction.
     */
    public Ui() {
        outputBuilder = new StringBuilder();
    }

    /**
     * Prints a hello message when program first initializes.
     */
    public void printWelcomeMessage() {
        outputBuilder.append("Hello! I'm Duke\n");
        outputBuilder.append("What can I do for you?\n");
        outputBuilder.append("Enter \"help\" for a list of commands.\n");
    }

    /**
     * Prints a list of actions that can be used.
     */
    public void printHelpMessage() {
        outputBuilder.append(HELP_MESSAGE);
    }

    /**
     * Prints a message upon exit of the program.
     */
    public void printByeMessage() {
        outputBuilder.append("Bye. Hope to see you again soon!\n");
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
     * @param title    The preamble to be printed before listing the tasks.
     */
    public void printTaskList(List<Task> taskList, String title) {
        assert taskList != null : "Task list not found and cannot be printed.";
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
        assert task != null : "Done task not found and cannot be printed.";
        outputBuilder.append("Nice! I've marked this task as done:\n");
        outputBuilder.append(String.format("%s\n", task));
    }

    /**
     * Prints a message that the given task is marked as not done.
     *
     * @param task The task that is marked as not done.
     */
    public void printMarkedAsNotDoneMessage(Task task) {
        assert task != null : "Task not found and cannot be printed.";
        outputBuilder.append("I've marked this task as not done:\n");
        outputBuilder.append(String.format("%s\n", task));
    }

    /**
     * Prints a message to acknowledging the undo-ing of previous action.
     */
    public void printUndoMessage() {
        outputBuilder.append("Undo-ing previous action.\n");
    }

    /**
     * Prints a message to explain why undo is not allowed.
     */
    public void printUndoNotAllowedMessage() {
        outputBuilder.append("Undo not allowed as there is no earlier action.\n");
    }

    /**
     * Prints a message that the given task has been deleted and then
     * prints the total number of tasks remaining.
     *
     * @param task         The task that is deleted.
     * @param taskListSize The total number of tasks remaining in the task list
     */
    public void printTaskDeletedMessage(Task task, int taskListSize) {
        assert task != null : "Deleted task not found and cannot be printed.";
        outputBuilder.append("Noted. I've removed this task:\n");
        outputBuilder.append(String.format("%s\n", task));
        printListSummary(taskListSize);
    }

    private void printListSummary(int taskListSize) {
        outputBuilder.append(String.format("Now you have %d tasks in the list.\n", taskListSize));
    }

    /**
     * Prints a message that the given task has been added and then
     * prints the total number of tasks currently.
     *
     * @param task         The task that has been added.
     * @param taskListSize The total number of tasks currently in the task list.
     */
    public void printTaskAddedMessage(Task task, int taskListSize) {
        assert task != null : "Added task not found and cannot be printed.";
        outputBuilder.append("Got it. I've added this task:\n");
        outputBuilder.append(String.format("%s\n", task));
        printListSummary(taskListSize);
    }

    public void printListSortedMessage() {
        outputBuilder.append("Your list has been sorted.\n");
    }

    public String buildIncorrectArgumentsMessage() {
        return String.format("%s OOPS!!! Incorrect number of arguments. Use the \"help\" command for guide.\n",
                Ui.SAD_EMOTICON);
    }

    public String buildEmptyDescriptionMessage() {
        return String.format("%s OOPS!!! Description cannot be empty. Use the \"help\" command for guide.\n",
                Ui.SAD_EMOTICON);
    }

    public String buildIncorrectDateFormatMessage() {
        return String.format("%s OOPS!!! Date must be valid and be in the format \"%s\"\n",
                Ui.SAD_EMOTICON,
                Parser.DATE_FORMATTER_PATTERN);
    }

    public String buildInvalidCommandMessage() {
        return String.format("%s OOPS!!! I'm sorry, but I don't know what that means :-(\n",
                SAD_EMOTICON);
    }

    public String buildEmptyTaskListMessage() {
        return String.format("%s OOPS!!! You have no task at the moment.\n",
                Ui.SAD_EMOTICON);
    }

    public String buildInvalidTaskListIndexMessage(int size) {
        return String.format("%s OOPS!!! Task index number must be a number from %d to %d.\n",
                Ui.SAD_EMOTICON,
                1,
                size);
    }

    public String buildInvalidSortCategoryMessage() {
        return String.format("%s OOPS!!! Category must be one of the following: \"%s\", \"%s\", \"%s\", \"%s\"."
                + "\nEnter \"help\" for illustration.\n",
                Ui.SAD_EMOTICON,
                "name",
                "deadline",
                "type",
                "status");
    }




    public void appendMessage(String message) {
        outputBuilder.append(message);
    }

    public void resetOutputBuilder() {
        outputBuilder = new StringBuilder();
    }

    public String getOutputAndClearBuilder() {
        String output = outputBuilder.toString();
        resetOutputBuilder();
        return output;
    }
}
