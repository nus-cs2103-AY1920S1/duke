package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The user interaction reactor.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in); //The scanner scans user command.

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Reads user command.
     *
     * @return User's command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints loading error message.
     */
    public String showLoadingError() {
        return "There is some error in the file";
    }

    /**
     * Shows error message.
     *
     * @param errorMessage Error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints exit message.
     */
    public String showLeaving() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints tasks.
     *
     * @param tasks Tasks to be printed.
     */
    public String listTasks(TaskList tasks) {
        StringBuilder str = new StringBuilder();
        ArrayList<Task> list = tasks.getList();

        if (list.size() == 0) {
            return "There are no task in the list now.";
        }

        for (int i = 0; i < list.size(); i++) {
            String todo = String.format("%d. %s", i + 1, list.get(i).toString());
            str.append(todo);
            if (i != list.size() - 1) {
                str.append(System.lineSeparator());
            }
            //System.out.println(todo);
        }

        return str.toString();
    }

    /**
     * Prints done message.
     *
     * @param task The task that is done.
     */
    public String showDone(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("Nice! I've marked this task as done:  ");
        str.append(System.lineSeparator());
        str.append(task.toString());
        return str.toString();
    }

    /**
     * Prints delete message.
     *
     * @param task The task be deleted.
     * @param listSize The size of the tasklist.
     */
    public String showDelete(Task task, int listSize) {
        StringBuilder str = new StringBuilder();
        str.append("Noted. I've removed this task:");
        str.append(System.lineSeparator());
        str.append(String.format("  %s", task.toString()));
        str.append(System.lineSeparator());
        str.append(String.format("Now you have %d tasks in the list.", listSize));
        return str.toString();
    }

    /**
     * Prints add message.
     *
     * @param task The task to be added.
     * @param listSize The size of the tasklist.
     */
    public String showAdd(Task task, int listSize) {
        StringBuilder str = new StringBuilder();
        str.append("Got it. I've added this task:");
        str.append(System.lineSeparator());
        str.append(String.format("  %s", task.toString()));
        str.append(System.lineSeparator());
        str.append(String.format("Now you have %d tasks in the list.", listSize));

        return str.toString();
    }

    /**
     * Prints edit message.
     *
     * @param task The task that is edited.
     * @param listSize The size of the tasklist.
     * @param order The order of the edited task.
     */
    public String showEdit(Task task, int listSize, int order) {
        StringBuilder str = new StringBuilder();
        str.append("Got it. I've edit this task:");
        str.append(System.lineSeparator());
        str.append(String.format("  %d. %s",  order, task.toString()));
        str.append(System.lineSeparator());
        str.append(String.format("Now you have %d tasks in the list.", listSize));

        return str.toString();
    }

    /**
     * Prints find message.
     *
     * @param tasklist The tasks contains keyword.
     */
    public String showFind(ArrayList<String> tasklist) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the matching tasks in your list:");
        str.append(System.lineSeparator());
        for (int i = 0; i < tasklist.size(); i++) {
            str.append(tasklist.get(i));
            if (i != tasklist.size() - 1) {
                str.append(System.lineSeparator());
            }
        }
        return str.toString();
    }
}
