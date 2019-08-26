package seedu.duke.model;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.type = "D";
        this.by = by;
    }

    public Deadline(String description, String by, int status) {
        super(description, status);
        this.type = "D";
        this.by = by;
    }

    @Override
    public String toTextFileString() {
        return super.toTextFileString() + "," + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
