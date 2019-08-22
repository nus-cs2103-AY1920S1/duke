public class Deadline extends Task {
    protected String by;

    public Deadline(int id, String description, String by) {
        super(id, description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by +")";
    }
}
