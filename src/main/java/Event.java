import java.util.Date;

public class Event extends Task {
<<<<<<< HEAD
    public Event(String description, String at) {
=======
    protected String at;

    public Event(String description, Date at) {
>>>>>>> branch-Level-8
        super(description);
        super.date = at;
        super.type ="E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + super.date + ")";
    }
}

