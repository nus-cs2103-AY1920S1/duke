package task;

public class Deadline extends Task {
    protected String slashKeyword;
    protected String deadline;

    /**
     * Creates a new deadline task.
     * @param description Description of the task
     * @param slashKeyword Keyword right after the slash, which should equal 'by'
     * @param deadline Deadline of the task
     */
    public Deadline(String description, String slashKeyword, String deadline) {
        super(description);
        this.slashKeyword = slashKeyword;
        this.deadline = deadline;
    }

    public String getTaskInitial() {
        return "D";
    }

    public boolean isValid() {
        return this.description != null && this.slashKeyword.equals("by") && this.deadline != null;
    }

    @Override
    public String extraText() {
        return " (by: " + this.deadline + ")";
    }
}
