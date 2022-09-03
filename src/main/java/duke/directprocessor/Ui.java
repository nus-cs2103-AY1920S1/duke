package duke.directprocessor;

import duke.tasks.Task;
import java.util.ArrayList;

/**
 * The user end of the system. It is in charge of printing information on the screen and take user's input.
 */
public class Ui {

    public Ui() {

    }

    /**
     * This method pints out the welcome message to the user.
     * @return The welcome message as a string.
     */
    public String showWelcome() {
        String toReturn = "";
        toReturn = toReturn + "Hello, I'm duke." + "\n";
        toReturn = toReturn + "What can I do for you?" + "\n";
        return toReturn;
    }

    /**
     * This method is called to tell the user that a task is successfully added to the task list.
     *
     * @param t The task just have been added.
     * @param totalTaskNumber The total number of tasks in the list.
     * @return The task message of the newly added task as a string.
     */
    public String showAddMessage(Task t, int totalTaskNumber) {
        return "Got it master! I have added this task:\n"
                + " " + t.taskInfo() + "\n"
                + "You have now " + totalTaskNumber + " tasks in the list.\n";
    }

    /**
     * This method is called to tell the user that a new possible slot of an event is added to the target event.
     *
     * @param t The target event.
     * @return The String containing successful adding message.
     */
    public String showSlotMessage(Task t) {
        return "Got it master! I have added this slot to the following event:\n"
                + " " + t.taskInfo() + "\n";
    }

    public String showSpecifyMessage(Task t) {
        return "Got it master! I have specified this slot to the following event: \n"
                + " " + t.taskInfo() + "\n";
    }

    /**
     * This method is to tell the user that a task is successfully deleted from the task list.
     *
     * @param t The task just have been deleted.
     * @param totalTaskNumber The total number of tasks in the list
     * @return The task message of the deleted task as a string
     */
    public String showDeleteMessage(Task t, int totalTaskNumber) {
        return "Noted master! I've removed this task:\n"
                + "  " + t.taskInfo() + "\n"
                + "Now you have " + totalTaskNumber + " tasks in the list.\n";
    }

    /**
     * This task is to list out the task list when the list command is given by the user.
     * @param tl The task information to be listed out in the form of a string array list.
     * @return The processed list of all task information as a string.
     */
    public String showListMessage(ArrayList<String> tl) {
        String toReturn = "";
        for (int i = 0; i < tl.size(); i++) {
            toReturn = toReturn + tl.get(i) + "\n";
        }
        return toReturn;
    }

    /**
     * This method is to tell the user that a task is successfully set as finished.
     * @param t The task that just have been set as finished.
     * @return The task information of the finished task as a string.
     */
    public String showFinishMessage(Task t) {
        return "Nice master! I have set this task as done:\n"
                + " " + t.taskInfo() + "\n";
    }

    /**
     * This method shows the reader all tasks that matches his target array.
     * @param tl The task information to be listed out in the form of a string array list.
     * @return The processed list of task information that fits the finding target information.
     */
    public String showFindMessage(ArrayList<String> tl) {
        String toReturn = "";
        toReturn = toReturn + "Here are the matching tasks in your list, master:\n";
        for (int i = 0; i < tl.size(); i++) {
            toReturn = toReturn + " " + tl.get(i) + "\n";
        }
        return toReturn;
    }

    /**
     * This method is to give the user a goodbye message when the exit command is given.
     * @return The exit information as a string.
     */
    public String showExitMessage() {
        return "Bye, master. Hope to see you again soon!\n" + "You can now close the window.";
    }
}
