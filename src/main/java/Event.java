public class Event extends TaskWithDate {

    public Event(String description, String date) {
        super(description, date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}