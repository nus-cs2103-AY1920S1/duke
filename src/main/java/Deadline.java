public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String date = formatDate(by);
        if (getIsCorrectFormat()) {
            return "[D]" + super.toString() + description + " (by: " + date + ")";
        } else {
            return "Invalid date format!";
        }
    }
}