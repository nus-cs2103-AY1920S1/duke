public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    // [Level-7] Converts task to String format to write to hard disk
    public String convertTaskToString() {
        return String.format("D | %s | %s | %s", this.getStatus(), this.description, this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
