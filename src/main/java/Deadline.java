public class Deadline extends Task {
    public Deadline(String description, String date) {
        super(description);
        super.date = date;
        super.type = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.date + ")";
    }
}
