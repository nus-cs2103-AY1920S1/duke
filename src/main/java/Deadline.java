public class Deadline extends Task {
    private String by;

    protected Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    protected Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        return String.format("D | %s | %s", super.toSaveFormat(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
