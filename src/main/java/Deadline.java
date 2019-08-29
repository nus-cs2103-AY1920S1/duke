import java.util.Date;

public class Deadline extends Task {
    protected Date deadline;


    public Deadline(String description, Date deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D][" + getStatusIcon() + "] " + getDescription() + " (by: " + this.deadline + ")\n";
    }
}

