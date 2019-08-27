import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    Date at;

    public Event(String description, String eventDate) throws DukeException {
        super(description);
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.at = dateFormat.parse(eventDate);
            System.out.println(this);
        } catch (Exception e){
            throw new DukeException("Wrong date and time format");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String stringForAppend() {
        return "E | " + super.getStatusIcon() + " | " + description + " | " + at;
    }
}