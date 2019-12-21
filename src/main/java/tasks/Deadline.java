package tasks;

import java.util.Date;

public class Deadline extends Task {
    private Date deadline;

    Deadline(String description, Date deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + deadline + ")";
    }
}