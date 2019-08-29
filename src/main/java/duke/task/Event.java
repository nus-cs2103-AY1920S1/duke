package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private Date at;
    public Event(String description, String at) throws ParseException {
        super(description);
        this.at = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(at);
    }
    @Override
    public String toString() {
        return "E -- " + super.toString() + " -- " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(at);
    }
}
