package duke.core;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represents a system that deals with user interaction, for example, printing 
 * welcome messages and prompts.
 */
public class Ui {

    /** A <code>Scanner</code> used to read user input. */
    private Scanner sc;

    /**
     * Constructs a <code>Ui</code> object and initilizes the 
     * <code>Scanner</code> to read user input from the system.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Shows Duke logo and welcome message, and prompts user to type instructions.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Shows a welcome message, and prompts user to type instructions. GUI version.
     *
     * @return A string that represents the welcome message.
     */
    public String showWelcomeGui() {
        return "Hello from TSINGHUA\n"
                    + "\nHello! I'm Tsinghua : )\nWhat can I do for you?";
    }

    public String askForFilePath() {
        return "Please specify the path where your tasks are stored, "
                + "in the following format: \nload  {complete file path}/{filename}\n"
                + "You could omit the complete file path and only specify a file name, "
                + "in which case the file will be stored into and read from the current directory.";
    }

    /**
     * Shows an error in loading the file where past tasks are stored.
     */
    public void showLoadingError() {
        System.out.println("Failed to Load past tasks :-(");
    }

    /**
     * Shows an error in loading the file where past tasks are stored. GUI version.
     *
     * @return A string that indicates failure in loading past tasks.
     */
    public String showLoadingErrorGui() {
        return "Failed to Load past tasks :-(";
    }

    /**
     * Shows success in loading past tasks.
     */
    public void showLoadingSuccess() {
        System.out.println("Past tasks loaded successfully :-)");
    }

    /**
     * Shows success in loading past tasks. GUI version.
     *
     * @return A string that indicates success in loading past tasks.
     */
    public String showLoadingSuccessGui() {
        return "Past tasks loaded successfully :-)";
    }

    /**
     * Reads one line of user instruction.
     *
     * @return A string that represents the user instruction.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows a divider line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Shows that a <code>Task</code> has been added, and displays the number 
     * of current tasks in the list.
     *
     * @param t The <code>Task</code> that is added to the list.
     * @param size The number of tasks stored in the <code>TaskList</code>.
     */
    public void addedTask(Task t, int size) {
        System.out.println("Got it. I've added this task: \n  " + t + "\nNow you have "
                + size + " task(s) in the list.");
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
    public String addedTaskGui(Task t, int size) {
        return "Got it. I've added this task: \n  " + t + "\nNow you have "
                + size + " task(s) in the list.";
    }

    /**
     * Shows that a <code>Task</code> has been marked as done.
     *
     * @param t The <code>Task</code> that is marked as done.
     */
    public void markedAsDone(Task t) {
        System.out.println("Nice! I've marked this task as done: \n  " + t);
    }

    /**
     * Shows that a <code>Task</code> has been marked as done. GUI version.
     *
     * @param t The <code>Task</code> that is marked as done.
     * @return A string that indicates the task has been successfully marked as done.
     */
    public String markedAsDoneGui(Task t) {
        return "Nice! I've marked this task as done: \n  " + t;
    }

    /**
     * Shows that a <code>Task</code> has been removed, and displays the number 
     * of current tasks in the list.
     *
     * @param t The <code>Task</code> that is deleted from the list.
     */
    public void removedTask(Task t, int size) {
        System.out.println("Noted. I've removed this task: \n  " + t + "\nNow you have "
                + size + " task(s) in the list.");
    }

    /**
     * Shows that a <code>Task</code> has been removed, and displays the number
     * of current tasks in the list. GUI version.
     *
     * @param t The <code>Task</code> that is deleted from the list.
     * @return A string that represents the successful removal of the task, and
     *          the number of current tasks in the list.
     */
    public String removedTaskGui(Task t, int size) {
        return "Noted. I've removed this task: \n  " + t + "\nNow you have "
                + size + " task(s) in the list.";
    }

    /**
     * Shows that a <code>Task</code> has been updated, and display the number of
     * current tasks in the list.
     *
     * @param t The <code>Task</code> that is updated.
     * @param size The number of tasks stored in the <code>TaskList</code>.
     */
    public void updatedTask(Task t, int size) {
        System.out.println("Ok! I've updated this task: \n  " + t + "\nNow you have "
                + size + " task(s) in the list.");
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
    public String updatedTaskGui(Task t, int size) {
        return "Ok! I've updated this task: \n  " + t + "\nNow you have "
                + size + " task(s) in the list.";
    }

    /**
     * Displays all tasks currently stored in the list.
     *
     * @param tasks The <code>TaskList</code> used to store tasks.
     */
    public void printTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        ArrayList<Task> list = tasks.getList();
        list.forEach(t -> System.out.println(list.indexOf(t) + 1 + "." + t));
    }

    /**
     * Displays all tasks currently stored in the list. GUI version.
     *
     * @param tasks The <code>TaskList</code> used to store tasks.
     * @return A string that represents all existing tasks.
     */
    public String printTasksGui(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> list = tasks.getList();
        list.forEach(t -> sb.append(list.indexOf(t) + 1 + "." + t + "\n"));
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
        }
        return "Here are the tasks in your list:\n" + sb.toString();
    }

    /**
     * Shows content of an error.
     *
     * @param errorMessage A string that represents the content of the error.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Shows content of an error. GUI version.
     *
     * @param errorMessage A string that represents the content of the error.
     * @return A string that represents the error message.
     */
    public String showErrorGui(String errorMessage) {
        return errorMessage;
    }

    /**
     * Shows search results for finding a keyword in the task list. Displays
     * all tasks that contain the keyword.
     *
     * @param tasks The <code>TaskList</code> where keyword is searched.
     * @param keyword A string representation of the keyword.
     */
    public void printSearchResults(TaskList tasks, String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        List<Task> results = tasks.getList().stream()
                    .filter(t -> t.toString().contains(keyword))
                    .collect(Collectors.toList());
        results.forEach(t -> System.out.println(results.indexOf(t) + 1 + "." + t));
    }

    /**
     * Shows search results for finding a keyword in the task list. Displays
     * all tasks that contain the keyword.
     *
     * @param tasks The <code>TaskList</code> where keyword is searched.
     * @param keyword A string representation of the keyword.
     * @return A string that represents all the matching results.
     */
    public String printSearchResultsGui(TaskList tasks, String keyword) {
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
     * Shows bye message to user.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Shows bye message to user. GUI version.
     *
     * @return A string that represents the bye message.
     */
    public String showByeGui() {
        return "Bye. Hope to see you again soon!";
    }
}




