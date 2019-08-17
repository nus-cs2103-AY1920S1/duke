public class Event extends Task {
    private String date;

    public Event(int index, String topic, String date) {
        super(index, topic);
        this.date = date;
        this.type = "E";
        this.details = String.format("%s (at: %s)", topic, date);
    }
}
