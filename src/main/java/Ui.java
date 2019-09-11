package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;
import duke.task.Task;

/**
 * Deals with interactions with user.
 */
public class Ui {
    public static String liner = "    ____________________________________________________________";

    /**
     * Greets user with welcome dialog.
     *
     * @return String of welcome message
     */
    public String showWelcome() {
        String greeting = "\tHello! I'm Duke\n\tWhat can I do for you?";
        return liner + "\n" + greeting + "\n" + liner;
    }

    /**
     * Says goodbye to user when exit program.
     *
     * @return String of goodbye message
     */
    public String showBye() {
        return liner + "\n\tBye. Hope to see you again soon!\n" + liner;
    }

    /**
     * Generates divider line.
     *
     * @return String of divider line for dialogs
     */
    public String showLine() {
        return liner + "\n";
    }

    /**
     * Generates error message.
     */
    public String showError(String message) {
        return liner + "\n" + message + "\n" + liner;
    }

    /**
     * Generates loading file error.
     */
    public String showLoadingError() {
        return "New task list generated: no existing file found!";
    }

    /**
     * Generates add task message once AddCommand is successful.
     *
     * @param newTask Task to be added in Storage
     * @param totalTasks Total Number of existing Tasks
     * @return String of successful add task message
     */
    public String printAddTask(Task newTask, int totalTasks) {
        return liner + "\n\tGot it. I've added this task:\n\t" + newTask.toString()
                + "\n\t" + "Now you have " + totalTasks + " tasks in the list.\n" + liner;
    }

    /**
     * Generates add syntax message once AddAlias is successful.
     *
     * @param type Type of command for alias
     * @param alias
     * @return String of successful add alias message
     */
    public static String printAddAlias(String type, String alias) {
        return liner + "\n\tGot it. I've overwritten the alias:\n\t" + type + " : " + alias
                + liner;
    }


    /**
     * Generates done task message once successful.
     *
     * @param currTask Most recent Task executed
     * @return String of successful done task message
     */
    public String printDoneTask(Task currTask) {
        return liner + "\n\tNice! I've marked this task as done: \n\t"
                + currTask.toString() + "\n" + liner;
    }

    /**
     * Generates deleted task message once delete is successful.
     *
     * @param currTask most recent Task that was deleted
     * @param totalTasks total number of tasks after deleton of task
     * @return String of deleted task message
     */
    public String printDeletedTask(Task currTask, int totalTasks) {
        return liner + "\n\tNoted. I've removed this task:\n\t" + currTask.toString()
                + "\n\t" + "Now you have " + totalTasks + " tasks in the list.\n" + liner;
    }

    /**
     * Generates all contents found in taskList.
     *
     * @param tasks TaskList
     * @return String message of TaskList
     */
    public String showList(TaskList tasks) {
        StringBuilder sb = new StringBuilder("\tHere are the tasks in your list:\n");
        ArrayList<Task> list = tasks.getList();
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            Task currTask = list.get(i);
            sb.append("\t" + num + ". " + currTask.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Generates matching tasks from search result.
     *
     * @param tasks TaskList
     * @return String message of TaskList of matching results
     */
    public String printMatches(ArrayList<Task> results) {
        StringBuilder sb = new StringBuilder("\tHere are the matching tasks in your list:\n");
        for (int i = 0; i < results.size(); i++) {
            int num = i + 1;
            Task currTask = results.get(i);
            sb.append("\t" + num + ". " + currTask.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Calls for user's input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}