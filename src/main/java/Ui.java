/**
 * Deals with the interactions with the user.
 *
 * @author Michelle Yong
 */
public class Ui {
    public Ui() {}

    /**
     * Shows the details of the task added.
     *
     * @param task The task added.
     * @param size The new total number of tasks.
     * @return The task added and the number of task in the list.
     */
    public String showTaskAdded(Task task, int size) {
        StringBuffer taskAdded =
                new StringBuffer("Got it. I've added this task:\n ");
        taskAdded.append(task);
        taskAdded.append("\nNow you have ");
        taskAdded.append(size);
        taskAdded.append(" tasks in the list.");
        return taskAdded.toString();
    }

    /**
     * Shows todo error.
     *
     * @return The todo error message.
     */
    public String showTodoError() {
        return "OOPS!!! The todo description cannot be empty.";
    }

    /**
     * Shows deadline error.
     *
     * @return The deadline error message.
     */
    public String showDeadlineError() {
        return "OOPS!!! The deadline description cannot be empty.";
    }

    /**
     * Shows bye when the application ends.
     *
     * @return The bye message.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows the details of the task marked done.
     *
     * @param task The task that is marked done.
     * @return The message telling user that the task has been marked done.
     */
    public String showTaskDone(Task task) {
        assert (task.getIsDone());
        StringBuffer taskDone =
                new StringBuffer("Nice! I've marked this task as done: \n");
        taskDone.append(task);
        return taskDone.toString();
    }

    /**
     * Shows the details of the task removed.
     *
     * @param removed The task removed.
     * @param size The new total number of tasks.
     * @return The message telling user that the task has been removed.
     */
    public String showTaskRemoved(Task removed, int size) {
        StringBuffer taskRemoved =
                new StringBuffer("Noted. I've removed this task: ");
        taskRemoved.append(removed);
        taskRemoved.append("\nNow you have ");
        taskRemoved.append(size);
        taskRemoved.append(" tasks in the list.\n");
        return taskRemoved.toString();
    }

    public String showPrioritySet(Task task) {
        StringBuffer taskPriority =
                new StringBuffer("Noted. I've have set the task as ");
        taskPriority.append(task.getPriority());
        taskPriority.append(" priority.");
        return taskPriority.toString();
    }

    // still need to update the file itself

    /**
     * Shows event error.
     *
     * @return The event error message.
     */
    public String showEventError() {
        return "OOPS!!! The event description cannot be empty.";
    }

    /**
     * Shows task error.
     *
     * @return The message telling user that there is no such task.
     */
    public String showNoSuchTaskError() {
        return "OOPS!!! There is no such task.";
    }

    /**
     * Shows command error.
     *
     * @return The message telling user that there is no such command.
     */
    public String showUnknownCommandError() {
        return "OOPS!!! I'm sorry, I don't know what you mean :(";
    }
}