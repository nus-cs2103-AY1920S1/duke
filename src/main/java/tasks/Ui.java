package tasks;

import tasks.Storage;
import tasks.TaskList;

/**
 * Represents a UI object that communicates with the user.
 */
public class Ui {
    /**
     * Shows the welcome message.
     */
    public String showWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello! I'm Duke\n");
        sb.append("What can I do for you?");
        return sb.toString();
    }

    /**
     * Shows the message when a task has been added.
     */
    public String showAdded() {
        return "Got it. I've added this task:";
    }

    /**
     * Shows the message when a wrong input has been detected.
     */
    public String inputWrong() {
        return "Input format is wrong.";
    }

    /**
     * Shows the message when a task has been deleted.
     */
    public String showDeleted() {
        return "Noted. I've removed this task:";
    }

    /**
     * Shows the goodbye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again!";
    }

    /**
     * Prints out the list of tasks.
     *
     * @param t taskList.
     * @param s Storage where the data has been stored.
     */
    public String printList(TaskList t, Storage s) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        int size = t.getCommandList().size();
        for (int i = 1; i < size + 1; i++) {
            sb.append(i + ".");
            sb.append(t.getCommandList().get(i - 1) + "\n");
        }
        return sb.toString();
    }

    /**
     * Prints out the error message.
     *
     * @param e Error.
     */
    public String showError(String e) {
        return e;
    }

    public String printMatching(TaskList t, String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:");
        int size = t.getCommandList().size();
        int noMatching = 0;
        int counter = 1;
        for (int i = 1; i < size + 1; i++) {
            if (t.getCommandList().get(i - 1).getName().contains(keyword)) {
                //returns tasks even if they partially match keyword
                //doesn't have to be the whole word/task
                sb.append(counter + ".");
                sb.append(t.getCommandList().get(i - 1) + "\n");
                counter++;
                noMatching++;
            }
        }
        if (noMatching == 0) {
            return "There are no matching tasks in your tasklist";
        } else {
            return sb.toString();
        }
    }
}
