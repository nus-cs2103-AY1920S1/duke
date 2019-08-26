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
    public String format() {
        String formatted = "D | ";
        int binary = 0;
        if (super.isDone == true) {
            binary = 1;
        }
        formatted += binary + " | " + super.description + " | " + this.by;
        return formatted;
    }
}
