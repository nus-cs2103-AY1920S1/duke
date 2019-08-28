public class Deadline extends Task {
    protected String by;

    // to manage incoming deadline list at specific time
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String newBy) {
        this.by = newBy;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }
}