public class Deadline extends Task {
    protected DateAndTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = new DateAndTime(by);
    }

    @Override
    public String toSave() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
