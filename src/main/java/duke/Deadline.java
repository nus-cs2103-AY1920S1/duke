package duke;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        this(description, false, by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String toSaveFormat() {
        return "D" + "|" + super.toSaveFormat() + "|" + by + '\n';
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
