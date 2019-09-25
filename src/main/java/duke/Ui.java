package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Ui class deals with interactions with the user.
 *
 * @author scwaterbear
 */
public class Ui {

    /**
     * Class constructor.
     */
    Ui() {
    }

    /**
     * Returns Duke welcome message.
     *
     * @return String Duke welcome message.
     */
    String showWelcome() {
        return "Hello! I'm Duke. What can I do for you?";
    }

    /**
     * Returns trimmed user input.
     *
     * @return String trimmed user input.
     */
    String readCommand(String command) {
        return command.trim();
    }

    /**
     * Returns Duke load file error message.
     *
     * @return String Duke load file error message.
     */
    String showLoadingError() {
        return "Error loading data from file. Creating tasklist from scratch.";
    }

    /**
     * Returns Duke error message.
     *
     * @return String Duke error message.
     */
    String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns Duke farewell message.
     *
     * @return String Duke farewell message.
     */
    public String showFarewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns Duke successfully added task.
     *
     * @return String Duke successfully added task.
     */
    public String showAddTask(Task task, int size) {
        String s = "Got it. I've added this task:\n";
        s = appendTask(s, task);
        return appendListSize(s, size);
    }

    /**
     * Returns Duke successfully deleted task.
     *
     * @return String Duke successfully deleted task.
     */
    public String showDeleteTask(Task task, int size) {
        String s = "Noted. I've removed this task:\n";
        s = appendTask(s, task);
        return appendListSize(s, size);
    }

    private String appendTask(String s, Task task) {
        return s.concat(task.toString() + "\n");
    }

    private String appendListSize(String s, int size) {
        if (size == 1) {
            return s.concat("Now you have 1 task in the list.");
        } else {
            return s.concat("Now you have " + size + " tasks in the list.");
        }
    }

    /**
     * Returns Duke successfully set task as done.
     *
     * @return String Duke successfully set task as done.
     */
    public String showDoneTask(Task task) {
        String s = "Nice! I've marked this task as done:\n";
        return appendTask(s, task);
    }

    /**
     * Returns error message stating the task index does not exist.
     *
     * @return String error message stating the task index does not exist.
     */
    public String showIndexError(int index) {
        return " ☹ OOPS!!! There is no task number " + (index + 1) + "\n";
    }

    /**
     * Returns error message that Duke was unable to save to file.
     *
     * @return String error message that Duke was unable to save to file.
     */
    public String showSaveError() {
        return "Unable to write to datafile.";
    }

    /**
     * Prints the task list it is supplied with.
     *
     * @param list task list to print.
     */
    public String showList(List<Task> list) {
        //guard clause
        if (list.size() == 0) {
            return "No tasks found\n";
        }

        //happy path
        String s = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            s = s.concat((i + 1) + "." + list.get(i).toString() + "\n");
        }
        return s;
    }
}
