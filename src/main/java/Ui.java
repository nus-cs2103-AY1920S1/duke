import java.util.LinkedList;
import java.util.Scanner;

public class Ui {
    //Scanner sc;

    /**
     * Constructor
     */
    public Ui() {

    }

    /**
     * Displays welcome message
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        return logo + "Hello I'm Duke\n" + "What can I do for you?\n";
    }

    /**
     * Gets command
     *
     * @return user input
     */
    /*
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays error message
     *
     * @param message text of error
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Displays goodbye message
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays all tasks in given linkedlist
     *
     * @param tasks linkedlist containing tasks
     */
    public String showList(LinkedList<Task> tasks) {
        return "Here are the tasks in your list:\n" + showGivenList(tasks);
    }
    public String showMatch(LinkedList<Task> tasks) {
        return "Here are the matching tasks in your list:\n" + showGivenList(tasks);
    }
    private String showGivenList(LinkedList<Task> tasks) {
        int i = 1;
        StringBuilder result = new StringBuilder();
        if (!tasks.isEmpty()) {
            for (Task t : tasks) {
                result.append(i + ". " + t + "\n");
                i++;
            }
        }

        return result.toString();
    }

    /**
     * Displays confirmation for deleting task
     *
     * @param t task deleted
     * @param i total number of tasks
     */
    public String showDelete(Task t, int i) {

        return "Noted. I've removed this task:\n" + t + "\n" + "Now you have " + i + " tasks in the list.";
    }

    /**
     * Displays confirmation for completing task
     *
     * @param t completed task
     */
    public String showComplete(Task t) {
        return "Nice! I've marked this task as done:\n" + t;
    }

    /**
     * Displays confirmation for adding task
     *
     * @param t task added
     * @param i total number of tasks
     */
    public String showAddedTask(Task t, int i) {

        return "Got it. I've added this task:\n" + t + "\n" +  "Now you have " + i + " tasks in the list.";
    }

    /**
     * Displays line
     */
    public void showLine() {
        System.out.println("-----------------------------------------------------------------------------------\n");
    }
}
