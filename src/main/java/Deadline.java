public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String print() {
        if (this.isDone) {
            return "D @ 1 @ " + this.description + " @ " + this.by;
        } else {
            return "TD @ 0 @ " + this.description + " @ " + this.by;
        }
    }
}