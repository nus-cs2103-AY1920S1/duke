package taskpackage;

public class Event extends Task {

    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public Event(String isDone, String description, String date) {
        super(description);
        this.date = date;
        if (isDone.equals("\u2713")) {
            this.isDone = true;
        }
    }

    protected String toDataBase() {
        return "[E] | " + getStatusIcon() + " | " + description + " | " + date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}