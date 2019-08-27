public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by;
    }

    @Override
    public String serialise() {
        return String.format("D | %d | %s | %s\n", isDone ? 1 : 0, description, by);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "]" + super.toString() + " (by: " + by + ")";
    }
}
