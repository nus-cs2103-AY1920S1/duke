import tasks.Task;

/**
 * Deals with the interactions with the user.
 */
public class Ui {
    public Ui() {}

    /**
     * Shows the details of the task added.
     *
     * @return The task added and the number of task in the list.
     */
    public String showTaskAdded(Task task, int size) {
        StringBuffer taskAdded = new StringBuffer("Got it. I've added this task:\n ");
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
     */
    public String showDeadlineError() {
        return "OOPS!!! The deadline description cannot be empty.";
    }

    /**
     * Shows hello when the application runs.
     */
    public void showHello() {
        System.out.println("Hello! I'm your task manager!");
        System.out.println("What can I do for you?");
    }

    /**
     * Shows bye when the application ends.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows the details of the task marked done.
     */
    public String showTaskDone(Task task) {
        StringBuffer taskDone = new StringBuffer("Nice! I've marked this task as done: /n ");
        taskDone.append(task);
        return taskDone.toString();
    }

    /**
     * Shows the details of the task removed.
     */
    public String showTaskRemoved(Task removed, int size) {
        StringBuffer taskRemoved = new StringBuffer("Noted. I've removed this task: ");
        taskRemoved.append(removed);
        taskRemoved.append("Now you have ");
        taskRemoved.append(size);
        taskRemoved.append(" tasks in the list.");
        return taskRemoved.toString();
    }

    /**
     * Shows event error.
     */
    public String showEventError() {
        return "OOPS!!! The event description cannot be empty.";
    }

    /**
     * Shows task error.
     */
    public String showNoSuchTaskError() {
        return "OOPS!!! There is no such task.";
    }

    /**
     * Shows command error.
     */
    public String showUnknownCommandError() {
        return "OOPS!!! I'm sorry, I don't know what you mean :(";
    }
}