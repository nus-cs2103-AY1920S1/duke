package todo;

public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description);

        this.date = date;
    }

    @Override
    public String getFormattedTask() {
        return "E | " + super.getDescription() +
                " /at " + date;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + this.getDescription() +
                " (on: " + date + ")";
    }
}
