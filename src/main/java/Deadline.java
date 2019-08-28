public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[D][" + getStatusIcon() + "] " + super.format_description();
    }

}
