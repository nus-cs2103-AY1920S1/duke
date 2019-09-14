package tasks;

/**
 * Represents a task that is input by the user
 */
public class Task {
    protected boolean completed = false;
    protected String name;

    /**
     * Constructor for task object
     *
     * @param name Task name.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks task as complete
     */
    public String complete() {
        this.completed = true;
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("+|" + this.name);
        return sb.toString();
    }

    /**
     * Returns name of task.
     *
     * @return name of task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Changes the sign of impending task.
     */
    public void changeSign() {
        completed = true;
    }

    public String toString() {
        if (!this.completed) {
            return "-|" + this.name;
        } else {
            return "+|" + this.name;
        }
    }

}
