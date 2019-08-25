import java.util.LinkedList;
import java.util.Scanner;

public class Ui {
    Scanner sc;

    /**
     * Constructor
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays welcome message
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hello I'm Duke\n" + "What can I do for you?\n");
    }

    /**
     * Gets command
     *
     * @return user input
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays error message
     *
     * @param message text of error
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays goodbye message
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays all tasks in given linkedlist
     *
     * @param tasks linkedlist containing tasks
     */
    public void showList(LinkedList<Task> tasks) {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasks) {
            System.out.println(i + ". " + t);
            i++;
        }
    }

    /**
     * Displays confirmation for deleting task
     *
     * @param t task deleted
     * @param i total number of tasks
     */
    public void showDelete(Task t, int i) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(t);
        System.out.println("Now you have " + i + " tasks in the list.");
    }

    /**
     * Displays confirmation for completing task
     *
     * @param t completed task
     */
    public void showComplete(Task t) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(t);
    }

    /**
     * Displays confirmation for adding task
     *
     * @param t task added
     * @param i total number of tasks
     */
    public void showAddedTask(Task t, int i) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        System.out.println("Now you have " + i + " tasks in the list.");
    }

    /**
     * Displays line
     */
    public void showLine() {
        System.out.println("-----------------------------------------------------------------------------------\n");
    }
}
