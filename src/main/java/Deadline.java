public class Deadline extends Task {
    protected DateAndTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = new DateAndTime(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
