package execution;

import models.Task;

import java.util.Scanner;

/**
 * Represents the items that affect the user interface of the program and handles the interaction with the user.
 */
public class UI {

    protected boolean isRunning;
    Scanner sc = new Scanner(System.in);

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
    }

    /**
     * Prints out a goodbye statement when the users enter bye, before the program ends.
     */
    public void exit() {

        System.out.println("Bye. Hope to see you again soon!");
        this.isRunning = false;

    }

    public void displayAddingOfTask(Task task, int size) {
        System.out.println(" Noted. I've added this task:\n" + task + "\nNow you have "
                + size + " tasks in the list.");
    }

    public void displayDeletedTask(Task task, int size) {
        System.out.println(" Noted. I've removed this task:\n" + task + "\nNow you have "
                + size + " tasks in the list.");
    }

    public void displayDone(Task task) {
        System.out.println(" Nice! I've marked this task as done:\n" + task);
    }

    public void listTasks(TaskList taskList) {
        if (taskList.getList().isEmpty()) {
            System.out.println(" No tasks to be found.");
        } else {
            System.out.println("Here are the tasks in your list\n");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println((i + 1) + ". " + taskList.getList().get(i) + "\n") ;
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

    public boolean isRunning() {
        return this.isRunning;
    }





}
