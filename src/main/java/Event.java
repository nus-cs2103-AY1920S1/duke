public class Event extends Task {
    private String date;

    public Event(String topic, String date) {
        super(topic);
        this.date = date;
        this.type = "E";
        this.details = String.format("%s (at: %s)", topic, date);
    }
}
