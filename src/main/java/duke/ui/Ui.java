package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Ui
 * Contains functions to interact with user.
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String getWelcome() {
        return "Hello! I'm Duke, what can I do for you?";
    }

    public String getBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Display loading error message.
     */
    public void showLoadingError() {
        System.out.println("Error in loading tasks into Duke.");
    }

    /**
     * Read user input from system input.
     *
     * @return String User input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    public String getAddedTask(char firstAlphabet, boolean isDone, String taskDescription, int numberOfItems) {
        char icon;
        if (isDone) {
            icon = 'Y';
        } else {
            icon = 'X';
        }
        return "Got it. I've added this task: \n" + "\t[" + firstAlphabet + "][" + icon + "] " + taskDescription +
               "\nNow you " + "have " + numberOfItems + " in the list.";
    }

    public String getList(ArrayList<String> listToPrint) {
        String result = "Here are the tasks in your list:";
        for (int i = 0; i < listToPrint.size(); i++) {
            result = result + "\n" + (i + 1) + "." + listToPrint.get(i);
        }
        return result;
    }

    public String getDeletedTask(Task t, int taskCount) {
        return "Noted. I've removed this task:" + t.printTask() + "Now you have " + taskCount + " tasks in the list.";
    }

    public String getDoneTask(Task t) {
        return "Nice! I've marked this task as done:" + t.printTask();
    }

    /**
     * Display index error message.
     */
    public void showIndexError() {
        System.out.println("Invalid task number. Please check again.");
    }

    /**
     * Display input error message.
     */
    public void showInputError() {
        System.out.println("Error! Please check input again");
    }

    /**
     * Display command not found message.
     */
    public void showCommandNotFoundError() {
        System.out.println("Command not found. Please check command again.");
    }

    public String getMatchingTaskList(ArrayList<String> listFound) {
        if (listFound.size() == 0) {
            return "No matching result from your list.";
        }

        String result;
        result = "Here are the matching tasks in your list:";
        for (int i = 0; i < listFound.size(); i++) {
            result = result + (i + 1) + "." + listFound.get(i);
        }
        return result;
    }
}
