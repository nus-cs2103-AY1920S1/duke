package duke.directprocessor;

import duke.tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The user end of the system. It is in charge of printing information on the screen and take user's input.
 */
public class Ui {

    private Scanner userCommandTaker;

    /**
     * The constructor of the class. Simply initialize the scanner, nothing special.
     */
    public Ui() {
        userCommandTaker = new Scanner(System.in);
    }

    /**
     * This method draws a line on the screen to separate each command information.
     */
    public String drawLine() {
        String toReturn = "";
        toReturn = toReturn + "    ";
        for (int i = 0; i < 70; i++) {
            toReturn = toReturn + "_";
        }
        toReturn = toReturn + "\n";
        return toReturn;
    }

    /**
     * This method pints out the welcome message to the user.
     */
    public String showWelcome() {
        String toReturn = "";
        toReturn = toReturn + drawLine() + "\n";
        toReturn = toReturn + "     Hello, I'm duke.Duke." + "\n";
        toReturn = toReturn + "     What can I do for you?" + "\n";
        toReturn = toReturn + drawLine() + "\n";
        return toReturn;
    }

    /**
     * This method simply shows the String s to the user.
     * Note it will only be called when an exception is caught, input s is actually the error message.
     * @param s The string to be printed on the screen, which is actually the error message.
     */
    public String showError(String s) {
        return "     " + s + "\n";
    }

    /**
     * This method is called to tell the user when the system is unable to load the previous task list.
     */
    public String showLoadingError() {
        return "     Unable to load previous task list. We are starting with a new one.\n";
    }

    /**
     * This method is called to tell the user that a task is successfully added to the task list.
     * @param t The task just have been added.
     */
    public String showAddMessage(Task t, int totalTaskNumber) {
        return "     Got it. I have added this task:\n"
                + "       " + t.taskInfo() + "\n"
                + "     You have now " + totalTaskNumber + " tasks in the list.\n";
    }

    /**
     * This method is to tell the user that a task is successfully deleted from the task list.
     * @param t The task just have been deleted.
     */
    public String showDeleteMessage(Task t, int totaltaskNumber) {
        return "     Noted. I've removed this task:\n"
                + "       " + t.taskInfo() + "\n" +
                "     Now you have " + totaltaskNumber + " tasks in the list.\n";
    }

    /**
     * This task is to list out the task list when the list command is given by the user.
     * @param tl The task information to be listed out in the form of a string array list.
     */
    public String showListMessage(ArrayList<String> tl) {
        String toReturn = "";
        for (int i = 0; i < tl.size(); i++) {
            toReturn = toReturn + "       " + tl.get(i) + "\n";
        }
        return toReturn;
    }

    /**
     * This method is to tell the user that a task is successfully set as finished.
     * @param t The task that just have been set as finished.
     */
    public String showFinishMessage(Task t) {
        return "     Nice! I have set this task as done:\n"
                + "       " + t.taskInfo() + "\n";
    }

    /**
     * This method shows the reader all tasks that matches his target array.
     * @param tl The task information to be listed out in the form of a string array list.
     */
    public String showFindMessage(ArrayList<String> tl) {
        String toReturn = "";
        toReturn = toReturn + "     Here are the matching tasks in your list:\n";
        for (int i = 0; i < tl.size(); i++) {
           toReturn = toReturn + "       " + tl.get(i) + "\n";
        }
        return toReturn;
    }

    /**
     * This method is to give the user a goodbye message when the exit command is given.
     */
    public String showExitMessage() {
        return "     Bye. Hope to see you again soon!\n";
    }
}
