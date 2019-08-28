package todo;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);

        this.deadline = deadline;
    }

    @Override
    public String getFormattedTask() {
        return "D | " + super.getDescription() +
                " /by " + deadline;
    }
    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + this.getDescription() +
                " (by: " + deadline + ")";
    }
}
