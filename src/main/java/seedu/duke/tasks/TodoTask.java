package seedu.duke.tasks;

public class TodoTask extends Task {
    private static final long serialVersionUID = 208976467902347L;

    /**
     * Create a generic todo task.
     *
     * @param description the task description
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
