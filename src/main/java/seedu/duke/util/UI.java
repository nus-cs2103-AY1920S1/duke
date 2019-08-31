package seedu.duke.util;

import seedu.duke.tasks.Task;

import java.util.Scanner;

public class UI {
    private Scanner sc;
    private static final String BORDER = "____________________________________________________________";
    private static final String UPPER_BORDER = BORDER + "\n\n";
    private static final String LOWER_BORDER = "\n" + BORDER + "\n";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String WELCOME_MESSAGE = "Hello from\n" + logo + "\n"
            + UPPER_BORDER + "Hello! I'm Duke\n" + "What can I do for you?" + LOWER_BORDER;
    private static final String ERROR_CANNOT_LOAD = UPPER_BORDER
            + "☹ OOPS!!! I cannot read your file! :(" + LOWER_BORDER;
    private static final String BYE = UPPER_BORDER
            + "Bye. Hope to see you again soon!" + LOWER_BORDER;
    private static final String TASK_WRAPPER_UPPER = UPPER_BORDER + "Got it. I've added this task:\n";
    private static final String DONE = "Nice! I've marked this task as done:\n";
    private static final String TASK_WRAPPER_UPPER_DELETE = UPPER_BORDER + "Got it. I've removed this task:\n";
    private static final String NOW_YOU_HAVE = "Now you have ";
    private static final String TASK_WRAPPER_LOWER = " tasks in the list.\n" + LOWER_BORDER;

    /**
     * Prints out a welcome message.
     */
    public void greet() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Prints out an error if file cannot be loaded.
     */
    public void cannotLoad() {
        System.out.println(ERROR_CANNOT_LOAD);
    }

    /**
     * Displays the TaskList in order of addition.
     *
     * @param taskList TaskList to print out.
     */
    public void showTaskList(TaskList taskList) {
        System.out.println(UPPER_BORDER + taskList + LOWER_BORDER);
    }

    /**
     * Lets the user know that a task has been marked done.
     *
     * @param task Task to be marked done.
     */
    public void showDone(Task task) {
        System.out.println(UPPER_BORDER + DONE + task + "\n" + LOWER_BORDER);
    }

    /**
     * Displays the Task that was operated on, other than done.
     *
     * @param task Task that was operated on
     * @param taskList TaskList of the Task that was operated on.
     * @param isAdd Type of operation that was done.
     */
    public void operateTask(Task task, TaskList taskList, boolean isAdd) {
        if (isAdd) {
            System.out.println(TASK_WRAPPER_UPPER + task + "\n" + NOW_YOU_HAVE + taskList.getTaskListSize()
                    + TASK_WRAPPER_LOWER);
        } else {
            System.out.println(TASK_WRAPPER_UPPER_DELETE + task + "\n" + NOW_YOU_HAVE + (taskList.getTaskListSize() - 1)
                    + TASK_WRAPPER_LOWER);
        }
    }

    /**
     * Show error to the user.
     *
     * @param e Error to be shown.
     */
    public void showError(String e) {
        System.out.println(UPPER_BORDER + e + LOWER_BORDER);
    }

    /**
     * Prints bye message.
     */
    public void bye() {
        System.out.println(BYE);
    }
}
