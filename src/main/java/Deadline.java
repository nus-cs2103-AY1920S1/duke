public class Deadline extends Task {
    protected String details;

    public Deadline(String description, String details) {
        super(description);
        this.details = details;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + this.description + " " + this.details;
    }
}