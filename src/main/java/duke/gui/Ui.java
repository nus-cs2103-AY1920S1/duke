package duke.gui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

import java.util.ArrayList;
import java.util.HashMap;

import static duke.task.TaskType.ALL;
import static duke.task.TaskType.DEADLINE;
import static duke.task.TaskType.DEADLINE_OVERDUE;
import static duke.task.TaskType.EVENT;
import static duke.task.TaskType.TODO;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    public static final String PREFIX = "    ";
    public static final String DIVIDER = "******************************************";
    private static final String WELCOME_MESSAGE_1 = "Hey partner! I'm the";
    private static final String WELCOME_MESSAGE_2 =
            "It's good to see you again! Here are your tasks. How can I help you?";
    private static final String WELCOME_MESSAGE_3 =
            "It's good to see you again! It seems you have no tasks yet. How can I help you?";
    private static final String ADD_MESSAGE = "No problem. I've added the task!";
    private static final String SEARCH_MESSAGE = "Here's the matching tasks from your list!";
    private static final String LIST_MESSAGE = "Glad to help partner! Here are your tasks: ";
    private static final String LIST_EMPTY_MESSAGE = "It seems we're done for the day! Amazing!";
    private static final String LIST_FILLED_MESSAGE = "Let's get to work!";
    private static final String REMIND_MESSAGE = "Here are your upcoming tasks. Don't forget them!";
    private static final String REMIND_OVERDUE_MESSAGE = "Oh no, you have some overdue tasks!";
    private static final String REMIND_ONTIME_MESSAGE = "It seems you're right on track. Keep it up!";

    private static final String DELETE_MESSAGE_1 = "All right. That's one task down: ";
    private static final String DELETE_MESSAGE_2 = "Feels good to check that off, doesn't it?";
    private static final String DONE_MESSAGE = "Great! I've marked this task as done:";
    private static final String EXIT_MESSAGE = "Aww... Come back soon partner!";
    private static final String LOGO =
            "  _  _   _   _  _ ___  _    ___ ___ \n"
            + " | || | /_\\ | \\| |   \\| |  | __| _ \\\n"
            + " | __ |/ _ \\| .` | |) | |__| _||   /\n"
            + " |_||_/_/ \\_\\_|\\_|___/|____|___|_|_\\\n"
            + "                                    ";

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
     * Greets the user and shows his current tasks.
     *
     * @param taskList the list of tasks to complete.
     * @return the formatted text string.
     */
    String showWelcomeMessage(TaskList taskList) {
        return showToUser(WELCOME_MESSAGE_1,
                LOGO,
                taskList.isEmpty() ? WELCOME_MESSAGE_3 : WELCOME_MESSAGE_2,
                "",
                showTaskList(taskList));
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
                "",
                task.toString(),
                "",
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
        return showToUser(DONE_MESSAGE, "", task.toString());
    }

    /**
     * Prints out the message indicating a task has been deleted.
     *
     * @param task the deleted task.
     * @param taskCount the number of tasks remaining in the task list.
     * @return the formatted text string.
     */
    public String showDeleteMessage(Task task, long taskCount) {
        return showToUser(DELETE_MESSAGE_1,
                task.toString(),
                "",
                (taskCount == 1
                        ? "Now you have 1 task left. "
                        : String.format("Now you have %d tasks left. ", taskCount)) + DELETE_MESSAGE_2);
    }

    public String showListMessage(TaskList taskList) {
        String postfix = taskList.isEmpty() ? LIST_EMPTY_MESSAGE : LIST_FILLED_MESSAGE;
        return showToUser(LIST_MESSAGE, "", showTaskList(taskList), postfix);
    }

    /**
     * Prints out the list of results from a keyword search.
     *
     * @param searchList the task list of results.
     * @return the formatted text string.
     */
    public String showSearchList(TaskList searchList) {
        return showToUser(SEARCH_MESSAGE, "", showTaskList(searchList));
    }

    /**
     * Prints out the sorted task list of the specified task type. Checks for overdue deadlines.
     *
     * @param taskDict the dictionary mapping task type to its task list.
     * @param type the task type to sort by.
     * @return the formatted text string.
     */
    public String showRemindList(HashMap<TaskType, TaskList> taskDict, TaskType type) {
        if (type == TODO) {
            return showToUser(REMIND_MESSAGE, "", showTaskList(taskDict.get(TODO)));
        }
        if (type == EVENT) {
            return showToUser(REMIND_MESSAGE, "", showTaskList(taskDict.get(EVENT)));
        }
        if (type == DEADLINE) {
            boolean hasOverdue = !taskDict.get(DEADLINE_OVERDUE).isEmpty();
            return showToUser(REMIND_MESSAGE, "")
                    + (!taskDict.get(DEADLINE).isEmpty() ? showTaskList(taskDict.get(DEADLINE)) : "")
                    + showToUser("", hasOverdue ? REMIND_OVERDUE_MESSAGE : REMIND_ONTIME_MESSAGE, "")
                    + (hasOverdue ? showTaskList(taskDict.get(DEADLINE_OVERDUE)) : "");
        }
        // ALL - deadlines, then events, then todos
        boolean hasOverdue = !taskDict.get(DEADLINE_OVERDUE).isEmpty();
        return showToUser(REMIND_MESSAGE, "", DEADLINE.toString())
                + (!taskDict.get(DEADLINE).isEmpty() ? showTaskList(taskDict.get(DEADLINE)) : "")
                + showToUser("", hasOverdue ? REMIND_OVERDUE_MESSAGE : REMIND_ONTIME_MESSAGE,
                    hasOverdue ? showTaskList(taskDict.get(DEADLINE_OVERDUE)) : "",
                    EVENT.toString(), showTaskList(taskDict.get(EVENT)),
                    TODO.toString(), showTaskList(taskDict.get(TODO)));
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
