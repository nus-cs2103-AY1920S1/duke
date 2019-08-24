package task;

public class Deadline extends Task {
    protected String deadline;

    /**
     * Creates a new deadline task.
     * @param description Description of the task
     * @param deadline Deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTaskInitial() {
        return "D";
    }

    @Override
    public String extraText() {
        return " (by: " + this.deadline + ")";
    }
}
