package execution;

import models.Task;

import java.util.Scanner;

/**
 * Represents the items that affect the user interface of the program and handles the interaction with the user.
 */
public class UI {

    protected boolean isRunning;
    Scanner sc = new Scanner(System.in);
    protected String response = "";

    /**
     * Creates a execution.UI object.
     */
    public UI() {

        this.isRunning = true;
    }

    /**
     * Prints out a welcome statement when users first open the Duke program.
     */
    public void welcome() {
        
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello I'm\n" + logo + "\nWhat can I do for you?");
        this.response = ("Hello I'm\n" + logo + "\nWhat can I do for you?");

    }

    /**
     * Returns a welcome message. This is mainly for the GUI.
     *
     * @return a string.
     */

    public String welcomeMsg() {

        return "Hello I'm Duke!" + " \nWhat can I do for you?";

    }

    /**
     * Prints out a goodbye statement when the users enter bye, before the program ends.
     */
    public void exit() {

        System.out.println("Bye. Hope to see you again soon!");
        this.response = "Bye. Hope to see you again soon!";
        this.isRunning = false;

    }

    /**
     * Prints out the response of duke when a task is added.
     *
     * @param task that is being added.
     * @param size of the arraylist after adding of the task.
     */
    public void displayAddingOfTask(Task task, int size) {

        System.out.println(" Noted. I've added this task:\n" + task + "\nNow you have "
                + size + " tasks in the list.");

        this.response = " Noted. I've added this task:\n" + task + "\nNow you have "
                + size + " tasks in the list.";

    }

    /**
     * Prints out the response of duke when a task is being deleted.
     *
     * @param task that is being deleted.
     * @param size of the arraylist after deletion of the task.
     */
    public void displayDeletedTask(Task task, int size) {

        System.out.println(" Noted. I've removed this task:\n" + task + "\nNow you have "
                + size + " tasks in the list.");

        this.response = " Noted. I've removed this task:\n" + task + "\nNow you have "
                + size + " tasks in the list.";

    }

    /**
     * Prints out the response of duke when a task is being marked as done.
     *
     * @param task that is done.
     */
    public void displayDone(Task task) {

        System.out.println(" Nice! I've marked this task as done:\n" + task);
        this.response = " Nice! I've marked this task as done:\n" + task;

    }

    /**
     * Prints out the response of duke when an error is thrown.
     *
     * @param e is the Exception object of the error.
     */
    public void displayError(Exception e) {

        setResponse(e.getMessage());

    }

    /**
     * Prints out the response of duke when user prompts the program to list tasks.
     *
     * @param taskList the current arraylist that duke will print.
     */
    public void listTasks(TaskList taskList) {

        if (taskList.getList().isEmpty()) {

            System.out.println(" No tasks to be found.");
            this.response = " No tasks to be found.";

        } else {

            System.out.println("Here are the tasks in your list\n");
            this.response = "Here are the tasks in your list\n";

            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println((i + 1) + ". " + taskList.getList().get(i) + "\n") ;
                this.response += (i + 1) + ". " + taskList.getList().get(i) + "\n" ;
            }

        }
    }

    /**
     * Prompts the user to enter into the program.
     *
     * @return the string that the user entered.
     */
    public String promptEntry() {

        return sc.nextLine();
    }

    /**
     * Returns the boolean value.
     *
     * @return true if duke is still running, and false if duke is not running.
     */
    public boolean isRunning() {

        return this.isRunning;

    }

    /**
     * Sets the response attribute for UI with the string input.
     *
     * @param response is the string value that we want to assign to the response attribute.
     */
    public void setResponse(String response) {

        this.response = response;

    }

    /**
     * Returns the string value of the response attribute for the GUI.
     *
     * @return the response.
     */
    public String getResponse() {

        return this.response;
    }



}
