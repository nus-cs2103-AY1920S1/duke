package run;

import task.Task;

import java.util.ArrayList;

/**
 * Handles all the interaction with the user (taking input and printing output).
 */
public class Ui {

    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String NO_TASKS = "No tasks!";
    public static final String NO_TASK_AT_DONE_PARAMETER = "No task at that number! (Marking as done unsuccessful)";
    public static final String NO_TASK_AT_DELETE_PARAMETER = "No task at that number! (Deletion unsuccessful)";
    public static final String NO_TASK_AT_RESCHEDULE_PARAMETER = "No task at that number! (Reschedule unsuccessful)";
    public static final String NO_MATCHING_TASK_FOUND = "No tasks found containing your search!";
    public static final String WRONG_RESCHEDULE_TASK_TYPE = "Task is not an event or deadline! Nothing to reschedule";
    public static final String PRINT_LIST_PREAMBLE = "Here are the tasks in your list:";
    public static final String PRINT_ADD_PREAMBLE = "Got it. I've added this task: ";
    public static final String PRINT_DONE_PREAMBLE = "Nice! I've marked this task as done: ";
    public static final String PRINT_DELETE_PREAMBLE = "Noted. I've removed this task: ";
    public static final String PRINT_FIND_PREAMBLE = "Here are the matching tasks in your list:";
    public static final String PRINT_RESCHEDULE_PREAMBLE = "Done! Here's the rescheduled task: ";
    public static final String DATE_TIME_FORMAT_EXCEPTION_MESSAGE = "DateTime or Number Format exception!";

    /**
     * Prints a message to the user. Only used before MainWindow launches.
     * @param message the error message to be printed to the user
     */
    public static void showMessage(String message) {
        System.out.println("---------------------------------------------------------------------");
        System.out.println(message);
        System.out.println("---------------------------------------------------------------------");
    }

    /**
     * Prints an error message to the user. Only used before MainWindow launches
     * @param errorMessage the message to be printed
     */
    public static void showErrorMessage(String errorMessage) {
        System.out.println("---------------------------------------------------------------------");
        System.out.println(errorMessage);
        System.out.println("---------------------------------------------------------------------");
    }

    /**
     * Prints exit message (Used when Duke is exited).
     */
    public String exit() {
        return EXIT_MESSAGE;
    }


    /**
     * Prints a list of tasks.
     * @param tasks arraylist of tasks to be printed
     * @return String formatted print of all tasks
     */
    public static String printList(ArrayList<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PRINT_LIST_PREAMBLE);
        stringBuilder.append("\n");
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i - 1);
            stringBuilder.append(i + ". " + currTask);
            if (i != tasks.size()) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Prints messages for when task is added to TaskList.
     * @param task the task that was added
     * @param size current number of tasks in TaskList
     * @return String message to be displayed to user after adding task
     */
    public static String printAdd(Task task, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PRINT_ADD_PREAMBLE);
        stringBuilder.append(task);
        stringBuilder.append("\n");
        stringBuilder.append("Now you have " + size + " tasks in the list.");
        return stringBuilder.toString();
    }

    /**
     * Prints message for when a task is marked as done.
     * @param task the task to be marked as done
     * @return String message to be displayed to user after marking task as done
     */
    public static String printDone(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PRINT_DONE_PREAMBLE);
        stringBuilder.append(task);
        return stringBuilder.toString();
    }

    /**
     * Prints messages for when a task is deleted from TaskList.
     * @param task the task to be deleted
     * @param size new total number of tasks in TaskList
     * @return String message to be displayed to user after deleting task
     */
    public static String printDelete(Task task, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PRINT_DELETE_PREAMBLE);
        stringBuilder.append(task);
        stringBuilder.append("\n");
        stringBuilder.append("Now you have " + size + " tasks in the list.");
        return stringBuilder.toString();
    }

    /**
     * Prints tasks that were found and special message if not tasks were found to match search string.
     * @param tasks arraylist of found tasks that contained previous search string
     * @return String representation of tasks that were found to meet criteria, otherwise "No tasks found
     *     containing your search" if none found
     */
    public static String printFind(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return NO_MATCHING_TASK_FOUND;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PRINT_FIND_PREAMBLE);
        stringBuilder.append("\n");
        for (int i = 1; i <= tasks.size(); i++) {
            Task currTask = tasks.get(i - 1);
            stringBuilder.append(i + ". " + currTask);
            if (i != tasks.size()) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Prints updated (rescheduled) task.
     * @param currTask updated task to be printed
     * @return String of PRINT_RESCHEDULE_PREAMBLE and updated task
     */
    public static String printReschedule(Task currTask) {
        return PRINT_RESCHEDULE_PREAMBLE + currTask.toString();
    }
}