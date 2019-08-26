public class Events extends Task {

    protected String date;

    public Events(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (at: " + date + ")";
    }
}
