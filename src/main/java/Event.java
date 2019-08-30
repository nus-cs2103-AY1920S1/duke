import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected Date time;
    private boolean isDone;

    public Event(String description, Date time) {
        super(description);
        this.isDone = false;
        this.time = time;
    }

    public Event(String isDone, String description, String time) {
        super(isDone, description);
        try {
            this.time = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").parse(time.trim());


        } catch (ParseException e) {
            System.out.println(e.getMessage());

        }

    }


    public String toString() {
        return "[E][" + getStatusIcon() + "] " + getDescription() + " (at: " + this.time + ")\n";
    }
}
