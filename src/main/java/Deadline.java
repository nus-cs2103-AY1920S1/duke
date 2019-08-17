public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }
}
