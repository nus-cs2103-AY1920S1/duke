package tool;

import task.Task;

public class Ui {

    /**
     * Greeting message for Duke
     */
    public String hi() {

        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Farewell message for Duke
     */
    public String bye() {

        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles the UI for "list" command
     */
    public String list() {

        return "Here are the tasks in your list:";
    }

    /**
     * Handles the UI for "done" command
     * @param t
     */
    public String done(Task t) {

        return "Nice! I've marked this task as done:\n  " + t;
    }

    /**
     * Handles the UI for "todo", "deadline" and "event" tasks
     * @param t
     * @param size
     */

    public String addTask(Task t, int size) {
        return "Got it. I've added this task:\n  " + t
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Handles the UI for "delete" command
     * @param t
     * @param size
     */
    public String delete(Task t, int size) {
        return "Noted. I've removed this task:\n  " + t +
                "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Handles the UI for "find" command
     */
    public String find() {

        return "Here are the matching tasks in your list:";
    }

}
