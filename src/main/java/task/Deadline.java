package task;

public class Deadline extends Task {
    protected String slashKeyword;
    protected String deadline;

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
