package duke.core;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a system that deals with user interaction, for example, printing 
 * welcome messages and prompts.
 */
public class Ui {

    /**
     * Shows a welcome message, and prompts user to type instructions. GUI version.
     *
     * @return A string that represents the welcome message.
     */
    public String showWelcome() {
        return "Hello from TSINGHUA\n"
                    + "\nHello! I'm Tsinghua : )\nWhat can I do for you?";
    }

    /**
     * Shows success in loading past tasks. GUI version.
     *
     * @return A string that indicates success in loading past tasks.
     */
    public String showLoadingSuccess() {
        return "Past tasks loaded successfully :-)";
    }

    /**
     * Shows that a <code>Task</code> has been added, and displays the number
     * of current tasks in the list. GUI version.
     *
     * @param t The <code>Task</code> that is added to the list.
     * @param size The number of tasks stored in the <code>TaskList</code>.
     * @return A string that represents successful addition of the task, and
     *      the number of current tasks in the list.
     */
    public String addedTask(Task t, int size) {
        return "Got it. I've added this task: \n  " + t + "\nNow you have "
                + size + " task(s) in the list.";
    }

    /**
     * Shows that a <code>Task</code> has been marked as done. GUI version.
     *
     * @param t The <code>Task</code> that is marked as done.
     * @return A string that indicates the task has been successfully marked as done.
     */
    public String markedAsDone(Task t) {
        return "Nice! I've marked this task as done: \n  " + t;
    }

    /**
     * Shows that a <code>Task</code> has been removed, and displays the number
     * of current tasks in the list. GUI version.
     *
     * @param t The <code>Task</code> that is deleted from the list.
     * @return A string that represents the successful removal of the task, and
     *          the number of current tasks in the list.
     */
    public String removedTask(Task t, int size) {
        return "Noted. I've removed this task: \n  " + t + "\nNow you have "
                + size + " task(s) in the list.";
    }

    /**
     * Shows that a <code>Task</code> has been updated, and display the number of
     * current tasks in the list. GUI version.
     *
     * @param t The <code>Task</code> that is updated.
     * @param size The number of tasks stored in the <code>TaskList</code>.
     * @return A string that represents the task has been successfully updated, and
     *          the number of current tasks in the list.
     */
    public String updatedTask(Task t, int size) {
        return "Ok! I've updated this task: \n  " + t + "\nNow you have "
                + size + " task(s) in the list.";
    }

    /**
     * Displays all tasks currently stored in the list. GUI version.
     *
     * @param tasks The <code>TaskList</code> used to store tasks.
     * @return A string that represents all existing tasks.
     */
    public String showAllTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> list = tasks.getList();
        list.forEach(t -> sb.append(list.indexOf(t) + 1 + "." + t + "\n"));
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
        }
        return "Here are the tasks in your list:\n" + sb.toString();
    }

    /**
     * Shows content of an error. GUI version.
     *
     * @param e An exception that occurred.
     * @return A string that represents the error message.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }

    /**
     * Shows search results for finding a keyword in the task list. Displays
     * all tasks that contain the keyword.
     *
     * @param tasks The <code>TaskList</code> where keyword is searched.
     * @param keyword A string representation of the keyword.
     * @return A string that represents all the matching results.
     */
    public String showSearchResults(TaskList tasks, String keyword) {
        StringBuilder sb = new StringBuilder();
        List<Task> results = tasks.getList().stream()
                .filter(t -> t.toString().contains(keyword))
                .collect(Collectors.toList());
        results.forEach(t -> sb.append(results.indexOf(t) + 1 + "." + t + "\n"));
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
        }
        return "Here are the matching tasks in your list:\n" + sb.toString();
    }

    /**
     * Shows bye message to user. GUI version.
     *
     * @return A string that represents the bye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }
}




