/**
 * Represents a Deadline Task
 */
public class Deadline extends Task {
    private String details;

    Deadline(String description, String details) {
        super(description);
        this.details = details;
    }

    Deadline(String description, String details, boolean done) {
        super(description, done);
        this.details = details;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + this.description + " (by: " + this.details + ")";
    }

    @Override
    String store(){
        return "D|" + getStatus() + "|" + this.description + "|" + this.details;
    }
}