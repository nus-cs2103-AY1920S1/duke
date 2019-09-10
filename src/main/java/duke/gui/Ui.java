package duke.gui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public static final String PREFIX = "    ";
    public static final String DIVIDER = "******************************************";
    private static final String WELCOME_MESSAGE_1 = "Hello! I'm";
    private static final String WELCOME_MESSAGE_2 = "What can I do for you?";
    private static final String LOGO =
            "            ____   _   _ __     ____   __\n"
            + "       / \\      /   __ \\ | \\  | |\\ \\     / /\\ \\   / /\n"
            + "     /     \\  \t ||   |||   \\| |  \\ \\_/ /   \\ V  / \n"
            + "    /  / \\ \\  ||   |||   . `   |    \\   /       > <  \n"
            + "  /   ____ \t ||__|||  |\\   |    | |     /  .   \\ \n"
            + " /_ /      \\_\\\\___ / |_|  \\_|   |_|   /_ / \\ _\\\n";
    private static final String ADD_MESSAGE = "Got it. I've added this task:";
    private static final String SEARCH_MESSAGE = "Here are the matching tasks in your list:";
    private static final String REMIND_MESSAGE = "Here are your upcoming tasks. Don't forget them!";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Convenience method for formatting un-indented input.
     *
     * @param input the text to format.
     * @return the formatted text string.
     */
    static String addNewLine(String input) {
        return input + '\n';
    }

    /**
     * Convenience method for adding dividers above and below the text.
     *
     * @param input the text to format.
     * @return the formatted text string.
     */
    public static String addDividers(String input) {
        return addNewLine(DIVIDER) + input + addNewLine(DIVIDER);
    }

    /**
     * Prints out a welcome message.
     *
     * @return the formatted text string.
     */
    String showWelcomeMessage() {
        return showToUser(WELCOME_MESSAGE_1, LOGO, WELCOME_MESSAGE_2);
    }

    /**
     * Prints out a specified error message.
     *
     * @param e the error to print.
     * @return the formatted text string.
     */
    public String showError(DukeException e) {
        return showToUser(e.getMessage());
    }

    /**
     * Prints out the message indicating a task has been added.
     *
     * @param task the task added.
     * @param taskCount the number of tasks currently in the task list.
     * @return the formatted text string.
     */
    public String showAddMessage(Task task, long taskCount) {
        return showToUser(ADD_MESSAGE,
                task.toString(),
                (taskCount == 1
                    ? "Now you have 1 task in the list."
                    : String.format("Now you have %d tasks in the list.", taskCount)));
    }

    /**
     * Prints out the message indicating a task has been marked done.
     *
     * @param task the task marked done.
     * @return the formatted text string.
     */
    public String showDoneMessage(Task task) {
        return showToUser(DONE_MESSAGE, task.toString());
    }

    /**
     * Prints out the message indicating a task has been deleted.
     *
     * @param task the deleted task.
     * @param taskCount the number of tasks remaining in the task list.
     * @return the formatted text string.
     */
    public String showDeleteMessage(Task task, long taskCount) {
        return showToUser(DELETE_MESSAGE,
                task.toString(),
                (taskCount == 1
                    ? "Now you have 1 task in the list."
                    : String.format("Now you have %d tasks in the list.", taskCount)));
    }

    /**
     * Prints out the list of results from a keyword search.
     *
     * @param searchList the task list of results.
     * @return the formatted text string.
     */
    public String showSearchList(TaskList searchList) {
        return showToUser(SEARCH_MESSAGE, showTaskList(searchList));
    }

    /**
     * Prints out a sorted list of tasks, filtered by the specified task.
     *
     * @param remindList the list to filter and sort.
     * @param type the task type to show reminders for.
     * @return the formatted text string.
     */
    public String showRemindList(TaskList remindList, TaskType type) {
        return showToUser(REMIND_MESSAGE,
                "",
                "TASK TYPE: " + type.toString(),
                "",
                showTaskList(remindList));
    }

    /**
     * Prints out a task list.
     *
     * @param taskList the task list to print.
     * @return the formatted text string.
     */
    public String showTaskList(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> tasks = taskList.getTaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task != null) { // account for 0-indexing
                sb.append(showToUser(String.format("%d. %s", i, task)));
            }
        }
        return sb.toString();
    }

    /**
     * Prints the exit message.
     *
     * @return the formatted text string.
     */
    public String showExitMessage() {
        return showToUser(EXIT_MESSAGE);
    }

    /**
     * Prints out a sequence of messages with dividers at beginning and end, and prefix added to each line.
     *
     * @param messages the sequence of messages to print.
     * @return the formatted text string.
     */
    private String showToUser(String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String line: messages) {
            sb.append(line).append('\n');
        }
        return sb.toString();
    }
}
