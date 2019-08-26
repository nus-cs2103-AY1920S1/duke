public class Events extends Task {

    protected DateTime date;

    public Events(String description, DateTime date) {
        super(description);
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
