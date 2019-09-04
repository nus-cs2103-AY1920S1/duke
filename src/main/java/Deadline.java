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

    public String taskSavedTextFormat() {
        String done = "0";
        if (super.isDone) {
            done = "1";
        }
        return "D | " + done + " | " + super.description + " | " + by;
    }
}
