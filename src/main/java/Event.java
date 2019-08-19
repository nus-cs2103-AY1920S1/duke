public class Event extends Task {
    private String date;
    private String time;

    // Constructor
    public Deadline(String description, String date, String time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    // Getters & Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + " " + time + by + ")";
    }
}
