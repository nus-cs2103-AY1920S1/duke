public class DeadlineTask extends Task {

    protected String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileString() {
        return "T\t" + (this.isDone ? "1\t" : "0\t") + this.description + "\t" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}