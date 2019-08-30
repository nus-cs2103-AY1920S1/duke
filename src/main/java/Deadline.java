public class Deadline extends Task {
    protected DateTime by;

    public Deadline(String description, DateTime by) {
        super(description);
        this.by = by;
        super.changeToFileFormat('D', description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
