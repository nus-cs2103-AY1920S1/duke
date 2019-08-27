public class Deadline extends Task {

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
        this.type = "deadline";
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "]" + description + " (by: " + date + ")";
    }
}