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

    @Override
    public String getTaskInitial() {
        return "D";
    }

    @Override
    public boolean isValid() {
        return this.description != null && this.slashKeyword != null && this.slashKeyword.equals("by")
            && this.deadline != null;
    }

    @Override
    public String invalidMessage() {
        if (this.description == null) {
            return "The description of a deadline cannot be empty.";
        } else {
            return "A deadline must have a due date or time.";
        }
    }

    @Override
    public String extraText() {
        return " (by: " + this.deadline + ")";
    }
}
