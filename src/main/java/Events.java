public class Events extends Task {

    protected DateTime date;

    public Events(String description, DateTime date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    public DateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (at: " + date + ")";
    }
}
