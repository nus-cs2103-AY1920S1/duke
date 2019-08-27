public class Deadline extends Task {
    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    protected String formatToWrite() {
        if (this.done) {
            return String.format("D | %d | %s | %s", 1, this.description, this.by);
        } else {
            return String.format("D | %d | %s | %s", 0, this.description, this.by);
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
