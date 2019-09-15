package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Ui
 * Contains functions to interact with user.
 */
public class Ui {

    public String getWelcome() {
        StringBuilder welcomeMessage = new StringBuilder("Hello! I'm Duke, what can I do for you?");
        welcomeMessage.append("\n");
        welcomeMessage.append("For help, please enter \"HELP\" for commands.\n");
        welcomeMessage.append("For help on using commands, please enter \"HELP <COMMAND>\"\n");
        return welcomeMessage.toString();
    }

    public String getBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String getAddedTask(char firstAlphabet, boolean isDone, String taskDescription, int numberOfItems) {
        char icon;
        if (isDone) {
            icon = 'Y';
        } else {
            icon = 'X';
        }

        StringBuilder addedTaskMessage = new StringBuilder("Got it. I've added this task: \n");
        addedTaskMessage.append("\t[").append(firstAlphabet).append("][").append(icon).append("] ").append(taskDescription).append("\n");
        addedTaskMessage.append("Now you have ").append(numberOfItems).append(" in the list.\n");
        return addedTaskMessage.toString();
    }

    public String getList(ArrayList<String> listToPrint) {
        StringBuilder listOfTasksToPrint = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < listToPrint.size(); i++) {
            listOfTasksToPrint.append(i + 1).append(".").append(listToPrint.get(i)).append("\n");
        }
        System.out.println(listOfTasksToPrint.toString());
        return listOfTasksToPrint.toString();
    }

    public String getDeletedTask(Task t, int taskCount) {
        StringBuilder deletedTaskMessage = new StringBuilder("Noted. I've removed this task:\n");
        deletedTaskMessage.append(t.printTask()).append("\n");
        deletedTaskMessage.append("Now you have ").append(taskCount).append(" tasks in the list.");
        return deletedTaskMessage.toString();
    }

    public String getDoneTask(Task t) {
        StringBuilder doneTaskMessage = new StringBuilder("Nice! I've marked this task as done:\n");
        doneTaskMessage.append(t.printTask());
        return doneTaskMessage.toString();
    }

    /**
     * Display error message.
     */
    public String getIndexError() {
        return "Invalid file or tasks. Please check again.";
    }

    public String getMatchingTaskList(ArrayList<String> listFound) {
        if (listFound.size() == 0) {
            return "No matching result from your list.";
        }

        StringBuilder matchingTaskResult = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < listFound.size(); i++) {
            matchingTaskResult.append(i + 1).append(".").append(listFound.get(i)).append("\n");
        }
        return matchingTaskResult.toString();
    }

    public String getLoadingError() {
        return "Error in Loading files\n";
    }

    public String getSavingError() {
        return "Error in saving to disk.";
    }
}
