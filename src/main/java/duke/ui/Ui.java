package duke.ui;

import duke.DukeException;
import duke.todo.Task;

public class Ui {
    StringBuilder messageBuffer;
    /**
     * Displays error when loading file.
     */

    public Ui() {
        this.messageBuffer = new StringBuilder();
    }

    public void showLoadingError() {
        messageBuffer.append("Unable to load file");
    }

    /**
     * Prints greeting message.
     */
    public void getGreeting() {
        messageBuffer.append("Hello! I'm Duke.\nWhat can I do for you?");
    }

    /**
     * Prints formatted report on the task done.
     *
     * @param task Task done.
     */
    public void reportDone(Task task) {
        messageBuffer.append("Nice! I've marked this task as done:\n");
        messageBuffer.append("    " + task.getStatusIcon());
        messageBuffer.append(task.getDescription());
    }

    /**
     * Prints formatted report on the task removed.
     *
     * @param task Task removed.
     * @param numOfTasks Number of tasks left in the task list.
     */
    public void reportRemove(Task task, int numOfTasks) {
        messageBuffer.append("Noted. I've removed this task:\n");
        messageBuffer.append("" + task + "\nNow you have " + numOfTasks);
        messageBuffer.append(" task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    /**
     * Prints formatted report on the task added.
     *
     * @param task Task added.
     * @param numOfTasks Number of tasks left in the task list.e
     */
    public void reportAdd(Task task, int numOfTasks) {
        messageBuffer.append("    Got it. I've added this task:\n");
        messageBuffer.append("      " + task);
        messageBuffer.append("\n    Now you have " + numOfTasks);
        messageBuffer.append(" task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    /**
     * Appends task found to message buffer.
     *
     * @param taskFound Task found.
     */
    public void reportFound(String taskFound) {
        messageBuffer.append(taskFound);
    }

    /**
     * Appends goodbye message to message buffer.
     */
    public void reportBye() {
        this.messageBuffer.append("Bye. Hope to see you again soon!");
    }

    /**
     * Appends formatted tasks.
     *
     * @param formattedTasks String of formatted tasks.
     */
    public void reportList(String formattedTasks) {
        messageBuffer.append(formattedTasks);
    }

    public void reportError(DukeException e) {
        messageBuffer.append(e.getMessage());
    }

    /**
     * Returns buffered message.
     *
     * @return Buffered message.
     */
    public String getMessage() {
        return messageBuffer.toString();
    }

    /**
     * Resets message buffer.
     */
    public void resetMessage() {
        messageBuffer = new StringBuilder();
    }
}
