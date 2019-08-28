import java.util.Date;

public class Event extends Task {
    private Date at;

    public Event(String description, String at) {
        super(description);
        this.at = Parser.parseDate(at);
    }

    public Event(String description, Date at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public String encode() {
        return "event," + super.description + "," + super.isDone + "," + Parser.format(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

}
