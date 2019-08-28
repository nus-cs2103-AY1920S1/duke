import java.util.Date;

public class Event extends Task {
    private Date at;

    public Event(String description, String at) {
        super(description);
        this.at = DukeDateFormatter.parse(at);
    }

    public String encode() {
        return "event," + super.description + "," + super.isDone + "," + DukeDateFormatter.format(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

}
