public class Event extends Task {
    private String dateTime;
    // Constructor
    protected Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    // Getters & Setters
    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
