package tool;

import task.Task;

public class Ui {

    /**
     * Greeting message for Duke
     */
    public void hi() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Farewell message for Duke
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Handles the UI for "list" command
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Handles the UI for "done" command
     * @param t
     */
    public void done(Task t) {
        System.out.println("Nice! I've marked this task as done:\n  " + t);
    }

    /**
     * Handles the UI for "todo", "deadline" and "event" tasks
     * @param t
     * @param size
     */

    public void addTask(Task t, int size) {
        System.out.println("Got it. I've added this task:\n  " + t
                + "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Handles the UI for "delete" command
     * @param t
     * @param size
     */
    public void delete(Task t, int size) {
        System.out.println("Noted. I've removed this task:\n  " + t +
                "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Handles the UI for "find" command
     */
    public void find() {
        System.out.println("Here are the matching tasks in your list:");
    }

}
