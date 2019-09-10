public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "  [D]" + super.toString() + "(by: " + by + ")";
    }
}
