package duke;

import duke.task.Task;

/**
 * Encapsulates a user interface.
 */
public class Ui {
    /**
     * Outputs tasks in task list.
     *
     * @param taskList   Task list to be output.
     */
    public static String showTaskList(TaskList taskList) {
        if (taskList.size() == 0) {
            return ("Your list is empty.\n");
        }
        assert (taskList.size() > 0);
        return ("Here are the tasks in your list:\n" + taskList.list());
    }

    /**
     * Outputs message indicating that action of marking a task as done was a success.
     *
     * @param task   Task marked as done.
     */
    public static String showDoneMessage(Task task) {
        return ("Nice! I've marked this task as done:\n" + task + "\n");
    }

    /**
     * Outputs message indicating that action of deleting a task was a success.
     *
     * @param size  Size of task list.
     * @param task   Task deleted.
     */
    public static String showDeleteMessage(int size, Task task) {
        if (size == 0 || size > 1) {
            return ("Noted. I've removed this task: \n" + task + "\n" + "Now you have "
                    + size + " tasks in the list.\n");
        }
        assert (size == 1);
        return ("Noted. I've removed this task: \n" + task + "\n" + "Now you have "
                + size + " task in the list.\n");
    }

    /**
     * Outputs message indicating that action of adding a task was a success.
     *
     * @param size  Size of task list.
     * @param task   Task added.
     */
    public static String showAddMessage(int size, Task task) {
        if (size == 1) {
            return ("Got it. I've added this task: \n" + task + "\n" + "Now you have "
                    + size + " task in the list.\n");
        }
        assert (size > 1);
        return ("Got it. I've added this task: \n" + task + "\n" + "Now you have "
                + size + " tasks in the list.\n");
    }

    /**
     * Outputs welcome message.
     */
    public static String showWelcomeMessage() {
        return ("Hello from Bear.\nWhat can I do for you?\n");
    }

    /**
     * Outputs goodbye message.
     */
    public static String showByeMessage() {
        return ("Bye. Hope to see you again soon!\n");
    }

    /**
     * Outputs message indicating that action of searching for a keyword was a success, and results of that search.
     *
     * @param taskList  Task list to be searched.
     * @param keyword   Keyword to be searched for.
     */
    public static String showFindMessage(TaskList taskList, String keyword) {
        return ("Here are the matching tasks in your list:\n" + taskList.find(keyword));
    }

    /**
     * Outputs message describing an error.
     *
     * @param error  Description of error to be output.
     */
    public static String showError(String error) {
        return error;
    }

    /**
     * Outputs message indicating that action of loading saved data file was a success.
     *
     * @param filePath  Path to saved data file on hard disk.
     */
    public static String showLoadMessage(String filePath) {
        return ("Your saved data has been successfully loaded from " + filePath + ".\n");
    }
}
