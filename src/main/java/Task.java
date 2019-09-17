import java.io.Serializable;

/**
 * Task that can be in the form of Todo, Deadline or Event.
 */
public class Task implements Serializable {
    private String task;
    private boolean status = false;

    /**
     * Constructs a new Task object.
     *
     * @param task description of the task.
     */
    public Task(String task) {
        assert task.length() > 0 : "Invalid task name";
        this.task = task;
    }

    /**
     * Marks a Task as done.
     */
    public void markAsDone() {
        this.status = true;
        System.out.println(
                "    ____________________________________________________________\n"
                        +
                        "     Nice! I've marked this task as done:\n"
                        +
                        "     " + this + "\n"
                        +
                        "    ____________________________________________________________\n");
    }

    /**
     * Returns the status of the Task.
     *
     * @return status of the Task.
     */
    public boolean getStatus() {
        return this.status;
    }

    /**
     * Returns description of the Task.
     *
     * @return description of the Task.
     */

    public String getTask() {
        return this.task;
    }

    /**
     * Returns a string representation of a Task object. Example: "[x] task description".
     *
     * @return a string representation of a Task object.
     */
    public boolean contains(String s) {
        return task.contains(s);
    }

    @Override
    public String toString() {
        String logo;
        if (this.status == false) {
            logo = "✗";
        } else {
            logo = "✓";
        }
        return "["
                +
                logo
                +
                "] "
                + this.getTask();
    }

}

