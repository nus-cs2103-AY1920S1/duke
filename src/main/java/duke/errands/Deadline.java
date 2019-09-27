package duke.errands;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getStatus() {
        String completion;
        if (this.isDone) {
            completion = "1";
        } else {
            completion = "0";
        }

        return "D | " + completion + " | " + this.description + " | " + this.by;
    }
}