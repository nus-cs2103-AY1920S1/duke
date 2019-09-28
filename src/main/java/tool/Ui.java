package tool;

import task.Task;

public class Ui {

    /**
     * Greeting message for Duke
     */
    protected String hi() {

        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Farewell message for Duke
     */
    protected String bye() {

        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles the UI for "list" command
     */
    protected String list(TaskList tasks) {
        int count = 1;
        String str = "Here are the tasks in your list:\n";
        for (Task t : tasks.getList()) {
            str = str + count + ". " + t + "\n";
            count++;
        }
        return str;
    }

    /**
     * Handles the UI for "done" command
     * @param t
     */
    protected String done(Task t) {

        return "Nice! I've marked this task as done:\n  " + t;
    }

    /**
     * Handles the UI for "todo", "deadline" and "event" tasks
     * @param t
     * @param size
     */

    protected String addTask(Task t, int size) {
        return "Got it. I've added this task:\n  " + t
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Handles the UI for "delete" command
     * @param t
     * @param size
     */
    protected String delete(Task t, int size) {
        return "Noted. I've removed this task:\n  " + t +
                "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Handles the UI for "find" command
     */
    protected String find() {

        return "Here are the matching tasks in your list:";
    }

    /**
     * Handles UI for "edit" command
     * @param prevTask
     * @param newTask
     * @return
     */
    protected String edit(String prevTask, Task newTask) {
        if (prevTask.equals(newTask.toString())) {
            return "OOPS!! There's nothing to change.";
        } else {
            return "Success! I've edited this task from this:\n" + prevTask + "\nto this:\n" + newTask;
        }
    }

}
