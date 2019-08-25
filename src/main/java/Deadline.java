public class Deadline extends Task {
    private String deadlineBy;

    Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String getOutputFormat() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, deadlineBy);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadlineBy);
    }
}
