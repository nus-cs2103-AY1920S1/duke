public class Deadline extends Task {

    public Deadline(String description, String by) {
        super(description);
        super.date = by;
        super.type = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.date + ")";
    }
}
