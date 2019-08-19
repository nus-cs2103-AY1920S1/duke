public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    public String toString() {
        return "[D][" + getStatusIcon() + "] " + super.format_description();
    }

}
