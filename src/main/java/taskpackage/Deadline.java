package taskpackage;

public class Deadline extends Task {

    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(String isDone, String description, String date) {
        super(description);
        this.date = date;
        if (isDone.equals("\u2713")) {
            this.isDone = true;
        }
    }

    protected String toDataBase() {
        return "[D] | " + getStatusIcon() + " | " + description + " | " + date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}