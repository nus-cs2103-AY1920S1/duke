package duke.util;

import duke.task.Task;

import java.util.Scanner;

/**
 * This class handles the interactions with users. Two main features are (1) reading users' inputs from the console; (2)
 * showing users the results responding to their commands. Many strings are pre-defined in certain formats. Pre-defined
 * string messages are <code>showWelcome</code>,
 * <code>showGoodbye</code>, <code>showSavingError</code>, <code>showLoadingError</code>, <code>showNoTask</code>. The
 * rest needs to take in some parameter(s) to show according responses.
 */
public class Ui {
    private static final String WELCOME = "Hello! I'm your Duke\nWhat can I do for you?";
    private static final String ENDING = "Bye. Hope to see you again soon!";
    private static final String SAVING_ERROR = "☹ OOPS!!! We cannot save your data!";
    private static final String LOADING_ERROR = "☹ OOPS!!! We cannot load your data!";
    private static final String NO_TASK = "kkk ~ There is no task in your todo list now!";
    private static final String DUPLICATION = "A same task already exists!!! Do not bother yourself to do it twice=)";

    public String showWelcome() {
        return WELCOME;
    }

    public String showGoodbye() {
        return ENDING;
    }

    public String showSavingError() {
        return SAVING_ERROR;
    }

    public String showLoadingError() {
        return LOADING_ERROR;
    }

    public String showError(String errorMsg) {
        return errorMsg;
    }

    public String showNoTask() {
        return NO_TASK;
    }

    public String showTaskDuplicated() {
        return DUPLICATION;
    }

    /**
     * Informs the user that the task he has marked done.
     *
     * @param doneTask  the <code>Task</code> that the user just marked as done
     * @return          a string that informs the user the marked-done task
     */
    public String showTaskDone(Task doneTask) {
        String msg = "Nice! I've marked this task as done:\n" + "  " + doneTask;
        return msg;
    }

    /**
     * Informs the user that the task he has added and the remaining number of tasks.
     *
     * @param total         the total number of tasks
     * @param newTask       the <code>Task</code> that the user just added
     * @return              a string that informs the user the added task and the total number of tasks
     */
    public String showTaskAdded(int total, Task newTask) {
        String msg = "Got it. I've added this task:\n" + "  " + newTask
                + "\nNow you have " + total + " tasks in the list.";
        return msg;
    }

    /**
     * Informs the user that the task he has deleted and the remaining number of tasks.
     *
     * @param total         the total number of left tasks
     * @param removedTask   the <code>Task</code> that the user just deleted
     * @return              a string that informs the user the deleted task and the total number of left tasks
     */
    public String showTaskDeleted(int total, Task removedTask) {
        String msg = "Noted. I've removed this task: \n" + "  " + removedTask
                + "\nNow you have " + total + " tasks in the list.";
        return msg;
    }

    /**
     * Shows the full list to the user.
     *
     * @param taskList  a <code>TaskList</code> of the user
     * @return          a string representing the full content of the list
     */
    public String showFullList(TaskList taskList) {
        if (taskList.getTotalTask() == 0) {
            return showNoTask();
        } else {
            return "Here are the tasks in your list:\n" + taskList;
        }
    }
}
