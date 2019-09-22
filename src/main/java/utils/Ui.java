package utils;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;
import task.Task;

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
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        return greeting + "\n";
    }

    /**
     * Says goodbye to user when exit program.
     *
     * @return String of goodbye message
     */
    public String showBye() {
        return  "\nBye. Hope to see you again soon!\n";
    }

    /**
     * Generates error message.
     */
    public String showError(String message) {
        return message + "\n";
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
        return  "\nGot it. I've added this task:\n" + newTask.toString()
                + "\n" + "Now you have " + totalTasks + " tasks in the list.\n";
    }

    /**
     * Generates add syntax message once AddAlias is successful.
     *
     * @param type Type of command for alias
     * @param alias The Alias
     * @return String of successful add alias message
     */
    public static String printAddAlias(String type, String alias) {
        return  "\nGot it. I've overwritten the alias:\n" + type + " : " + alias;
    }


    /**
     * Generates done task message once successful.
     *
     * @param currTask Most recent Task executed
     * @return String of successful done task message
     */
    public String printDoneTask(Task currTask) {
        return "\nNice! I've marked this task as done: \n"
                + currTask.toString() + "\n";
    }

    /**
     * Generates deleted task message once delete is successful.
     *
     * @param currTask most recent Task that was deleted
     * @param totalTasks total number of tasks after deleton of task
     * @return String of deleted task message
     */
    public String printDeletedTask(Task currTask, int totalTasks) {
        return "\nNoted. I've removed this task:\n" + currTask.toString()
                + "\n" + "Now you have " + totalTasks + " tasks in the list.\n";
    }

    /**
     * Generates all contents found in taskList.
     *
     * @param tasks TaskList
     * @return String message of TaskList
     */
    public String showList(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        ArrayList<Task> list = tasks.getList();
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            Task currTask = list.get(i);
            sb.append(num + ". " + currTask.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Generates matching tasks from search result.
     *
     * @param results List of matching tasks
     * @return String message of TaskList of matching results
     */
    public String printMatches(ArrayList<Task> results) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < results.size(); i++) {
            int num = i + 1;
            Task currTask = results.get(i);
            sb.append(num + ". " + currTask.toString() + "\n");
        }
        return sb.toString();
    }

    /**
     * Calls for user's input.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

}