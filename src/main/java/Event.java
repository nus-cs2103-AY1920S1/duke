public class Event extends Task {

    String date;

    public Event(String details, String date) {
        super(details);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}