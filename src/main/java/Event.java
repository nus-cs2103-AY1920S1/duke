import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected Date time;

    public Event(String description, String time) throws DukeException {
        super(description);
        this.type = Type.E;
        try {
            SimpleDateFormat formatter =new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.type = Type.E;
            this.time = formatter.parse(time);
        } catch (ParseException e) {
            throw new DukeException("Time format wrong");
        }
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), time);
    }
}
