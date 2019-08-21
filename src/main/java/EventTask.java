public class EventTask extends Task {

    String date;

    public EventTask(String details, String date) {
        super(details);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
}