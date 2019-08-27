package duke.DirectProcessor;

import duke.Tasks.Task;
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
    public void drawLine() {
        System.out.print("    ");
        for (int i = 0; i < 70; i++) System.out.print("_");
        System.out.print("\n");
    }

    /**
     * This method pints out the welcome message to the user.
     */
    public void showWelcome() {
        drawLine();
        System.out.println("     Hello, I'm duke.Duke.");
        System.out.println("     What can I do for you?");
        drawLine();
    }

    /**
     * This method simply shows the String s to the user.
     * Note it will only be called when an exception is caught, input s is actually the error message.
     * @param s The string to be printed on the screen, which is actually the error message.
     */
    public void showError(String s) {
        System.out.println("     " + s);
    }

    /**
     * This method is called to tell the user when the system is unable to load the previous task list.
     */
    public void showLoadingError() {
        System.out.println("     Unable to load previous task list. We are starting with a new one.");
    }

    /**
     * This method is called to tell the user that a task is successfully added to the task list.
     * @param t The task just have been added.
     */
    public void showAddMessage(Task t) {
        System.out.println("     Got it. I have added this task:");
        System.out.println("       " + t.taskInfo());
        System.out.println("     You have now " + Task.getTotalNumber() + " tasks in the list.");
    }

    /**
     * This method is to tell the user that a task is successfully deleted from the task list.
     * @param t The task just have been deleted.
     */
    public void showDeleteMessage(Task t) {
        System.out.println("     Noted, Noted. I've removed this task: ");
        System.out.println("       " + t.taskInfo());
        System.out.println("     Now you have " + Task.getTotalNumber() + " tasks in the list");
    }

    /**
     * This task is to list out the task list when the list command is given by the user.
     * @param tl The task information to be listed out in the form of a string array list.
     */
    public void showListMessage(ArrayList<String> tl) {
        for (int i = 0; i < tl.size(); i++) {
            System.out.println("       " + tl.get(i));
        }
    }

    /**
     * This method is to tell the user that a task is successfully set as finished.
     * @param t The task that just have been set as finished.
     */
    public void showFinishMessage(Task t) {
        System.out.println("     Nice! I have set this task as done:");
        System.out.println("       " + t.taskInfo());
    }

    /**
     * This method shows the reader all tasks that matches his target array.
     * @param tl The task information to be listed out in the form of a string array list.
     */
    public void showFindMessage(ArrayList<String> tl) {
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < tl.size(); i++) {
            System.out.println("       " + tl.get(i));
        }
    }

    /**
     * This method is to give the user a goodbye message when the exit command is given.
     */
    public void showExitMessage() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * This method takes in the user's input as a string.
     * @return The user's input as a string.
     */
    public String takeCommand() {
        return userCommandTaker.nextLine();
    }

}
