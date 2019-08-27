import java.util.Date;

class Event extends Task {
    private Date at;

    Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    Event(String description, boolean isDone, Date at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    String toSaveFormat() {
        return String.format("E | %s | %s", super.toSaveFormat(), Duke.dateFormatter.format(this.at));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (by: %s)", super.toString(), Duke.dateFormatter.format(this.at));
    }
}
