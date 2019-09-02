package seedu.duke.util;

import seedu.duke.tasks.Task;

import java.util.Scanner;

public class UI {
    private Scanner sc;
//    private static final String BORDER = "________________________________________________________";
//    private static final String UPPER_BORDER = BORDER + "\n\n";
//    private static final String LOWER_BORDER = BORDER + "\n";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String WELCOME_MESSAGE = "Hello from\n" + logo + "\n"
            +  "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String ERROR_CANNOT_LOAD = "OOPS!!! I cannot read your file! :(";
    private static final String BYE = "Bye. Hope to see you again soon!";
    private static final String TASK_WRAPPER_UPPER = "Got it. I've added this task:\n";
    private static final String DONE = "Nice! I've marked this task as done:\n";
    private static final String TASK_WRAPPER_UPPER_DELETE = "Got it. I've removed this task:\n";
    private static final String NOW_YOU_HAVE = "Now you have ";
    private static final String TASK_WRAPPER_LOWER = " tasks in the list.\n";
    private static final String FOUND = "Here are the matching tasks in your list:\n";

    /**
     * Prints out a welcome message.
     */
    public String greet() {
        return WELCOME_MESSAGE;
    }

    /**
     * Prints out an error if file cannot be loaded.
     */
    public String cannotLoad() {
        return ERROR_CANNOT_LOAD;
    }

    /**
     * Displays the TaskList in order of addition.
     *
     * @param taskList TaskList to print out.
     */
    public String showTaskList(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Lets the user know that a task has been marked done.
     *
     * @param task Task to be marked done.
     */
    public String showDone(Task task) {
        return DONE + task + "\n";
    }

    /**
     * Displays the Task that was operated on, other than done.
     *
     * @param task Task that was operated on
     * @param taskList TaskList of the Task that was operated on.
     * @param isAdd Type of operation that was done.
     */
    public String operateTask(Task task, TaskList taskList, boolean isAdd) {
        if (isAdd) {
            return TASK_WRAPPER_UPPER + task + "\n" + NOW_YOU_HAVE + taskList.getTaskListSize()
                    + TASK_WRAPPER_LOWER;
        } else {
            return TASK_WRAPPER_UPPER_DELETE + task + "\n" + NOW_YOU_HAVE + (taskList.getTaskListSize() - 1)
                    + TASK_WRAPPER_LOWER;
        }
    }

    /**
     * Displays the found items to the user.
     *
     * @param foundItems The result of finding the taskList.
     */
    public String showFound(TaskList foundItems) {
        return FOUND + foundItems;
    }

    /**
     * Show error to the user.
     *
     * @param e Error to be shown.
     */
    public String showError(String e) {
        return e;
    }

    /**
     * Prints bye message.
     */
    public String bye() {
        return BYE;
    }
}
